package com.example.note.dto;

import java.time.LocalDateTime;

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
	private String title;
	private String content;
	private String author;
	private boolean complete;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	private String userId;
}
