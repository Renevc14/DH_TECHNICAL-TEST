package com.renevc.app.rest.domain.services;

import java.util.List;

import com.renevc.app.rest.infrastructure.dto.UserDto;

public interface UserService {

    UserDto save(UserDto user);

    List<UserDto> findAll();

    UserDto findById(Long id);

    void delete(Long id);

    UserDto login(UserDto user);
}
