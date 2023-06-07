package com.example.autogaragebe.controller;

import com.example.autogaragebe.dto.PartDto;
import com.example.autogaragebe.model.Part;
import com.example.autogaragebe.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="parts")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping(value="{id}")
    public ResponseEntity<Object>getPart(@PathVariable Long id){
        return ResponseEntity.ok(partService.getPart(id));
    }

    @GetMapping(value="")
    public ResponseEntity<Object>getAllParts(){
        return ResponseEntity.ok(partService.getAllParts());
    }

    @PostMapping(value = "")
    public ResponseEntity<Object>createPart(@Valid @RequestBody PartDto partDto){
        Long newId = partService.createPart(partDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value="{id}")
    private ResponseEntity<Object> updatePart(@PathVariable("id") Long id, @RequestBody Part part){
        Part updatedPart = partService.getPart(id);
        updatedPart.setName(part.getName());
        updatedPart.setPrice(part.getPrice());
        partService.updatePart(id, part);

        return ResponseEntity.noContent().build();

   }


    @PatchMapping(value="{id}")
    public ResponseEntity<Object>partialUpdatePart(@PathVariable Long id, @RequestBody Part part){
        partService.partialUpdatePart(id, part);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object>deletePart(@PathVariable Long id){
     partService.deletePart(id);


        return ResponseEntity.noContent().build();
    }

}
