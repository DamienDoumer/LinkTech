package com.linktech.apigateway.apigateway.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linktech.apigateway.apigateway.Models.Role;

/**
 *
 * @author didin
 */
@Repository
public interface IRoleRepository extends MongoRepository<Role, String> {
    
    Role findByRole(String role);
} 