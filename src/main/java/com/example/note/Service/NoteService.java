package com.example.note.Service;

import java.util.List;
import java.util.Optional;

import com.example.note.dto.NoteDTO;

public interface NoteService {

	NoteDTO create(NoteDTO noteDTO);

	Optional<NoteDTO> edit(Long id, NoteDTO noteDTO);

	NoteDTO findbyid(Long id);

	List<NoteDTO> findall();

	void delete(Long id);

	List<NoteDTO> findByTitle(String title);

	List<NoteDTO> findByContent(String content);

	List<NoteDTO> findByAuthor(String author);

	List<NoteDTO> findByTitleContainingIgnoreCase(String title);
}
