package com.example.note.dto;

import java.util.List;

import com.example.note.Entity.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	private Long id;
	
	@notblank
	private String username;
	private String password;
	private List<Note> notes;
}
