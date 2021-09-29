package com.alntvs.service.web.exceptions;

public class BankAccountNotFoundExeption extends RuntimeException {
    public BankAccountNotFoundExeption(String message) {
        super(message);
    }
}
