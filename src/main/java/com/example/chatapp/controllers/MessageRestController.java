package com.example.chatapp.controllers;

import com.example.chatapp.converters.ZonedDateTimeWriteConverter;
import com.example.chatapp.model.Message;
import com.example.chatapp.model.PrivateMessage;
import com.example.chatapp.repositories.MessageRepository;
import com.example.chatapp.repositories.PrivateMessageRepository;
import org.bson.types.ObjectId;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api/messages", produces = "application/json")
@CrossOrigin
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @GetMapping("/messages/findAll/{channelId}")
    public ResponseEntity<?> findAllMessagesByChannelId(@PathVariable("channelId") String channelId){
        List<Message> messages = messageRepository.findByChannelIdLike(channelId);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/privateMessages/findAll/{from}/{to}")
    public ResponseEntity<?> findAllPrivateMessagesFromTo(@PathVariable("from") String from, @PathVariable("to") String to){
        List<PrivateMessage> privateMessages = privateMessageRepository.findByFromLikeAndToLike(from, to);

        return new ResponseEntity<>(privateMessages, HttpStatus.OK);
    }
}
