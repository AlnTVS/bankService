package com.alntvs.service.web.exceptions;

public class BankNotFoundExeption extends RuntimeException {
    public BankNotFoundExeption(String message) {
        super(message);
    }
}
