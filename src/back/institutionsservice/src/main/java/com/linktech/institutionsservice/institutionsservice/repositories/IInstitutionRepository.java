package com.linktech.institutionsservice.institutionsservice.repositories;

import org.springframework.stereotype.Repository;

import com.linktech.institutionsservice.institutionsservice.models.Institution;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface IInstitutionRepository extends MongoRepository<Institution, String> {

}