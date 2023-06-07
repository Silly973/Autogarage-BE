package com.example.autogaragebe.service;

import com.example.autogaragebe.dto.PartDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Part;
import com.example.autogaragebe.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;


    public Part getPart(Long id){
        Optional<Part> optionalPart = partRepository.findById(id);

        if(optionalPart.isPresent()){
            return optionalPart.get();
        }else{
            throw new RecordNotFoundException("No part found with id: " + id);
        }
    }

    public List<Part> getAllParts(){
        return partRepository.findAll();
    }



    public Long createPart(PartDto partDto){
        Part part = new Part();
        part.setName(partDto.getName());
        part.setPrice(partDto.getPrice());

        Part newPart = partRepository.save(part);
        return newPart.getId();
    }


    public void updatePart(Long id, Part part) {
        Part existingItem = partRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Part not found with id: " + id));

        if (!part.getName().isEmpty()) {
            existingItem.setName(part.getName());
        }
        partRepository.save(existingItem);
    }




    public void partialUpdatePart(Long id, Part part){
        Part optionalPart = partRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Part not found with id: " + id));

        if (!(part.getName() == null) && !part.getName().isEmpty()) {
            optionalPart.setName(part.getName());
        }
        partRepository.save(optionalPart);
    }


    public void deletePart(Long id){
        partRepository.deleteById(id);
    }


    public void save(Part updatedPart) {
    }
}
