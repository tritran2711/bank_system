package com.example.bank_system.repository;

import com.example.bank_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByAccountId(Long accountId);
    long countByType(String type);
    long countByAccountId(Long accountId);
    long countByAccountIdAndType(Long accountId, String type);
}
