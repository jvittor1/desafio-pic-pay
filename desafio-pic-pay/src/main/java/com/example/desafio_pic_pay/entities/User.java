package com.example.desafio_pic_pay.entities;

import com.example.desafio_pic_pay.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name= "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private  UserType userType;

    public User(String fullName, String cpf, String email, String password, BigDecimal balance, UserType userType) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.userType = userType;
    }


    public void update(UserDTO userDTO) {
        this.fullName = userDTO.fullName();
        this.cpf = userDTO.cpf();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }


    public void debit(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }


}
