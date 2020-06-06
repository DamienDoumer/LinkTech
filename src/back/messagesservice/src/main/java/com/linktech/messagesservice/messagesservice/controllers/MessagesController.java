package com.linktech.messagesservice.messagesservice.controllers;

import com.linktech.messagesservice.messagesservice.models.*;

import java.util.Date;
import java.util.List;

import com.linktech.messagesservice.messagesservice.repositories.IMessagesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/messages")
public class MessagesController {

    @Autowired
    IMessagesRepository messagesRepository;

    @GetMapping("/{conversationId}")
    public List<Message> getMessagesForConversation(@PathVariable("conversationId") String conversationId){
        return messagesRepository.findAll();
    }
    
    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity getMessagesForSenderAndReceiver(@PathVariable("senderId") String senderId,
        @PathVariable("receiverId") String receiverId){

        List<Message> iSent = messagesRepository.findBySenderId(senderId);
        List<Message> iReceived = messagesRepository.findByReceiverId(receiverId);

        for (Message message : iReceived) {
            if (!iSent.contains(message)){
                iSent.add(message);
            }
        }
        
        return new ResponseEntity<>(iSent, HttpStatus.OK);
    }

    @PostMapping()
    public Message sendMessage(@RequestBody Message message){
        message.setCreationDate(new Date());
        return messagesRepository.save(message);
    }
}