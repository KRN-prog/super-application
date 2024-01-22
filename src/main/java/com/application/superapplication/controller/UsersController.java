package com.application.superapplication.controller;


import com.application.superapplication.client.StarWarsClient;
import com.application.superapplication.service.dto.UserRequestDto;
import com.application.superapplication.service.dto.UserResponseDto;
import com.application.superapplication.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private StarWarsClient starWarsClient;

    @PostMapping(value = "/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = usersService.createUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/{userId}")
    public Object createUser(@PathVariable("userId") Long peopleId) {
        return starWarsClient.getPeopleById(peopleId);
    }

    @GetMapping(value = "/users/{userId}")
    public UserResponseDto getUserById(@PathVariable("userId") Integer userId) {
        return usersService.getUserDtoById(userId);
    }

    @GetMapping(value = "/users")
    public List<UserResponseDto> getAllUsers() { return usersService.getAllUser(); }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<String> modifyUser(@PathVariable("userId") Integer userId, @RequestBody UserRequestDto userRequestDto){
        usersService.modifyUser(userId, userRequestDto);
        return ResponseEntity.ok("Utilisateur modifié");
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId){
        usersService.deleteUser(userId);
        return ResponseEntity.ok("Utilisateur supprimé");
    }
}
