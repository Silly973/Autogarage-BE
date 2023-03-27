package com.example.autogaragebe.service;


import com.example.autogaragebe.dto.DeficiencyDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Deficiency;
import com.example.autogaragebe.repository.DeficiencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DeficiencyService {

    @Autowired
    private DeficiencyRepository deficiencyRepository;

    public Deficiency getDeficiency(Long id){
        Optional<Deficiency> optionalDeficiency = deficiencyRepository.findById(id);

        if(optionalDeficiency.isPresent()){
            return optionalDeficiency.get();

        }else {
            throw new RecordNotFoundException("No deficiency found with id: "+ id);
        }
    }

    public List<Deficiency> getAllDeficiencies(){
        return deficiencyRepository.findAll();
    }


    public Long createDeficiency(DeficiencyDto deficiencyDto){
        Deficiency deficiency = new Deficiency();
        deficiency.setName(deficiencyDto.getName());

        Deficiency newDeficiency = deficiencyRepository.save(deficiency);
        return newDeficiency.getId();
    }

    public void updateDeficiency(Long id, Deficiency deficiency){
        Deficiency optionelDeficiency = deficiencyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Deficiency not found with id: " + id));

        if(!deficiency.getName().isEmpty()){
            optionelDeficiency.setName(deficiency.getName());
        }
       deficiencyRepository.save(optionelDeficiency);
    }

    public void partialUpdateDeficiency(Long id, Deficiency deficiency){
        Deficiency optionalDeficiency = deficiencyRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Deficiency not found with id: " + id));

        if (!(deficiency.getName() == null) && !deficiency.getName().isEmpty()){
            optionalDeficiency.setName(deficiency.getName());
        }

        deficiencyRepository.save(optionalDeficiency);
    }

    public void deleteDeficiency(Long id){
        deficiencyRepository.deleteById(id);
    }
}
