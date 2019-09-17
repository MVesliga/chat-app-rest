package com.example.chatapp.repositories;

import com.example.chatapp.model.PrivateMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrivateMessageRepository extends MongoRepository<PrivateMessage, String> {
    List<PrivateMessage> findAllByFromUser_IdOrToUser_Id(String fromId, String toId);
}
