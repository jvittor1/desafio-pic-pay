package com.example.desafio_pic_pay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(Long payerId, Long payeeId, BigDecimal value) {
}
