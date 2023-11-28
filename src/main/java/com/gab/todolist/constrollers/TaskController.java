package com.gab.todolist.constrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gab.todolist.entities.Task;
import com.gab.todolist.repositories.TaskRepository;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	@GetMapping
	public List<Task> findAll() {
		List<Task> result = repository.findAll();
		return result;
	}

}
