package com.renevc.app.rest.infrastructure.services;

import com.renevc.app.rest.domain.services.TodoService;
import com.renevc.app.rest.exception.EntityNotFoundException;
import com.renevc.app.rest.exception.ErrorCodes;
import com.renevc.app.rest.exception.InvalidEntityException;
import com.renevc.app.rest.infrastructure.dto.CategoryDto;
import com.renevc.app.rest.infrastructure.dto.TodoDto;
import com.renevc.app.rest.infrastructure.dto.UserDto;
import com.renevc.app.rest.infrastructure.entities.CategoryEntity;
import com.renevc.app.rest.infrastructure.entities.TodoEntity;
import com.renevc.app.rest.infrastructure.repositories.CategoryRepository;
import com.renevc.app.rest.infrastructure.repositories.TodoRepository;
import com.renevc.app.rest.infrastructure.validators.TodoValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public TodoDto save(TodoDto todoDto) {
    List<String> errors = TodoValidator.validateTodo(todoDto);
        if (!errors.isEmpty()) {
            log.error("Todo is not valid {}", todoDto);
            throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }
        return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long id) {
        if (id == null) {
            log.error("User id is null");
            return null;
        }
        final Long categoryId = categoryRepository.findCategoryByTodoId(id);
        CategoryEntity category = new CategoryEntity();
        category.setId(categoryId);

        final Optional<TodoEntity> todo = todoRepository.findById(id);
        todo.ifPresent(value -> value.setCategory(category));

        final TodoDto todoDto = TodoDto.fromEntity(todo.get());
        CategoryDto categoryDto = CategoryDto.fromEntity(category);
        todoDto.setCategory(categoryDto);

        return Optional.of(todoDto).
                orElseThrow(() -> new EntityNotFoundException("No Todo found with ID = " + id, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<TodoDto> findByCategory(Long categoryId) {
        return todoRepository.findTodoByCategoryId(categoryId).stream()
                .map(TodoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Todo id is null");
            return;
        }
        todoRepository.deleteById(id);
    }
}
