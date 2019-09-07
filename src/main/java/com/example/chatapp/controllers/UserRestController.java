package com.example.chatapp.controllers;

import com.example.chatapp.model.User;
import com.example.chatapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User updatedUser){
        Optional<User> userOptional = userRepository.findById(updatedUser.getId());

        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setImgUrl(updatedUser.getImgUrl());

            userRepository.save(user);
            return user;
        }
        else{
            return null;
        }
    }
}
