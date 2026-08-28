package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Note;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    void deleteByCustomerId(Long customerId);

}