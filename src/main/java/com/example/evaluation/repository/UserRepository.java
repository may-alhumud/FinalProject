package com.example.evaluation.repository;


import com.example.evaluation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String name);
}
