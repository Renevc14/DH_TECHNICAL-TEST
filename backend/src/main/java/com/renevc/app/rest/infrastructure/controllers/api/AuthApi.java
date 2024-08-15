package com.renevc.app.rest.infrastructure.controllers.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renevc.app.rest.infrastructure.dto.UserDto;

import static com.renevc.app.rest.utils.Constants.APP_ROOT;

public interface AuthApi {


    @PostMapping(value = APP_ROOT + "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> loginUser(
            @RequestBody UserDto user
    );
}
