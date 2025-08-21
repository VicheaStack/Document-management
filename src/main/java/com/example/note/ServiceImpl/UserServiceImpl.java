package com.example.note.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.note.Entity.User;
import com.example.note.ExceptionHandle.ResourceNotFoundException;
import com.example.note.Mapper.UserMapper;
import com.example.note.Repository.UserRepository;
import com.example.note.Service.UserService;
import com.example.note.dto.UserDTO;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Transactional
	@Override
	public UserDTO create(UserDTO userDTO) {
		logger.info("Creating new user: {}", userDTO);
		User entity = userMapper.toEntity(userDTO);
		User saved = userRepository.save(entity);
		UserDTO dto = userMapper.dto(saved);
		logger.info("User created successfully with ID: {}", dto.getId());
		return dto;
	}

	@Transactional
	@Override
	public UserDTO update(Long id, UserDTO userDTO) {
		logger.info("Updating user with ID: {}", id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("User not found with ID: {}", id);
					return new ResourceNotFoundException("User not found with ID: " + id);
				});

		// Update fields
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());

		User saved = userRepository.save(user);
		UserDTO dto = userMapper.dto(saved);
		logger.info("User updated successfully: {}", dto);
		return dto;
	}

	@Override
	public List<UserDTO> findbyid(Long id) {
		logger.info("Finding user by ID: {}", id);
		Optional<User> userOpt = userRepository.findById(id);

		return userOpt
				.map(user -> {
					logger.info("User found: {}", user);
					return List.of(userMapper.dto(user));
				})
				.orElseThrow(() -> {
					logger.error("User not found with ID: {}", id);
					return new ResourceNotFoundException("User not found with ID: " + id);
				});
	}

	@Override
	public List<UserDTO> findall() {
		logger.info("Fetching all users");
		List<UserDTO> result = userRepository.findAll()
				.stream()
				.map(userMapper::dto)
				.collect(Collectors.toList());

		if (result.isEmpty()) {
			logger.error("No users found in database");
			throw new ResourceNotFoundException("No users available in the database");
		}

		logger.info("Total users found: {}", result.size());
		return result;
	}

	@Transactional
	@Override
	public void delete(long id) {
		logger.info("Deleting user with ID: {}", id);
		if (!userRepository.existsById(id)) {
			logger.error("Cannot delete, user not found with ID: {}", id);
			throw new ResourceNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
		logger.info("User deleted successfully with ID: {}", id);
	}
}
