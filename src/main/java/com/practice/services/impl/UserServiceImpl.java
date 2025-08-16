package com.practice.services.impl;

import com.practice.dto.CreateUserDto;
import com.practice.dto.UserDto;
import com.practice.entities.User;
import com.practice.repositories.UserRepository;
import com.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(CreateUserDto dto) {
        User user = modelMapper.map(dto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
