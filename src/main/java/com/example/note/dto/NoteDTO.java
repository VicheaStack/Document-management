package com.example.note.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDTO {

	private Long id;

	@NotBlank(message = "Title must be have!")
	private String title;

	@NotBlank(message = "content must be have!")
	private String content;

	@NotBlank(message = "must be have author name!")
	private String author;

	@NotBlank(message = "false or true")
	private boolean complete;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	private String userId;
}
