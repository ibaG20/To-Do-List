package com.gab.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gab.todolist.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
