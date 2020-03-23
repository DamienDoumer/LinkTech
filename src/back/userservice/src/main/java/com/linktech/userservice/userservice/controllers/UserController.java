package com.linktech.userservice.userservice.controllers;
import java.util.Optional;

import com.linktech.userservice.userservice.models.UserModel;
import com.linktech.userservice.userservice.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    
    @RequestMapping(method = RequestMethod.POST)
    public UserModel createUser(@RequestBody UserModel user){
        return userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(){
        return "No User";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<UserModel> getUserById(@PathVariable("id") String id) {
        return userRepository.findById(id);
    }
}