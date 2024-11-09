package com.example.desafio_pic_pay.dtos;

import com.example.desafio_pic_pay.entities.UserType;
import lombok.Getter;

import java.math.BigDecimal;
public record UserDTO(String fullName, String cpf, String email, String password, BigDecimal balance, UserType userType) {

}
