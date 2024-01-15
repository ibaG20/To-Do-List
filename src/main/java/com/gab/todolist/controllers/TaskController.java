package com.gab.todolist.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gab.todolist.dtos.TaskRecordDto;
import com.gab.todolist.entities.Category;
import com.gab.todolist.entities.Task;
import com.gab.todolist.repositories.CategoryRepository;
import com.gab.todolist.repositories.TaskRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> taskList = repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(taskList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") Long id) {
		Optional<Task> taskO = repository.findById(id);
		if (taskO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(taskO);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable Long categoryId) {
		//var task = new Task();
		//Optional<Category> category = categoryRepository.findById(categoryId);
		
		List<Task> taskList = repository.findTaskByCategory(categoryRepository.findById(categoryId));
		return ResponseEntity.status(HttpStatus.OK).body(taskList);
	}

	@PostMapping
	public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskRecordDto taskRecordDto) {
		var task = new Task();
		BeanUtils.copyProperties(taskRecordDto, task);

		Optional<Category> category = categoryRepository.findById(task.getCategory().getId());
		if (category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}
		task.setCategory(category.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(task));
	}

	/*
	 * @PutMapping("/{id}") public ResponseEntity<Object>
	 * updateTask(@PathVariable(value = "id") Long id,
	 * 
	 * @RequestBody @Valid TaskRecordDto taskRecordDto) { Optional<Task> taskO =
	 * repository.findById(id); if (taskO.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found"); }
	 * 
	 * Optional<Category> category =
	 * categoryRepository.findById(taskO.getCategory().getId()); if
	 * (category.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found"); }
	 * taskO.setCategory(category.get());
	 * 
	 * var task = taskO.get(); BeanUtils.copyProperties(taskRecordDto, task); return
	 * ResponseEntity.status(HttpStatus.OK).body(repository.save(task)); }
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable(value = "id") Long id,
			@RequestBody @Valid TaskRecordDto taskRecordDto) {
		Optional<Task> taskO = repository.findById(id);
		if (taskO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}

		Task task = taskO.get(); // Obtém a instância de Task do Optional<Task>

		Optional<Category> category = categoryRepository.findById(task.getCategory().getId());
		if (category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}

		task.setCategory(category.get()); // Atualiza a categoria da tarefa

		BeanUtils.copyProperties(taskRecordDto, task);

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(task));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateTask(@PathVariable(value = "id") Long id,
			@RequestBody Map<String, Object> updates) {
		Optional<Task> taskO = repository.findById(id);
		if (taskO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}

		Task task = taskO.get();

		Optional<Category> category = categoryRepository.findById(task.getCategory().getId());
		if (category.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		}

		task.setCategory(category.get());

		for (Map.Entry<String, Object> entry : updates.entrySet()) {
			String field = entry.getKey();
			Object value = entry.getValue();

			switch (field) {
			case "completed":
				if (value instanceof Boolean) {
					task.setCompleted((Boolean) value);
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value for 'completed' field");
				}
				break;
			// Add more cases for other fields if needed
			default:
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid field: " + field);
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(task));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id) {
		Optional<Task> taskO = repository.findById(id);
		if (taskO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}
		repository.delete(taskO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
	}

}
