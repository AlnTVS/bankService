package com.alntvs.service.transaction.services;
import com.alntvs.common.enums.TransactionStatus;
import com.alntvs.common.models.Message;
import com.alntvs.service.transaction.entities.Transaction;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaService {
    private KafkaTemplate<String, Message> kafkaTemplate;
    private TransactionsService transactionsService;

    @Autowired
    public void setTransactionsService(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(id = "TransactionService", topics = "TransactionA")
    public void transactionConsumer(Message msg){
        System.out.println("Get message from TransactionQ6: " + msg.toString());
        Transaction transaction = new Transaction();
        Map<String,String> msgMap = msg.getMessage();
        transaction.setCardNumTX(msgMap.get("bankAccountNumber"));
        transaction.setCardNumRX(msgMap.get("numberRX"));
        transaction.setSumOfTransaction(Integer.valueOf(msgMap.get("summa")));
        transaction.setDateOfTransaction(Timestamp.valueOf(msgMap.get("timestamp")));
        transactionsService.addTransaction(transaction);
        sendTransaction(new Message(msgMap, TransactionStatus.OK));
        return;
    }

    public void sendTransaction(Message msg) {
        kafkaTemplate.send("TransactionB",msg);
        System.out.println("Send msg to TransactionQ5: " + msg.toString());
        return;
    }
}
