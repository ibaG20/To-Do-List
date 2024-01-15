package com.gab.todolist.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gab.todolist.entities.Category;
import com.gab.todolist.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	//@Query("SELECT t FROM Task t WHERE t.category.id = :categoryId")
	List<Task> findTaskByCategory(Optional<Category> category);

}
