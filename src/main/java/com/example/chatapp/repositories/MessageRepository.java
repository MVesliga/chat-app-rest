package com.example.chatapp.repositories;

import com.example.chatapp.model.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChannelIdLike(String channelId);
    List<Message> findAllByUser_Id(String id);
}
