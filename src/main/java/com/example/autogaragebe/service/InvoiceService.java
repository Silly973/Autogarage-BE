package com.example.autogaragebe.service;

import com.example.autogaragebe.exeption.FileNotFoundException;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.*;
import com.example.autogaragebe.repository.CarRepository;
import com.example.autogaragebe.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class InvoiceService {
    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;
    private final Path uploads = Paths.get("uploads");


    @Autowired
    private InvoiceRepository invoiceRepository;

    public void file() {
        try {
            Files.createDirectory(uploads);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Autowired
    private CarRepository carRepository;


    public Invoice getInvoice(Long id){
        Optional<Invoice>optionalInvoice = invoiceRepository.findById(id);

        if(optionalInvoice.isPresent()){
            return optionalInvoice.get();
        }else {
            throw new RecordNotFoundException("No invoice found with id: " + id);
        }
    }


    public Map<String, Object> getCarInvoice(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        TreeMap<String, Object> carInvoice = new TreeMap<String, Object>();

        if (optionalCar.isPresent()) {
            Car car =  optionalCar.get();
            List<Repair> repairs = car.getRepairs();
            TreeMap<String, Object> repairsAndTotalCosts  = new TreeMap<String, Object>();

            for (Repair repair : repairs) {
                if (repair.getAppointmentStatus() == AppointmentStatus.REPAIR_COMPLETED) {
                    BigDecimal totalRepairCosts;
                    LocalDate repairDate;

                    totalRepairCosts = calculateTotalRepairPrice(repair);
                    repairDate = repair.getAppointmentDate();

                    BigDecimal tax = totalRepairCosts.multiply(new BigDecimal(0.21)).setScale(1, RoundingMode.CEILING); //rounded off to 1 decimal voor demo purpose.
                    BigDecimal totalCostWithTax = totalRepairCosts.add(tax);

                    String totalRepairCostsString = String.valueOf(totalRepairCosts);
                    String taxString = String.valueOf(tax);
                    String totalCostWithTaxString = String.valueOf(totalCostWithTax);
                    String repairDateString = repairDate.toString();

                    repairsAndTotalCosts.put("Parts:", repair.getParts());
                    repairsAndTotalCosts.put("Total costs:", totalRepairCostsString);
                    repairsAndTotalCosts.put("Tax of 21%:", taxString);
                    repairsAndTotalCosts.put("Total costs with tax:", totalCostWithTaxString);
                    repairsAndTotalCosts.put("Repair date:", repairDateString);
                }
            }

            carInvoice.put("Invoice:", car);
            carInvoice.put("Repairs:", repairsAndTotalCosts);
        } else {
            throw new RecordNotFoundException("No invoice found with ID: " + id);
        }

        sortMap(carInvoice);


        return carInvoice;
    }


    public void deleteCarInvoice(Long id) {
        carRepository.deleteById(id);
    }





    public BigDecimal calculateTotalRepairPrice(Repair repair) {
        final BigDecimal[] result = {new BigDecimal(0)};
        List<Part> parts = repair.getParts();

        parts.forEach( part -> {

            result[0] =  result[0].add(new BigDecimal(String.valueOf(part.getPrice())));

        });

        return result[0];

    }

    public Map<String, Object> sortMap(Map unsortedMap) {

        Map<String, Object> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());

        reverseSortedMap.putAll(unsortedMap);

        return reverseSortedMap;
    }



    public String uploadFile(Long id, MultipartFile file) {

        MediaType uploadedMediaType = MediaType.parseMediaType(file.getContentType());
        String acceptedMediaType = "application/pdf";

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        if (!acceptedMediaType.equals(uploadedMediaType.toString())) {
            throw new IllegalArgumentException("Incorrect file type, PDF is required.");
        }

        Invoice optionalInvoice = invoiceRepository.findById(id).orElse(null);
        if (optionalInvoice == null) {
            throw new RecordNotFoundException("No invoice found with this id: " + id);
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path copyLocation = this.uploads.resolve(originalFilename);

        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + originalFilename + ". Please try again!", e);
        }

        optionalInvoice.setInvoiceFileName(originalFilename);
        Invoice savedInvoice = invoiceRepository.save(optionalInvoice);

        return savedInvoice.getInvoiceFileName().toString();
    }



    //    // download PDF
    public UrlResource downloadFile(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            String filename = optionalInvoice.get().getInvoiceFileName();
            Path path = this.uploads.resolve(filename);
            if (Files.exists(path)) {
                try {
                    return new UrlResource(path.toUri());
                } catch (MalformedURLException e) {
                    throw new FileNotFoundException("File not found: " + filename);
                }
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } else {
            throw new RecordNotFoundException("No invoice found with id: " + id);
        }
    }



}
