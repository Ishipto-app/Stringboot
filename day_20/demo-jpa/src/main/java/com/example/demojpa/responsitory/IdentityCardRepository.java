package com.example.demojpa.responsitory;

import com.example.demojpa.one_to_one.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}