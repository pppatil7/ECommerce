package com.practice.controllers;

import com.practice.dto.CreateUserDto;
import com.practice.dto.UserDto;
import com.practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto dto) {
        UserDto userDto = userService.createUser(dto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> updatePartialUserByUserId(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        UserDto userDto = userService.updatePartialUserByUserId(userId, updates);
        return ResponseEntity.ok(userDto);
    }


}
