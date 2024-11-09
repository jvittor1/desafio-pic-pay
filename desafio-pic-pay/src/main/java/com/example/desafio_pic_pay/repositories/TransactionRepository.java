package com.example.desafio_pic_pay.repositories;

import com.example.desafio_pic_pay.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
