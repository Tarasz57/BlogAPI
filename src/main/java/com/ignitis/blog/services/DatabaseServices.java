package com.ignitis.blog.services;

import com.ignitis.blog.entities.User;
import com.ignitis.blog.repositories.UserRepository;

public class DatabaseServices {

    public static boolean checkForUser(UserRepository userRepository, String email){
        if(userRepository.existsById(email)){
            return true;
        }
        else return false;
    }

    public static boolean checkCredentials(UserRepository userRepository, User user) throws Exception {
        User userFromDB = userRepository.findById(user.getEmail()).orElseThrow(() -> new Exception("User doesn't exist"));
        if(userFromDB.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }

}
