package com.example.chatapp.controllers;

import com.example.chatapp.model.User;
import com.example.chatapp.model.UserCredentials;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtGenerator;
    private UserRepository userRepository;

    @Autowired
    public TokenController(JwtGenerator jwtGenerator, UserRepository userRepository) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    @PostMapping
    public String generate(@RequestBody UserCredentials userCredentials){

        User user = userRepository.findByUsername(userCredentials.getUsername()).get();

        String token =  jwtGenerator.generate(user);

        return token;
    }
}
