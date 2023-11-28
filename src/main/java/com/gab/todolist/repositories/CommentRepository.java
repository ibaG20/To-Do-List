package com.gab.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gab.todolist.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
