package com.example.note.Service;

import java.util.List;

import com.example.note.dto.UserDTO;

public interface UserService {

	UserDTO create(UserDTO userDTO);

	UserDTO update(Long id, UserDTO userDTO);

	List<UserDTO> findbyid(Long id);

	List<UserDTO> findall();

	void delete(long id);

}
