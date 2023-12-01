package com.gab.todolist.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRecordDto(
		@NotBlank String name
		/* List<Task> tasks */) {

}
