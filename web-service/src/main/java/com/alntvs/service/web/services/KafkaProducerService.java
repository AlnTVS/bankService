package com.alntvs.service.web.services;

import com.alntvs.common.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(Message msg) {
        kafkaTemplate.send("TransactionA",msg);
        System.out.println("Send msg to TransactionQ6: " + msg);
        return;
    }

}
