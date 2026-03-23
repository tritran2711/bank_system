package com.example.bank_system.controller;
import com.example.bank_system.entity.Transaction;
import com.example.bank_system.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestParam Long accountId,
                               @RequestParam Double amount,
                               @RequestParam String location){

        return transactionService.deposit(accountId,amount,location);
    }

    @PostMapping("/withdraw")
    public Transaction withdraw(@RequestParam Long accountId,
                                @RequestParam Double amount,
                                @RequestParam String location){

        return transactionService.withdraw(accountId,amount,location);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId){
        return transactionService.getTransactions(accountId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }


}
