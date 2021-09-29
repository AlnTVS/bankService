package com.alntvs.service.web.services;

import com.alntvs.common.enums.TransactionStatus;
import com.alntvs.common.models.Message;
import com.alntvs.service.web.entities.Bank;
import com.alntvs.service.web.entities.BankAccount;
import com.alntvs.service.web.exceptions.BankAccountInsufficientFundsFoundExeption;
import com.alntvs.service.web.exceptions.BankAccountNotFoundExeption;
import com.alntvs.service.web.exceptions.ClientNotFoundException;
import com.alntvs.service.web.repositories.BankAccountsRepository;
import com.alntvs.service.web.repositories.BanksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class BanksService {

    private BanksRepository banksRepository;

    @Autowired
    public void setBanksRepository(KafkaProducerService kafkaProducerService) {
        this.banksRepository = banksRepository;
    }

    public Bank findById(Long bankId){
        return banksRepository.findById(bankId).orElseThrow(() -> new ClientNotFoundException("Can't found bank with id = " + bankId));
    }
}
