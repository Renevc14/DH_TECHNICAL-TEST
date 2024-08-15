package com.renevc.app.rest.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renevc.app.rest.infrastructure.entities.TodoEntity;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findTodoByCategoryId(Long categoryId);
}
