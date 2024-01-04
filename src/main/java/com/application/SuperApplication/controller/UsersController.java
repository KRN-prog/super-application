package com.application.SuperApplication.controller;


import com.application.SuperApplication.model.Users;
import com.application.SuperApplication.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/user/register")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        usersService.createUser(users);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") Integer userId) {
        return usersService.getUserById(userId);
    }

    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<String> modifyUser(@PathVariable("userId") Integer userId, @RequestBody Users users){
        usersService.modifyUser(userId, users);
        return ResponseEntity.ok("Utilisateur modifié");
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId){
        usersService.deleteUser(userId);
        return ResponseEntity.ok("Utilisateur supprimé");
    }
}
