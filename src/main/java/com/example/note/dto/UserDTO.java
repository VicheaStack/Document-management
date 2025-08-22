package com.example.note.dto;

import java.util.List;

import com.example.note.Entity.Note;

import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "content must be have!")
	private String username;
	
	@NotBlank(message = "must be have at least 8 digit")
	private String password;
	private List<Note> notes;
}
