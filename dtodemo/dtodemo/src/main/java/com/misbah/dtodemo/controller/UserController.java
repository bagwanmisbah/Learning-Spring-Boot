package com.misbah.dtodemo.controller;

import com.misbah.dtodemo.dto.UserDto;
import com.misbah.dtodemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto, @RequestParam String password)
    {
        return userService.createUser(userDto,password);

    }
    @GetMapping
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }
}

