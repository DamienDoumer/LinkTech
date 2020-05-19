package com.linktech.messagesservice.messagesservice.repositories;

import java.util.List;

import com.linktech.messagesservice.messagesservice.models.Message;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMessagesRepository extends MongoRepository<Message, String> {

    List<Message> findBySenderId(String senderId);
    List<Message> findByReceiverId(String receiverId);

}