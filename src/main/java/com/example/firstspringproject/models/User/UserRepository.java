package com.example.firstspringproject.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
