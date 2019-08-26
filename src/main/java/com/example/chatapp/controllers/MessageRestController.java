package com.example.chatapp.controllers;

import com.example.chatapp.converters.ZonedDateTimeWriteConverter;
import com.example.chatapp.model.Message;
import com.example.chatapp.repositories.MessageRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

@RestController
@RequestMapping(path="/api/messages", produces = "application/json")
@CrossOrigin
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepository;
    private ZonedDateTimeWriteConverter zonedDateTimeWriteConverter;

    @PostMapping("/add")
    public ResponseEntity<?> saveMessage(@RequestBody Message message){
        Message saveMessage = new Message();
        saveMessage.setId(ObjectId.get());
        saveMessage.setChannelId(message.getChannelId());
        saveMessage.setMessageContent(message.getMessageContent());
        saveMessage.setUser(message.getUser());
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        saveMessage.setTimestamp(LocalTime.now());

        messageRepository.save(saveMessage);

        return new ResponseEntity<>(saveMessage, HttpStatus.CREATED);
    }
}
