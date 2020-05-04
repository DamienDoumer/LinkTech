package com.linktech.apigateway.apigateway.Repositories;

import java.util.List;

import com.linktech.apigateway.apigateway.Models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IUserRepository extends MongoRepository<User, String> {
    @Query(value="{'email' : ?0}")
    User findByEmail(String email);
}