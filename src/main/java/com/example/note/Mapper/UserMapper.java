package com.example.note.Mapper;

import org.mapstruct.Mapper;

import com.example.note.Entity.User;
import com.example.note.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO dto(User user);

	User toEntity(UserDTO dto);

}
