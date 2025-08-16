package com.practice.services.impl;

import com.practice.dto.CreateUserDto;
import com.practice.dto.UserDto;
import com.practice.entities.User;
import com.practice.repositories.UserRepository;
import com.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public UserDto updatePartialUserByUserId(Long userId, Map<String, Object> updates) {
        User user = userRepository.findById(userId).orElseThrow();
        Object value;
        for (String field : updates.keySet()) {
            switch (field) {
                case "userName":
                    value = updates.get(field);
                    user.setUserName((String) value);
                    break;
                case "userEmail":
                    value = updates.get(field);
                    user.setUserEmail((String) value);
                    break;
                case "userPassword":
                    value = updates.get(field);
                    user.setUserPassword((String) value);
                    break;
                case "userAddress":
                    value = updates.get(field);
                    user.setUserAddress((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not Supported");
            }
        }
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }
}
