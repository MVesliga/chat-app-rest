package com.example.chatapp.controllers;

import com.example.chatapp.model.Channel;
import com.example.chatapp.repositories.ChannelRepository;
import com.example.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
