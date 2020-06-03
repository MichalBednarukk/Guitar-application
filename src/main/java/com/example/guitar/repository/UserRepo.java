package com.example.guitar.repository;

import com.example.guitar.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
}
