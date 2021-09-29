package com.alntvs.service.web.services;

import com.alntvs.common.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    BankAccountsService bankAccountsService;

    @Autowired
    public void setBankAccountsService(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @KafkaListener(id = "WebService", topics = "TransactionB")
    public void webConsumer(Message msg) {
        System.out.println("Get message from TransactionQ5: " + msg.toString());
        bankAccountsService.commitTransaction(msg.getMessage(),msg.getStatus());
    }
}
