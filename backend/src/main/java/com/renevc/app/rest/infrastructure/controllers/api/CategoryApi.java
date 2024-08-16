package com.renevc.app.rest.infrastructure.controllers.api;

import jakarta.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renevc.app.rest.infrastructure.dto.CategoryDto;
import com.renevc.app.rest.infrastructure.dto.TodoDto;

import java.util.List;

import static com.renevc.app.rest.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> createCategory(
           @RequestBody CategoryDto categoryDto);

    @PatchMapping(value = APP_ROOT + "/categories/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto user);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CategoryDto>> getAllCategories();

    @GetMapping(value = APP_ROOT + "/categories/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(
            @PathVariable("id") Long id
    );

    @GetMapping(value = APP_ROOT + "/categories/todos/today", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday();

    @GetMapping(value = APP_ROOT + "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    ResponseEntity<CategoryDto> getCategory(
            @PathVariable("id") Long id
    );

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id}")
    ResponseEntity<CategoryDto> deleteCategory(
            @PathVariable("id") Long id
    );
}