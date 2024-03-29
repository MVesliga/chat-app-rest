package com.example.chatapp.repositories;

import com.example.chatapp.model.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends MongoRepository<Channel, String> {
    Optional<Channel> findByChannelName(String channelName);
}
