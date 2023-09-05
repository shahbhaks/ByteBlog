package com.example.byteblog.service;

import com.example.byteblog.dto.UserDto;
import com.example.byteblog.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto, Long userId);
    void deleteUserById(Long userId);




}
