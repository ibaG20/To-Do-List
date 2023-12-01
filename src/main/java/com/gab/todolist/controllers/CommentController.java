package com.gab.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gab.todolist.dtos.CommentRecordDto;
import com.gab.todolist.entities.Comment;
import com.gab.todolist.entities.Task;
import com.gab.todolist.repositories.CommentRepository;
import com.gab.todolist.repositories.TaskRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
	
	@Autowired
	private CommentRepository repository;
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> commentList = repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(commentList);
	}
	
	@PostMapping
	public ResponseEntity<Object> saveComment(@RequestBody @Valid CommentRecordDto commentRecordDto){
		var comment = new Comment();
		BeanUtils.copyProperties(commentRecordDto, comment);
		
		Optional<Task> task = taskRepository.findById(comment.getTask().getId());
		if(task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}
		comment.setTask(task.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comment));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateComment(@PathVariable(value = "id") Long id, @RequestBody @Valid CommentRecordDto commentRecordDto){
		Optional<Comment> commentO = repository.findById(id);
		if(commentO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
		}
		
		Comment comment = commentO.get();
		Optional<Task> task = taskRepository.findById(comment.getTask().getId());
		if(task.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
		}
		comment.setTask(task.get());
		BeanUtils.copyProperties(commentRecordDto, comment);
		
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(comment));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteComment(@PathVariable(value = "id") Long id){
		Optional<Comment> commentO = repository.findById(id);
		if(commentO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
		}
		repository.delete(commentO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
	}

}
