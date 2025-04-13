package com.example.journal.controller;


import com.example.journal.entity.User;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

      @GetMapping
      public List<User> getAllUser(){
          return userService.getAll();
      }

      @PostMapping
      public void createUser(@RequestBody User user){
          userService.saveEntry(user);
      }

      @PutMapping
      public ResponseEntity<?> updateUser(@RequestBody User user){
           User userInDb = userService.findByUserName(user.getUserName());

           if(userInDb != null){
               userInDb.setUserName(user.getUserName());
               userInDb.setPassword(user.getPassword());
               userService.saveEntry(userInDb);
           }
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }


}