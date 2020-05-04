package com.linktech.apigateway.apigateway.Repositories;

import com.linktech.apigateway.apigateway.Secuirity.JwtToken;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken,String> {
}