package com.practice.services;

import com.practice.dto.CreateUserDto;
import com.practice.dto.UserDto;

public interface UserService {

    UserDto createUser(CreateUserDto dto);
}
