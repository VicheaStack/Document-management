package com.example.note.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.note.Entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByContent(String content);

	List<Note> findByTitle(String title);

	List<Note> findByAuthor(String author);

	List<Note> findByTitleContainingIgnoreCase(String title);

}
