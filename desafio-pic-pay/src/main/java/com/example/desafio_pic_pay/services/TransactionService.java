package com.example.desafio_pic_pay.services;

import com.example.desafio_pic_pay.dtos.TransactionDTO;
import com.example.desafio_pic_pay.entities.Transaction;
import com.example.desafio_pic_pay.entities.UserType;
import com.example.desafio_pic_pay.repositories.TransactionRepository;
import com.example.desafio_pic_pay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {

        // Validate transaction
        validateTransaction(transactionDTO);
        authorizationService.authorize();

        // Make transaction
        var payer = userService.getUserBytId(transactionDTO.payerId());
        var payee = userService.getUserBytId(transactionDTO.payeeId());

        payer.debit(transactionDTO.value());
        payee.credit(transactionDTO.value());
        var transaction = new Transaction(payee, payer, transactionDTO.value(), LocalDateTime.now());

        // Send notification
//        notificationService.sendNotification(payer, "You have made a transaction");
//        notificationService.sendNotification(payee, "You have received a transaction");

        transactionRepository.save(transaction);
        return transaction;
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    private void validateTransaction(TransactionDTO transactionDTO) throws Exception {
        var payer = userService.getUserBytId(transactionDTO.payerId());
        var payee = userService.getUserBytId(transactionDTO.payeeId());

        if(payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchants can't make transactions");
        }

        if(payer.getBalance().compareTo(transactionDTO.value()) < 0) {
            throw new Exception("Value is greater than payer balance");
        }

        if(payer.getId().equals(payee.getId())) {
            throw new Exception("Can't make transactions to yourself");
        }
    }




}

