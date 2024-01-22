package com.application.superapplication.service;

import com.application.superapplication.service.dto.UserRequestDto;
import com.application.superapplication.service.dto.UserResponseDto;
import com.application.superapplication.repository.model.UserEntity;
import com.application.superapplication.service.mapper.UserMapper;
import com.application.superapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    public static final String ERROR_MESSAGE_USER_NOT_FOUND = "Utilisateur non trouvé.";
    private final UserRepository userRepository;


    private final UserMapper userMapper;

    public UsersService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userToSave = userMapper.toEntity(userRequestDto);
        UserEntity saveUser = userRepository.save(userToSave);

        return userMapper.toDto(saveUser);
    }

    public UserResponseDto getUserDtoById(int userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE_USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    public List<UserResponseDto> getAllUser() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserEntity modifyUser(int userId, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE_USER_NOT_FOUND));
        userMapper.updateEntity(userRequestDto, user);
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE_USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
