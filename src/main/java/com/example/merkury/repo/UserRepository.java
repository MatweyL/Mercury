package com.example.merkury.repo;

import com.example.merkury.entity.Role;
import com.example.merkury.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findAllByRole(Role role);
}
