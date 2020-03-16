package com.linktech.userservice.userservice.repositories;

import com.linktech.userservice.userservice.models.UserModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<UserModel, String>{
}
 