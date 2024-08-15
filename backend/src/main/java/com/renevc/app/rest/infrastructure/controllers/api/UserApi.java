package com.renevc.app.rest.infrastructure.controllers.api;

import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renevc.app.rest.infrastructure.dto.UserDto;

import java.util.List;

import static com.renevc.app.rest.utils.Constants.APP_ROOT;

public interface UserApi {

    @PostMapping(value = APP_ROOT + "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> createUser(
            @RequestBody UserDto user);

    @PatchMapping(value = APP_ROOT + "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> updateUser(
            Long id,
            @RequestBody UserDto user);

    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping(value = APP_ROOT + "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(
            @PathVariable("id")Long id
    );

    @DeleteMapping(value = APP_ROOT + "/users/delete/{id}")
    ResponseEntity<UserDto> deleteUser(
            @PathVariable("id")Long id
    );
}
