package com.example.chatapp.repositories;

import com.example.chatapp.model.PrivateMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrivateMessageRepository extends MongoRepository<PrivateMessage, String> {
    List<PrivateMessage> findByFromLikeAndToLike(String from, String to);
}
