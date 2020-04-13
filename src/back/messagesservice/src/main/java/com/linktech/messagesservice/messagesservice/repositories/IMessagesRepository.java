package com.linktech.messagesservice.messagesservice.repositories;

import com.linktech.messagesservice.messagesservice.models.Message;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMessagesRepository extends MongoRepository<Message, String> {

}