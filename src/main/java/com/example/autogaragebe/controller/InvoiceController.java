package com.example.autogaragebe.controller;

import com.example.autogaragebe.model.Invoice;
import com.example.autogaragebe.service.CarService;
import com.example.autogaragebe.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping(value="/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

   private Invoice invoice;

    @Autowired
    private CarService carService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Object>getInvoice(@PathVariable Long id){
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @GetMapping(value="")
    public ResponseEntity<Object>getAllInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoicess());
}


    @GetMapping(value = "carInvoice/{id}")
    public ResponseEntity<Object> getCarInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getCarInvoice(id));
    }

    @DeleteMapping(value = "carInvoice/{id}")
    public ResponseEntity<Object> deleteCarInvoice(@PathVariable Long id) {
        invoiceService.deleteCarInvoice(id);
        return ResponseEntity.notFound().build();
    }

    // Upload Invoice PDF
    @PostMapping(value = "/carInvoice/{id}/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, "application/json"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String invoiceFileName = invoiceService.uploadFile(id, file);

        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath("/uploads/" + invoiceFileName)
                .build()
                .toUri();


        return ResponseEntity.created(location).body(location);
    }

    // Download Invoice PDF
    @GetMapping(value = "/carInvoice/{id}/download")
    public ResponseEntity downloadFile(@PathVariable Long id) {
       Resource resource = invoiceService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"") //getFilename
                .body(resource);
    }

}
