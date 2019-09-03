package com.example.chatapp.controllers;

import com.example.chatapp.model.Channel;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.model.Message;
import com.example.chatapp.repositories.ChannelRepository;
import com.example.chatapp.repositories.MessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @MessageMapping("/chat.addChannel")
    @SendTo("/topic/addChannel")
    public ResponseEntity<?> addChannel(@Payload Channel channel){
        Optional<Channel> channelOptional = channelRepository.findByChannelName(channel.getChannelName());
        channel.setId(ObjectId.get());
        if(!channelOptional.isPresent()){
            if(channel.getChannelName().contains(" ")){
                return new ResponseEntity<>("Channel name can't contain blank spaces!", HttpStatus.BAD_REQUEST);
            }
            else {
                Channel saveChannel = new Channel();
                saveChannel.setChannelName(channel.getChannelName().toLowerCase());
                saveChannel.setChannelDescription(StringUtils.capitalize(channel.getChannelDescription()));
                saveChannel.setUser(channel.getUser());
                saveChannel.setCreationDate(new Date());
                saveChannel.setId(ObjectId.get());
                channelRepository.save(saveChannel);
                return new ResponseEntity<>(saveChannel, HttpStatus.CREATED);
            }
        }
        else{
            if(channel.getChannelName().contains(" ")){
                return new ResponseEntity<>("Channel name can't contain blank spaces!", HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<>("Channel with that name already exists!", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/sendMessage")
    public Message sendMessage(@Payload Message message){
        Message saveMessage = new Message();
        saveMessage.setId(ObjectId.get());
        saveMessage.setChannelId(message.getChannelId());
        saveMessage.setMessageContent(message.getMessageContent());
        saveMessage.setUser(message.getUser());
        saveMessage.setTimestamp(LocalDateTime.now());

        messageRepository.save(saveMessage);

        return saveMessage;
    }

    /*@MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){
        return message;
    }*/
}
