package com.example.autogaragebe.repository;


import com.example.autogaragebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
