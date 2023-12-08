package com.example.byteblog.repository;

import com.example.byteblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
