package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.JournalEntryRepository;
import com.example.journal.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public List<JournalEntity> getAll() {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            journalEntity.setUser(user);
            journalEntity.setDate(LocalDateTime.now()); // fixed: not static
            user.getJournalEntries().add(journalEntity);
            userService.saveUser(user);
        }
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public Optional<JournalEntity> findById(int id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(int id, String userName) {
        boolean remove = false;
        try {
            User user = userService.findByUserName(userName);
            if (user != null) {
                 remove = user.getJournalEntries().removeIf(entry -> entry.getId() == id);
                if(remove){
                    userService.saveUser(user);
                    journalEntryRepository.deleteById(id);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An Error occurs while deleting the journal entry",e);
        }

        return remove;

    }

}
