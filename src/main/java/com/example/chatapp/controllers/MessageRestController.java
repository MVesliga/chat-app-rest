package com.example.chatapp.controllers;

import com.example.chatapp.model.Message;
import com.example.chatapp.model.PrivateMessage;
import com.example.chatapp.repositories.MessageRepository;
import com.example.chatapp.repositories.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/privateMessages/findAll")
    public ResponseEntity<?> findAllPrivateMessagesFromTo(){
        List<PrivateMessage> privateMessages = privateMessageRepository.findAll();

        return new ResponseEntity<>(privateMessages, HttpStatus.OK);
    }
}
