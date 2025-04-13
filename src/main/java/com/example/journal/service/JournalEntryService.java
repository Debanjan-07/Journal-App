package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.JournalEntryRepository;
import com.example.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //Used to return all the data into the database
    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }


    //Used to Add the data into the database
    public void saveEntry(JournalEntity journalEntity, String userName) {
        User user = userService.findByUserName(userName);
        if(user != null){
            journalEntity.setUser(user);
            JournalEntity.setDate(LocalDateTime.now());
            user.getJournalEntries().add(journalEntity);
            userService.saveEntry(user);
        }


    }

    //used to find the data based on the id
    public Optional<JournalEntity> findById(int id){
        return journalEntryRepository.findById(id);
    }

    //used to delete the data based on the id
    public void deleteById(int id, String userName){
        User user = userService.findByUserName(userName);
        if(user != null){
        user.getJournalEntries().removeIf(x -> x.getId()== id);
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
        }
    }
}