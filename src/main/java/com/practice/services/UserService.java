package com.practice.services;

import com.practice.dto.CreateUserDto;
import com.practice.dto.UserDto;

import java.util.Map;

public interface UserService {

    UserDto createUser(CreateUserDto dto);

    UserDto updatePartialUserByUserId(Long userId, Map<String, Object> updates);
}
