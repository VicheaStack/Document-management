package com.example.note.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

import com.example.note.Entity.Note;
import com.example.note.Entity.User;
import com.example.note.dto.NoteDTO;

@Mapper(componentModel = "spring", uses = { UserMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface NoteMapper {

	@Mapping(source = "user", target = "userId", qualifiedByName = "userToId")
	NoteDTO dto(Note note);

	@Mapping(source = "userId", target = "user", qualifiedByName = "idToUser")
	Note toEntity(NoteDTO noteDTO);

	// convert User -> String
	@Named("userToId")
	default String userToId(User user) {
		return user == null ? null : String.valueOf(user.getId());
	}

	// convert String -> User
	@Named("idToUser")
	default User idToUser(String id) {
		if (id == null || id.isBlank())
			return null;
		User user = new User();
		user.setId(Long.parseLong(id));
		return user;
	}
}
