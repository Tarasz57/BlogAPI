package com.ignitis.blog.controllers;

import com.ignitis.blog.entities.User;
import com.ignitis.blog.repositories.UserRepository;
import com.ignitis.blog.services.DatabaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/registration")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        if(DatabaseServices.checkForUser(userRepository,user.getEmail())) {
            return new ResponseEntity<String>("User already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userRepository.save(user);
        return new ResponseEntity<String>("Registration successful", HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) throws Exception {
        if(DatabaseServices.checkCredentials(userRepository,user)){
            return new ResponseEntity<String>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
