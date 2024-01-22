package com.application.superapplication.service.mapper;

import com.application.superapplication.service.dto.UserRequestDto;
import com.application.superapplication.service.dto.UserResponseDto;
import com.application.superapplication.repository.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(UserEntity user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setHeight(user.getHeight());
        return userResponseDto;
    }


    public UserEntity toEntity(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity();
        user.setName(userRequestDto.getName());
        user.setAge(userRequestDto.getAge());
        user.setHeight(userRequestDto.getHeight());
        return user;
    }

    public UserEntity updateEntity(UserRequestDto userRequestDto, UserEntity userToUpdate) {
        userToUpdate.setName(userRequestDto.getName());
        userToUpdate.setAge(userRequestDto.getAge());
        userToUpdate.setHeight(userRequestDto.getHeight());
        return userToUpdate;
    }
}
