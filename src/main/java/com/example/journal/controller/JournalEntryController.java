package com.example.journal.controller;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.service.JournalEntryService;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryservice;
    @Autowired
    private UserService userService;

    // Get all the data into the database
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntity> all = user.getJournalEntries();
        if(all != null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add new Journal into the database
    @PostMapping("userName/{userName}")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntity,@PathVariable String userName) {

            User user = userService.findByUserName(userName);
            if(user != null){
                myEntity.setUser(user);
                journalEntryservice.saveEntry(myEntity,userName);
                return new ResponseEntity<>(myEntity,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    //Get the data using the Id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalEntryBYId(@PathVariable int id) {
        Optional<JournalEntity> journalEntity = journalEntryservice.findById(id);
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Delete the data based on the Id
    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable int id,@PathVariable String userName){
        journalEntryservice.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}