package com.example.journal.service;


import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Used to return all the data into the database
    public List<User> getAll(){
        return userRepository.findAll();
    }


    //Used to Add the data into the database
    public boolean saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
    }
    public void saveUser(User user){
        userRepository.save(user);
    }


    //used to find the data based on the id
    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    //used to delete the data based on the id
    @Transactional
    public void deleteByUserName(String name){
        userRepository.deleteByUserName(name);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        return true;
    }


}