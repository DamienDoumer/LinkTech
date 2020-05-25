package com.linktech.institutionsservice.institutionsservice.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.linktech.institutionsservice.institutionsservice.models.Institution;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface IInstitutionRepository extends MongoRepository<Institution, String> {
    List<Institution> findBySector(String sector);
    List<Institution> findByCountryName(String countryName);
    List<Institution> findByCountryNameAndSector(String countryName, String sector);
}