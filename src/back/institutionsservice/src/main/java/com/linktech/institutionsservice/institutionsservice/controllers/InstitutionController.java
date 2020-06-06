package com.linktech.institutionsservice.institutionsservice.controllers;

import java.util.List;

import com.linktech.institutionsservice.institutionsservice.models.Institution;
import com.linktech.institutionsservice.institutionsservice.repositories.IInstitutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping(value = "/{countryName}/{sector}")
    public ResponseEntity<List<Institution>> seachInstitution(@PathVariable(name = "countryName", required = false) String countryName,
        @PathVariable(name = "sector", required = false) String sector){
        
        if (countryName != null && sector != null){
            return new ResponseEntity<List<Institution>>(institutionRepository.findByCountryNameAndSector(countryName, sector), HttpStatus.OK);
        }
        if (countryName != null)
            return new ResponseEntity<List<Institution>>(institutionRepository.findByCountryName(countryName), HttpStatus.OK);
        if (sector != null)
            return new ResponseEntity<List<Institution>>(institutionRepository.findBySector(sector), HttpStatus.OK);
        
        return new ResponseEntity<List<Institution>>(institutionRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInstitution(@PathVariable("id") String id){
        institutionRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Institution> updateInstitution(@PathVariable("id") String id,
         @RequestBody Institution institution){
        
        institution.setId(id);
        Institution thisInstitution = institutionRepository.findById(id).get();
        
        if (thisInstitution == null)
            return new ResponseEntity<Institution>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(institutionRepository.save(institution),HttpStatus.OK);
    }
}