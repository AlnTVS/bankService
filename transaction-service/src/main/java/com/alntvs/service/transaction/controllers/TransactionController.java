package com.alntvs.service.transaction.controllers;

import com.alntvs.service.transaction.entities.Transaction;
import com.alntvs.service.transaction.repositories.specification.TransactionSpecifications;
import com.alntvs.service.transaction.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TransactionController {
    private TransactionsService transactionsService;

    @Autowired
    public void setTransactionsService(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/getTransactionById/{id}")
    @ResponseBody
    public String findTransactionById(@PathVariable Long id) {
        return transactionsService.findById(id).toString();
    }

    @GetMapping("/getTransactionsByNumber/{number}")
    @ResponseBody
    public String findAllTransactionByNumber(Model model, @PathVariable String number) {
        System.out.println("number - " + number);
        Specification<Transaction> spec = Specification.where(TransactionSpecifications.findAllByBankAccountNumber(number));
        System.out.println(transactionsService.findAllByNumber(spec).toString());
        return transactionsService.findAllByNumber(spec).toString();
    }
}
