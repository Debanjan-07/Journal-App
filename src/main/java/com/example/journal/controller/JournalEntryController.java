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

    // Get all journal entries for a user
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            List<JournalEntity> all = user.getJournalEntries();
            if (all != null && !all.isEmpty()) {
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add new Journal Entry
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntity, @PathVariable String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            myEntity.setUser(user);
            journalEntryservice.saveEntry(myEntity, userName);
            return new ResponseEntity<>(myEntity, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get journal entry by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable int id) {
        Optional<JournalEntity> journalEntity = journalEntryservice.findById(id);
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete journal entry by ID and username
    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String userName, @PathVariable int id) {
        journalEntryservice.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update journal entry by ID
    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable int id,
            @RequestBody JournalEntity newEntry,
            @PathVariable String userName
    ) {
        JournalEntity old = journalEntryservice.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            journalEntryservice.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
