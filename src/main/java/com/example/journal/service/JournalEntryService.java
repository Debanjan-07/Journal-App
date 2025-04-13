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
            userService.saveEntry(user);
        }
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public Optional<JournalEntity> findById(int id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(int id, String userName) {
        User user = userService.findByUserName(userName);
        if (user != null) {
            user.getJournalEntries().removeIf(entry -> entry.getId() == id);
            userService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }
    }
}
