package com.misbah.dtodemo.mapper;

import com.misbah.dtodemo.dto.UserDto;
import com.misbah.dtodemo.entity.User;

public class UserMapper {
    public static UserDto toDto(User user)
    {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();

    }

    public static User toEntity(UserDto userDto,String password)
    {
        return User
                .builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(password)
                .build();
    }
}
