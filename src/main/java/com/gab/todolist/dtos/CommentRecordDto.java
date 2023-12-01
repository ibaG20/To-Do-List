package com.gab.todolist.dtos;

import com.gab.todolist.entities.Task;

import jakarta.validation.constraints.NotBlank;

public record CommentRecordDto(
		@NotBlank String description,
		Task task) {
}
