package com.example.chatapp.controllers;

import com.example.chatapp.model.User;
import com.example.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/users", produces="application/json")
@CrossOrigin
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getOne/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userRepository.findByUsername(username).get();
    }
}
