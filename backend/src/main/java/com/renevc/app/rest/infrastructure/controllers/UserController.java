package com.renevc.app.rest.infrastructure.controllers;

import com.renevc.app.rest.domain.services.UserService;
import com.renevc.app.rest.infrastructure.controllers.api.UserApi;
import com.renevc.app.rest.infrastructure.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        System.out.println(id);
        return new ResponseEntity<>(userService.findById(Long.valueOf(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
