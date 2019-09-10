package com.example.chatapp.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "privateMessages")
public class PrivateMessage {
    @Id
    private ObjectId id;

    private String from;
    private String to;
    private String messageContent;
    private LocalDateTime timestamp;

    public PrivateMessage() {}

    public PrivateMessage(ObjectId id, String from, String to, String messageContent, LocalDateTime timestamp) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
