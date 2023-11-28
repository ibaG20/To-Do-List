package com.gab.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gab.todolist.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
