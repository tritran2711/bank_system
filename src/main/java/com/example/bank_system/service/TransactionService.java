package com.example.bank_system.service;
import com.example.bank_system.entity.Account;
import com.example.bank_system.entity.Transaction;
import com.example.bank_system.repository.AccountRepository;
import com.example.bank_system.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction deposit(Long accountId, Double amount, String loaction){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // check status
        if ("BLOCKED".equals(account.getStatus()) ||
                "CLOSED".equals(account.getStatus())) {
            throw new RuntimeException("Account is not active");
        }

        if(amount > account.getTransactionLimit()){
            throw new RuntimeException("Giao dịch vượt quá hạn mức");
        }

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setLocation(loaction);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        if(amount > 10000){
            transaction.setFee(amount * 0.01); // phí 1%
        } else {
            transaction.setFee(0.0);
        }
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccount(account);

        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, Double amount, String location){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // check status
        if ("BLOCKED".equals(account.getStatus()) ||
                "CLOSED".equals(account.getStatus())) {
            throw new RuntimeException("Account is not active");
        }

        if(amount > account.getTransactionLimit()){
            throw new RuntimeException("Giao dịch vượt quá hạn mức");
        }

        if(account.getBalance() < amount){
            throw new RuntimeException("Số dư không đủ");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setLocation(location);
        transaction.setAmount(amount);
        transaction.setType("WITHDRAW");
        if(amount > 10000){
            transaction.setFee(amount * 0.01); // phí 1%
        } else {
            transaction.setFee(0.0);
        }
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccount(account);

        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountId){
        return transactionRepository.findByAccountId(accountId);
    }

    public void deleteTransaction(Long id){

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transactionRepository.delete(transaction);
    }


}
