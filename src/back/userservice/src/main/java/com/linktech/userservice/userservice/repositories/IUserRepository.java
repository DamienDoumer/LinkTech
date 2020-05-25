package com.linktech.userservice.userservice.repositories;

import java.util.List;

import com.linktech.userservice.userservice.models.UserModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<UserModel, String>{

    UserModel findByEmail(String email);
    List<UserModel> findByFirstName(String firstName);
    List<UserModel> findBySecondName(String secondName);
    List<UserModel> findByFirstNameAndSecondName(String firstName, String secondName);
}
 