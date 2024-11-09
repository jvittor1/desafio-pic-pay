package com.example.desafio_pic_pay.repositories;

import com.example.desafio_pic_pay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
}
