package com.example.chatapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document(collection = "messages")
public class Message {

    @Id
    private ObjectId id;
    private String channelId;

    private String messageContent;
    private ZonedDateTime timestamp;
    private User user;

    public Message() {}

    public Message(ObjectId id, String channelId, String messageContent, ZonedDateTime timestamp, User user) {
        this.id = id;
        this.channelId = channelId;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
        this.user = user;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
