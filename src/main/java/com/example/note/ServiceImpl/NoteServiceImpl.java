package com.example.note.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.note.Entity.Note;
import com.example.note.ExceptionHandle.ResourceNotFoundException;
import com.example.note.Mapper.NoteMapper;
import com.example.note.Mapper.UserMapper;
import com.example.note.Repository.NoteRepository;
import com.example.note.Service.NoteService;
import com.example.note.dto.NoteDTO;

import jakarta.transaction.Transactional;

@Service
public class NoteServiceImpl implements NoteService {

	private static final Logger logger = LogManager.getLogger(NoteServiceImpl.class);

	private final NoteRepository noteRepository;
	private final NoteMapper noteMapper;
	private final UserMapper userMapper;

	public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper, UserMapper userMapper) {
		this.noteRepository = noteRepository;
		this.noteMapper = noteMapper;
		this.userMapper = userMapper;
	}

	@Transactional
	@Override
	public NoteDTO create(NoteDTO noteDTO) {
		logger.info("Creating new note: {}", noteDTO);
		Note entity = noteMapper.toEntity(noteDTO);
		Note save = noteRepository.save(entity);
		logger.info("Note created with ID: {}", save.getId());
		return noteMapper.dto(save);
	}

	@Transactional
	@Override
	public Optional<NoteDTO> edit(Long id, NoteDTO noteDTO) {
		logger.info("Editing note with ID: {}", id);

		return noteRepository.findById(id)
				.map(note -> {
					note.setTitle(noteDTO.getTitle());
					note.setContent(noteDTO.getContent());
					note.setComplete(noteDTO.isComplete());
					Note saved = noteRepository.save(note);
					logger.info("Note updated successfully: {}", id);
					return noteMapper.dto(saved);
				});
	}

	@Transactional
	@Override
	public NoteDTO findbyid(Long id) {
		logger.info("Finding note with ID: {}", id);
		Note foundNote = noteRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Note not found for ID: {}", id);
					return new ResourceNotFoundException("Note not found with ID: " + id);
				});
		logger.info("Note found: {}", foundNote);
		return noteMapper.dto(foundNote);
	}

	@Transactional
	@Override
	public List<NoteDTO> findall() {
		List<Note> all = noteRepository.findAll();

		if (all.isEmpty()) {
			logger.error("No notes found!");
			throw new ResourceNotFoundException("No notes available in the database");
		}

		return all.stream()
				.map(noteMapper::dto)
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void delete(Long id) {
		logger.info("Deleting note with ID: {}", id);
		if (!noteRepository.existsById(id)) {
			logger.error("Note not found for deletion: {}", id);
			throw new ResourceNotFoundException("Note not found with ID: " + id);
		}
		noteRepository.deleteById(id);
		logger.info("Note deleted successfully: {}", id);
	}

	@Transactional()
	public List<NoteDTO> findByAuthor(String name) {
		List<Note> byAuthor = noteRepository.findByAuthor(name);
		return byAuthor.stream()
				.map(noteMapper::dto)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<NoteDTO> findByTitle(String title) {
		List<Note> byTitle = noteRepository.findByTitle(title);
		return byTitle.stream()
				.map(noteMapper::dto)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<NoteDTO> findByTitleContainingIgnoreCase(String name) {
		List<Note> byIgnoreCase = noteRepository.findByTitleContainingIgnoreCase(name);
		return byIgnoreCase.stream()
				.map(noteMapper::dto)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<NoteDTO> findByContent(String content) {
		List<Note> contentList = noteRepository.findByContent(content);
		return contentList.stream()
				.map(noteMapper::dto)
				.collect(Collectors.toList());
	}

}
