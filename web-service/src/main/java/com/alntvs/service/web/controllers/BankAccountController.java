package com.alntvs.service.web.controllers;

import com.alntvs.service.web.entities.BankAccount;
import com.alntvs.service.web.entities.Client;
import com.alntvs.service.web.exceptions.BankAccountInsufficientFundsFoundExeption;
import com.alntvs.service.web.exceptions.BankAccountNotFoundExeption;
import com.alntvs.service.web.services.BankAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/bankAccount")
public class BankAccountController {

    private BankAccountsService bankAccountsService;
    private TransactionController transactionController;

    @Autowired
    public void setBankAccountsService(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @Autowired
    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    @GetMapping
    public String initTransaction(Model model, @RequestParam(required = false) String bankAccountNumber) {
        BankAccount bankAccount = new BankAccount();
        if (bankAccountNumber != null) {
            bankAccount = bankAccountsService.findByNumber(bankAccountNumber);
        }
        model.addAttribute("bankAccount", bankAccount);
        return "transaction_form";
    }

    @PostMapping
    @ResponseBody
    public String doTransaction(@RequestParam Map<String, String> param) {
        try {
            bankAccountsService.doTransaction(param);
        } catch (BankAccountNotFoundExeption e) {
            return e.toString();
        } catch (BankAccountInsufficientFundsFoundExeption e) {
            return e.toString();
        }
//        return "redirect:/list?bankAccountNumber=" + param.get("bankAccountNumber");
        return transactionController.getTransactionsByNumber(param.get("bankAccountNumber"));
    }

    @GetMapping("/delete")
    public String deleteBankAccountById(@RequestParam String bankAccountNumber) {
        bankAccountsService.deleteByBankAccountNumber(bankAccountNumber);
        return ("redirect:/client/list");
    }

    @GetMapping("/add")
    public String showFromAddNewUser(Model model, @RequestParam Long clientId) {
        model.addAttribute("clientId", clientId);
        return "add_bankAccount";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute BankAccount bankAccount) {
        bankAccountsService.saveOrUpdate(bankAccount);
        return "redirect:/client/list";
    }

}
