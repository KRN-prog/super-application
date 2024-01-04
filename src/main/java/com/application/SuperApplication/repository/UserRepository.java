package com.application.SuperApplication.repository;

import com.application.SuperApplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    //Optional<Users> findByNameAgeAndHeight(String userName, int userAge, double UserHeight);
    Optional<Users> findById(int userId);
}
