package com.example.bank_system.controller;

import com.example.bank_system.entity.Account;
import com.example.bank_system.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{customerId}")
    public Account createAccount(@PathVariable Long customerId,
                                 @RequestBody Account account){

        return accountService.createAccount(customerId,account);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountByCustomer(@PathVariable Long customerId){
        return accountService.getAccountsByCustomer(customerId);
    }

    @GetMapping("/{customerId}/{accountId}")
    public Account getAccountByCustomerIdAndAccountId(@PathVariable Long customerId,
                                                      @PathVariable Long accountId){
        return accountService.getAccountByCustomerIdAndAccountId(customerId, accountId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    //Level 2
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestParam String status) {
        accountService.updateStatus(id, status);
    }
}
