package com.gab.todolist.dtos;

import java.util.List;

import com.gab.todolist.entities.Category;
import com.gab.todolist.entities.Comment;

import jakarta.validation.constraints.NotBlank;

public record TaskRecordDto(
		@NotBlank String description,
		boolean completed,
		Category category,
		List<Comment> comments) {
}
