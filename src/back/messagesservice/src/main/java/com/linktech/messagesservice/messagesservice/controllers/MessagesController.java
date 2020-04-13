package com.linktech.messagesservice.messagesservice.controllers;

import com.linktech.messagesservice.messagesservice.models.*;

import java.util.List;

import com.linktech.messagesservice.messagesservice.repositories.IMessagesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/messages")
public class MessagesController {

    @Autowired
    IMessagesRepository messagesRepository;

    @GetMapping("/{conversationId}")
    public List<Message> getMessagesForConversation(@PathVariable("conversationId") String conversationId){
        return messagesRepository.findAll();
    }

    @PostMapping()
    public Message sendMessage(@RequestBody Message user){
        return messagesRepository.save(user);
    }
}