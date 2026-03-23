package com.example.bank_system.service;

import com.example.bank_system.repository.AccountRepository;
import com.example.bank_system.repository.CustomerRepository;
import com.example.bank_system.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public StatisticsService(AccountRepository accountRepository,
                             TransactionRepository transactionRepository,
                             CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public Map<String, Long> getCounts(){

        Map<String, Long> result = new HashMap<>();

        result.put("totalAccounts", accountRepository.count());
        result.put("totalTransactions", transactionRepository.count());

        return result;
    }

    public Map<String, Long> getCounttransactionByAccount(Long accountId){
        Map<String, Long> result = new LinkedHashMap<>();
        result.put("accountId", accountId);
        result.put("transactionCount",
                transactionRepository.countByAccountId(accountId));
        return result;
    }

    public Map<String, Long> getAccountClassification(){
        Map<String, Long> result = new LinkedHashMap<>();
        result.put("highBalanceAccounts",
                accountRepository.countByBalanceGreaterThan(10000.0));

        result.put("mediumBalanceAccounts",
                accountRepository.countByBalanceBetween(1000.0, 10000.0));

        result.put("lowBalanceAccounts",
                accountRepository.countByBalanceLessThan(1000.0));

        return result;
    }

    public Map<String, Long> countAllTypes(){
        Map<String, Long> result = new HashMap<>();
        result.put("DEPOSIT", transactionRepository.countByType("DEPOSIT"));
        result.put("WITHDRAW", transactionRepository.countByType("WITHDRAW"));
        return result;
    }

    public Map<String, Long> countTypeByAccount(Long accountId){

        Map<String, Long> result = new LinkedHashMap<>();
        result.put("AccountId",accountId);
        result.put("DEPOSIT",
                transactionRepository.countByAccountIdAndType(accountId, "DEPOSIT"));

        result.put("WITHDRAW",
                transactionRepository.countByAccountIdAndType(accountId, "WITHDRAW"));

        return result;
    }

    public Map<String, Long> getCustomersByLocation(String location){

        Map<String, Long> result = new HashMap<>();

        long count = customerRepository.countByAddress(location);

        result.put(location, count);

        return result;
    }
}