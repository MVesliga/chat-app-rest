package com.example.chatapp.controllers;

import com.example.chatapp.model.Channel;
import com.example.chatapp.model.User;
import com.example.chatapp.repositories.ChannelRepository;
import com.example.chatapp.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/channels", produces = "application/json")
@CrossOrigin
public class ChannelRestController {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAll")
    public List<Channel> getAllChannels(){
        return channelRepository.findAll();
    }
}
