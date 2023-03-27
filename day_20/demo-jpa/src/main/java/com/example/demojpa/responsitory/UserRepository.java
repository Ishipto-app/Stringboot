package com.example.demojpa.responsitory;

import com.example.demojpa.one_to_one.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}