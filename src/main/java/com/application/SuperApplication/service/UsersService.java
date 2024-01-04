package com.application.SuperApplication.service;

import com.application.SuperApplication.model.Users;
import com.application.SuperApplication.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users users) {
        return userRepository.save(users);
    }

    public Optional<Users> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public Users modifyUser(int userId, Users users) {
        Users user = userRepository.findById(userId).get();
        user.setName(users.getName());
        user.setAge(users.getAge());
        user.setHeight(users.getHeight());
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        Users user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }
}
