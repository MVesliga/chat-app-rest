package com.example.chatapp.controllers;

import com.example.chatapp.model.Message;
import com.example.chatapp.model.PrivateMessage;
import com.example.chatapp.model.User;
import com.example.chatapp.repositories.MessageRepository;
import com.example.chatapp.repositories.PrivateMessageRepository;
import com.example.chatapp.repositories.UserRepository;
import org.bson.types.ObjectId;
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
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getOne/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userRepository.findByUsername(username).get();
    }

    @GetMapping("/getAll/{fromId}/{toId}")
    public List<PrivateMessage> findAll(@PathVariable String fromId, @PathVariable String toId){
        return privateMessageRepository.findAllByFromUser_IdOrToUser_Id(fromId, toId);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User updatedUser){
        Optional<User> userOptional = userRepository.findById(updatedUser.getId());

        if(userOptional.isPresent()){
            User saveUser = userOptional.get();
            saveUser.setFirstName(updatedUser.getFirstName());
            saveUser.setLastName(updatedUser.getLastName());
            saveUser.setUsername(updatedUser.getUsername());
            saveUser.setEmail(updatedUser.getEmail());

            List<Message> listOfMessagesByUser = messageRepository.findAllByUser_Id(saveUser.getId());
            List<PrivateMessage> listOfPrivateMessagesFromOrToUser = privateMessageRepository.findAllByFromUser_IdOrToUser_Id(saveUser.getId(), saveUser.getId());
            for (Message message: listOfMessagesByUser) {
                message.getUser().setEmail(saveUser.getEmail());
                message.getUser().setFirstName(saveUser.getFirstName());
                message.getUser().setLastName(saveUser.getLastName());
                message.getUser().setUsername(saveUser.getUsername());

                messageRepository.save(message);
            }

            for (PrivateMessage privateMessage: listOfPrivateMessagesFromOrToUser) {
                if(privateMessage.getFromUser().getId().equals(saveUser.getId())){
                    privateMessage.getFromUser().setEmail(saveUser.getEmail());
                    privateMessage.getFromUser().setFirstName(saveUser.getFirstName());
                    privateMessage.getFromUser().setLastName(saveUser.getLastName());
                    privateMessage.getFromUser().setUsername(saveUser.getUsername());
                }
                else if(privateMessage.getToUser().getId().equals(saveUser.getId())){
                    privateMessage.getToUser().setEmail(saveUser.getEmail());
                    privateMessage.getToUser().setFirstName(saveUser.getFirstName());
                    privateMessage.getToUser().setLastName(saveUser.getLastName());
                    privateMessage.getToUser().setUsername(saveUser.getUsername());
                }

                privateMessageRepository.save(privateMessage);
            }

            userRepository.save(saveUser);
            return saveUser;
        }
        else{
            return null;
        }
    }
}
