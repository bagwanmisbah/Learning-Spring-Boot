package com.misbah.dtodemo.service;

import com.misbah.dtodemo.dto.UserDto;
import java.util.List;
public interface UserService
{
    UserDto createUser(UserDto userDto,String password);
    List<UserDto> getAllUsers();

}
