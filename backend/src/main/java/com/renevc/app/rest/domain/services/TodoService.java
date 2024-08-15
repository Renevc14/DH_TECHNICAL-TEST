package com.renevc.app.rest.domain.services;

import java.util.List;

import com.renevc.app.rest.infrastructure.dto.TodoDto;

public interface TodoService {

    TodoDto save(TodoDto todoDto);

    List<TodoDto> findAll();

    TodoDto findById(Long id);

    List<TodoDto> findByCategory(Long categoryId);

    void delete(Long id);

}
