package com.example.journal.repository;

import com.example.journal.entity.JournalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntity, Integer> {

}