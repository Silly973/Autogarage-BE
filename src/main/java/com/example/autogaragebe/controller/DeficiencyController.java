package com.example.autogaragebe.controller;


import com.example.autogaragebe.dto.DeficiencyDto;
import com.example.autogaragebe.model.Deficiency;
import com.example.autogaragebe.service.DeficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value= "deficiencies")
public class DeficiencyController {

    @Autowired
    private DeficiencyService deficiencyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object>getDeficiency(@PathVariable Long id){
        return ResponseEntity.ok(deficiencyService.getDeficiency(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object>getAllDeficiencies(){
        return ResponseEntity.ok(deficiencyService.getAllDeficiencies());
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createDeficiency(@Valid @RequestBody DeficiencyDto deficiencyDto) {
        Long newId = deficiencyService.createDeficiency(deficiencyDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();

    }


    @PutMapping(value = "{id}")
    public ResponseEntity<Object>updateDeficiency(@PathVariable Long id, @RequestBody Deficiency deficiency){
        deficiencyService.updateDeficiency(id, deficiency);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Object>partialUpdateDeficiency(@PathVariable Long id, @RequestBody Deficiency deficiency){
        deficiencyService.partialUpdateDeficiency(id, deficiency);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteDeficiency(@PathVariable Long id){
        deficiencyService.deleteDeficiency(id);
        return ResponseEntity.noContent().build();
    }
}
