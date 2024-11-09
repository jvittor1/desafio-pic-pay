package com.example.desafio_pic_pay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payeeId;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payerId;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Transaction(User payee, User payer, BigDecimal value, LocalDateTime now) {
        this.payeeId = payee;
        this.payerId = payer;
        this.value = value;
        this.createdAt = now;
    }
}
