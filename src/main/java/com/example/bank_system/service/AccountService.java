package com.example.bank_system.service;
import com.example.bank_system.entity.Account;
import com.example.bank_system.entity.Customer;
import com.example.bank_system.repository.AccountRepository;
import com.example.bank_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import com.example.bank_system.entity.AccountStatusHistory;
import com.example.bank_system.repository.AccountStatusHistoryRepository;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountStatusHistoryRepository historyRepository;

    public AccountService(AccountRepository accountRepository,
                          CustomerRepository customerRepository,
                          AccountStatusHistoryRepository historyRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.historyRepository = historyRepository;
    }

    public Account createAccount(Long customerId, Account account){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        account.setCustomer(customer);
        if(account.getTransactionLimit() == null){
            account.setTransactionLimit(50000.0);
        }
        account.setOpenDate(LocalDate.now());
        account.setBalance(0.0);
        account.setStatus("ACTIVE");

        return accountRepository.save(account);
    }

    public List<Account> getAccountsByCustomer(Long customerId){
        return accountRepository.findByCustomerId(customerId);
    }

    public Account getAccountByCustomerIdAndAccountId(Long customerId, Long accountId){
        return accountRepository.findByIdAndCustomerId(accountId, customerId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    // ===== LEVEL 2: UPDATE STATUS =====
    public void updateStatus(Long accountId, String newStatus) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setStatus(newStatus);
        accountRepository.save(account);

        AccountStatusHistory history = new AccountStatusHistory();
        history.setAccountId(accountId);
        history.setStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());

        historyRepository.save(history);
    }
}
