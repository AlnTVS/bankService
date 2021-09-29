package com.alntvs.service.web.exceptions;

public class BankAccountInsufficientFundsFoundExeption extends RuntimeException {
    public BankAccountInsufficientFundsFoundExeption(String message) {
        super(message);
    }
}
