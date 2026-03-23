package com.example.bank_system.repository;
import com.example.bank_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerID);
    long countByBalanceGreaterThan(Double amount);

    long countByBalanceBetween(Double min, Double max);

    long countByBalanceLessThan(Double amount);

    Optional<Account> findByIdAndCustomerId(Long id, Long customerId);

}