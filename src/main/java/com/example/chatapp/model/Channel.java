package com.example.chatapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "channels")
public class Channel {

    @Id
    private ObjectId id;
    private String channelName;
    private String channelDescription;
    @JsonProperty("createdBy")
    private User user;
    private Date creationDate;

    public Channel(){}

    public Channel(ObjectId id, String channelName, String channelDescription, User user, Date creationDate) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.user = user;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    @JsonProperty("createdBy")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
