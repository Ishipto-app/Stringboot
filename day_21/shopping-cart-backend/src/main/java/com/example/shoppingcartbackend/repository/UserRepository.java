package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.entity.User;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
