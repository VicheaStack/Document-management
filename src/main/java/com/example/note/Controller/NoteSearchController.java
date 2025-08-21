package com.example.note.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.note.Service.NoteService;
import com.example.note.dto.NoteDTO;

@RestController
@RequestMapping("/querySearch")
public class NoteSearchController {

	private static final Logger logger = LogManager.getLogger(NoteSearchController.class);

	private final NoteService noteService;

	public NoteSearchController(NoteService noteService) {
		super();
		this.noteService = noteService;
	}

	@GetMapping("/findby/title")
	public ResponseEntity<List<NoteDTO>> findbyTitle(@RequestParam String title) {
		List<NoteDTO> byTitle = noteService.findByTitle(title);
		if (byTitle.isEmpty()) {
			logger.warn("No note found with id: {}", title);
			return ResponseEntity.notFound().build();
		}
		logger.info("Found {} notes with title: {}", byTitle.size(), title);
		return ResponseEntity.ok(byTitle);
	}

	@GetMapping("/findbyContent")
	public ResponseEntity<List<NoteDTO>> findByContent(@RequestParam String content) {
		List<NoteDTO> byContent = noteService.findByContent(content);
		if (byContent.isEmpty()) {
			logger.warn("notes found with content: {} ", content);
			return ResponseEntity.ok(byContent);
		}
		logger.info(" Found {} notes with content: {} ", byContent.size(), content);
		return ResponseEntity.ok(byContent);
	}

	@GetMapping("/findByIgnoreCase")
	public ResponseEntity<List<NoteDTO>> findbyIgnoreContentCase(@RequestParam String title) {
		List<NoteDTO> byTitleContainingIgnoreCase = noteService.findByTitleContainingIgnoreCase(title);
		if (byTitleContainingIgnoreCase.isEmpty()) {
			logger.warn("no note found containing (ignore case title: {}", title);
			return ResponseEntity.ok(byTitleContainingIgnoreCase);
		}
		logger.info("Found {} notes containing (ignore case) title: {}", byTitleContainingIgnoreCase);
		return ResponseEntity.ok(byTitleContainingIgnoreCase);
	}

	@GetMapping("/findByAuthor")
	public ResponseEntity<List<NoteDTO>> findByAuthor(@RequestParam String author) {
		List<NoteDTO> byAuthor = noteService.findByAuthor(author);
		if (byAuthor.isEmpty()) {
			logger.warn("no notes found by author: {}", author);
			return ResponseEntity.ok(byAuthor);
		}
		logger.info("Found {} notes by author: {}", byAuthor.size(), author);
		return ResponseEntity.ok(byAuthor);
	}

}