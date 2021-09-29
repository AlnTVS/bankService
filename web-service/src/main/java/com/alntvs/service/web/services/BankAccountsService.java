package com.alntvs.service.web.services;

import com.alntvs.common.enums.TransactionStatus;
import com.alntvs.common.models.Message;
import com.alntvs.service.web.entities.BankAccount;
import com.alntvs.service.web.entities.Client;
import com.alntvs.service.web.exceptions.BankAccountInsufficientFundsFoundExeption;
import com.alntvs.service.web.exceptions.BankAccountNotFoundExeption;
import com.alntvs.service.web.repositories.BankAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class BankAccountsService {

    private BankAccountsRepository bankAccountsRepository;
    private KafkaProducerService kafkaProducerService;
    private BanksService banksService;

    @Autowired
    public void setBanksService(BanksService banksService) {
        this.banksService = banksService;
    }

    @Autowired
    public void setKafkaService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Autowired
    public void setBankAccountsRepository(BankAccountsRepository bankAccountsRepository) {
        this.bankAccountsRepository = bankAccountsRepository;
    }

    public BankAccount findByNumber(String bankAccountNumber) {
        BankAccount bankAccount = bankAccountsRepository.findOneByNumber(bankAccountNumber);
        if (bankAccount == null) {
            throw new BankAccountNotFoundExeption("Счет с указанным номером не найден. Not Exist: " + bankAccountNumber);
        }
        return bankAccount;
    }

    @Transactional
    public void doTransaction(Map<String, String> param) throws RuntimeException {
        param.put("timestamp", new Timestamp(new Date().getTime()).toString());
        isAvailable(param.get("bankAccountNumber"), Float.valueOf(param.get("summa")));
        findByNumber(param.get("numberRX"));
        BankAccount bankAccount = bankAccountsRepository.findOneByNumber(param.get("bankAccountNumber"));
        bankAccount.setHoldBalance((bankAccount.getHoldBalance() + Float.valueOf(param.get("summa"))));
        kafkaProducerService.sendTransaction(new Message(param, TransactionStatus.IN_PROGRESS));
    }

    public void isAvailable(String bankAccountNumber, Float summa) {
        BankAccount bankAccount = findByNumber(bankAccountNumber);
        if (summa > bankAccount.getBalance() - bankAccount.getHoldBalance()) {
            throw new BankAccountInsufficientFundsFoundExeption("Недостаточно средств на счете. Insufficient funds in the account: " + bankAccountNumber);
        }
    }

    public BankAccount saveOrUpdate(BankAccount bankAccount) {
//        bankAccount.setBank(banksService.findById();
        return bankAccountsRepository.save(bankAccount);
    }

    @Transactional
    public void commitTransaction(Map<String, String> transactionMap, TransactionStatus status) {
        String bankAccountNumber = transactionMap.get("bankAccountNumber");
        String numberRX = transactionMap.get("numberRX");
        Float summa = Float.valueOf(transactionMap.get("summa"));
        if (status.equals(TransactionStatus.OK)) {
            findByNumber(bankAccountNumber).setHoldBalance(findByNumber(bankAccountNumber).getHoldBalance() - summa);
            findByNumber(bankAccountNumber).setBalance(findByNumber(bankAccountNumber).getBalance() - summa);
            findByNumber(numberRX).setBalance(findByNumber(numberRX).getBalance() + summa);
        } else{
            findByNumber(bankAccountNumber).setHoldBalance(findByNumber(bankAccountNumber).getHoldBalance() - summa);
//            findByNumber(bankAccountNumber).setBalance(findByNumber(bankAccountNumber).getBalance() - summa);
//            findByNumber(numberRX).setBalance(findByNumber(numberRX).getBalance() + summa);
        }
    }

    @Transactional
    public void deleteByBankAccountNumber(String bankAccountNumber) {
        bankAccountsRepository.delete(findByNumber(bankAccountNumber));
    }

    @Transactional
    public void deleteById(Long bankAccountId) {
        bankAccountsRepository.deleteById(bankAccountId);
    }
}
