package com.example.autogaragebe.controller;


import com.example.autogaragebe.dto.RepairDto;
import com.example.autogaragebe.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value="repairs")
public class RepairController {

    @Autowired
    private RepairService repairService;

    //GET
    @GetMapping(value = "{id}")
    public ResponseEntity<Object>getRepair(@PathVariable Long id){
        return ResponseEntity.ok(repairService.getRepair(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object>getAllRepairs(@RequestParam(value= "date", required = false) LocalDate date){
        return ResponseEntity.ok(repairService.getAllRepairs(date));
    }

    //POSt
    @PostMapping(value = "")
    public ResponseEntity<Object>createRepair(@Valid @RequestBody RepairDto repairDto){

        Long newId = repairService.createRepair(repairDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    //PUT
    @PutMapping(value="{id}")
    public ResponseEntity<Object>updateRepair(@PathVariable Long id, @Valid @RequestBody RepairDto repairDto){
        repairService.updateRepair(id, repairDto);
         return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="{id}")
    public ResponseEntity<Object> partialUpdateRepair(@PathVariable Long id, @Valid @RequestBody RepairDto repairDto){
        repairService.partialUpdateRepair(id, repairDto);
        return ResponseEntity.noContent().build();
    }

    //Delete
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object>deleteRepair(@PathVariable Long id){
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }



}
