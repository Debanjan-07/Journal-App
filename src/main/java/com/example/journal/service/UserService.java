package com.example.journal.service;


import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Used to return all the data into the database
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //Used to Add the data into the database
    public User saveEntry(User user) {
        return userRepository.save(user);
    }

    //used to find the data based on the id
    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    //used to delete the data based on the id
    public void deleteById(int id){
        userRepository.deleteById(id);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}