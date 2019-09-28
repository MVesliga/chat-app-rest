package com.example.chatapp.controllers;

import com.example.chatapp.model.JwtUser;
import com.example.chatapp.model.User;
import com.example.chatapp.model.UserCredentials;
import com.example.chatapp.repositories.UserRepository;
import com.example.chatapp.security.JwtGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auth", produces = "application/json")
@CrossOrigin
public class AuthRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtGenerator jwtGenerator;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(!userOptional.isPresent()){
            String hashedPassword = passwordEncoder().encode(user.getPassword());
            user.setId(ObjectId.get());
            user.setPassword(hashedPassword);
            user.setRole("USER");
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else{
            if(userOptional.get().getEmail().equals(user.getEmail())){
                return new ResponseEntity<>("User with that email already exists!", HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<>("User with that username already exists!", HttpStatus.BAD_REQUEST);
            }

        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentials userCredentials){

        Optional<User> storedUser = userRepository.findByUsername(userCredentials.getUsername());

        if(!storedUser.isPresent()){
            return new ResponseEntity<>("User doesn't exist.", HttpStatus.NOT_FOUND);
        }
        else {
            if(passwordEncoder().matches(userCredentials.getPassword(), storedUser.get().getPassword())){
                String token =  jwtGenerator.generate(storedUser.get());
                JwtUser jwtUser = new JwtUser();
                jwtUser.setUser(storedUser.get());
                jwtUser.setToken(token);
                return new ResponseEntity<>(jwtUser, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Wrong password.",HttpStatus.NOT_FOUND);
            }
        }
    }
}
