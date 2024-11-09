package com.example.desafio_pic_pay.controllers;

import com.example.desafio_pic_pay.dtos.TransactionDTO;
import com.example.desafio_pic_pay.entities.Transaction;
import com.example.desafio_pic_pay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
