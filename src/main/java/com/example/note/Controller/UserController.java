package com.example.note.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.note.Service.UserService;
import com.example.note.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
		logger.info("Creating user: {}", userDTO);
		UserDTO created = userService.create(userDTO);
		logger.info("User created successfully with ID: {}", created.getId());
		return ResponseEntity.ok(created);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		logger.info("Updating user with ID: {}", id);
		UserDTO updated = userService.update(id, userDTO);
		logger.info("User updated successfully: {}", updated);
		return ResponseEntity.ok(updated);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		logger.info("Finding user by ID: {}", id);
		List<UserDTO> users = userService.findbyid(id);
		if (users.isEmpty()) {
			logger.warn("User not found with ID: {}", id);
		} else {
			logger.info("User found: {}", users.get(0));
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/all")
	public ResponseEntity<?> find() {
		logger.info("Fetching all users");
		List<UserDTO> allUsers = userService.findall();
		logger.info("Total users found: {}", allUsers.size());
		return ResponseEntity.ok(allUsers);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		logger.info("Deleting user with ID: {}", id);
		userService.delete(id);
		logger.info("User deleted successfully with ID: {}", id);
		return ResponseEntity.ok("Successfully deleted!");
	}
}
