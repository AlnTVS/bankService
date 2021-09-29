package com.alntvs.service.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionController {

    private RestTemplate restTemplate;

    @Autowired
    public void RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getTransactionsByNumber(String bankAccountNumber) {
        String response;
        try {
            String url = "http://localhost:8090/transaction/getTransactionsByNumber/" + bankAccountNumber;
            response = this.restTemplate.getForObject(url, String.class);
        } catch (RuntimeException e) {
            System.out.println(e);
            return "Сервис транзакций временно недоступен";
        }
        return response;
    }

}
