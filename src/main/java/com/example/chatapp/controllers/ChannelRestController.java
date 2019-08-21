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

    @PostMapping("/add")
    public ResponseEntity<?> addChannel(@RequestBody Channel channel){
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
                //User user = userRepository.findByUsername(channel.getUser().getUsername()).get();
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
}
