package com.cydeo.banksimulation.controller;

import com.cydeo.banksimulation.model.Account;
import com.cydeo.banksimulation.model.Transaction;
import com.cydeo.banksimulation.service.AccountService;
import com.cydeo.banksimulation.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Controller
public class TransactionController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }
    @GetMapping("/make-transfer")
    public String retrieveTransactionForm(Model model){
        model.addAttribute("accounts", accountService.listAllAccount());
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("lastTransactionList", transactionService.retrieveLastTransaction());
        return "/transaction/make-transfer";
    }
    @PostMapping("/transfer")
    public String makeTransfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "transfer";
        Account reciever = accountService.retrieveById(transaction.getReceiver());
        Account sender = accountService.retrieveById(transaction.getSender());
        transactionService.makeTransfer(transaction.getAmount(),new Date(),sender,reciever,transaction.getMessage());
        return "redirect:/make-transfer";

    }
    @GetMapping("/transaction/{id}")
    public String transactionDetailById(@PathVariable("id")UUID id, Model model){
        model.addAttribute("transactionList", transactionService.findTransactionById(id));
        return "transaction/transactions";
    }
}
