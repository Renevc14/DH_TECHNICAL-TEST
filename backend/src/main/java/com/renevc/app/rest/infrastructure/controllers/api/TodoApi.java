package com.renevc.app.rest.infrastructure.controllers.api;

import jakarta.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renevc.app.rest.infrastructure.dto.TodoDto;

import java.util.List;

import static com.renevc.app.rest.utils.Constants.APP_ROOT;


public interface TodoApi {

    @PostMapping(value = APP_ROOT + "/todos/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> createTodo(
            @RequestBody TodoDto todoDto);

    @PatchMapping(value = APP_ROOT + "/todos/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> updateTodo(
            @RequestBody TodoDto user);

    @GetMapping(value = APP_ROOT + "/todos/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TodoDto>> getAllTodos();

    @GetMapping(value = APP_ROOT + "/todos/{todoId:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TodoDto> getTodo(
            @PathParam(value = "todoId") Long todoId
    );

    @DeleteMapping(value = APP_ROOT + "/todos/delete/{id}")
    ResponseEntity deleteTodo(
            @PathParam("id")  Long id
    );
}
