package com.alntvs.service.transaction.services;

import com.alntvs.common.enums.TransactionStatus;
import com.alntvs.common.models.Message;
import com.alntvs.service.transaction.entities.Transaction;
import com.alntvs.service.transaction.exceptions.TransactionNotFoundException;
import com.alntvs.service.transaction.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionsService {
    private TransactionsRepository transactionsRepository;

    @Autowired
    public void setTransactionsRepository(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public void addTransaction(Transaction transaction) {
        transactionsRepository.save(transaction);
        return;
    }

    public List<Transaction> findAllByNumber(Specification<Transaction> spec) {
        return transactionsRepository.findAll(spec);
    }

    public Transaction findById(Long id){
        return transactionsRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Can't found transaction with id = " + id));
    }
}
