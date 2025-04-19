package com.example.journal.controller;

import com.example.journal.entity.User;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/h")
    public String healthcheck(){
        return "All is Well ....";
    }
    @PostMapping("/create-user")
    public boolean saveUser(@RequestBody User user){
        return userService.saveEntry(user);
    }

}
