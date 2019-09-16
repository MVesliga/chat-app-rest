package com.example.chatapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "privateMessages")
public class PrivateMessage {
    @Id
    private ObjectId id;

    private User fromUser;
    private User toUser;
    private String imageUrl;
    private String messageContent;
    private LocalDateTime timestamp;

    public PrivateMessage() {}

    public PrivateMessage(ObjectId id, User fromUser, User toUser,String imageUrl, String messageContent, LocalDateTime timestamp) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.imageUrl = imageUrl;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
