package com.example.note.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.note.Service.NoteService;
import com.example.note.dto.NoteDTO;

@RestController
@RequestMapping("/request")
public class NoteController {

	private static final Logger logger = LogManager.getLogger(NoteController.class);

	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createNote(@RequestBody NoteDTO noteDTO) {
		logger.info("Creating a new note: {}", noteDTO);
		NoteDTO created = noteService.create(noteDTO);
		logger.info("Note created successfully with ID: {}", created.getId());
		return ResponseEntity.ok(created);
	}

	@PatchMapping("/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
		logger.info("Editing note with ID: {}", id);
		Optional<NoteDTO> edit = noteService.edit(id, noteDTO);
		if (edit.isPresent()) {
			logger.info("Note updated successfully: {}", edit.get());
			return ResponseEntity.ok("Update successful: " + edit.get());
		} else {
			logger.warn("Note with ID {} not found", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
		}
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		logger.info("Finding note with ID: {}", id);
		NoteDTO found = noteService.findbyid(id);
		if (found != null) {
			logger.info("Note found: {}", found);
			return ResponseEntity.ok(found);
		} else {
			logger.warn("Note with ID {} not found", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Note not found");
		}
	}

	@GetMapping("/findall")
	public ResponseEntity<?> find() {
		List<NoteDTO> findall = noteService.findall();
		return ResponseEntity.ok(findall);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		logger.info("Deleting note with ID: {}", id);
		noteService.delete(id);
		logger.info("Note deleted successfully with ID: {}", id);
		return ResponseEntity.ok("Note deleted successfully");
	}
}
