package com.example.byteblog.service;

import com.example.byteblog.dto.UserDto;
import com.example.byteblog.exception.ResourceNotFoundException;
import com.example.byteblog.model.User;
import com.example.byteblog.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this id : " + userId));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        //1.get
       User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this id : " + userId));
        //2.set
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());
        //3.update
        User updatedUser=userRepo.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        //convert users to userDto
        List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUserById(Long userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with this id : " + userId));
        this.userRepo.delete(user);
    }

    //for mapping user to userDto and vice-versa
    public User dtoToUser(UserDto userDto){

//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        User user=this.modelMapper.map(userDto,User.class);
        return user;

    }

    public UserDto userToDto(User user){

//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;

    }
}
