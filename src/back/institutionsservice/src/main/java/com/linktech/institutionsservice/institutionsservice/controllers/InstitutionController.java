package com.linktech.institutionsservice.institutionsservice.controllers;

import java.util.List;

import com.linktech.institutionsservice.institutionsservice.models.Institution;
import com.linktech.institutionsservice.institutionsservice.repositories.IInstitutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/institutions")
public class InstitutionController {

    @Autowired
    private IInstitutionRepository institutionRepository;

    @PostMapping()
    public Institution createInstitution(@RequestBody Institution institution){
        return institutionRepository.save(institution);
    }

    @GetMapping()
    public List<Institution> getInstitutions(){
        return institutionRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Institution getInstitution(@PathVariable("id") String id){
        return institutionRepository.findById(id).get();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInstitution(@PathVariable("id") String id){
        institutionRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Institution updateInstitution(@PathVariable("id") String id, @RequestBody Institution institution){
        institution.setId(id);
        return institutionRepository.save(institution);
    }
}