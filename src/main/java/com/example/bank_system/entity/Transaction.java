package com.example.bank_system.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", schema = "public")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Double fee;

    private String location;

    private String type;

    private LocalDateTime transactionDate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(){}

    public Long getId(){
        return id;
    }
     public Double getAmount() {
        return amount;
     }

     public void setAmount(Double amount) {
        this.amount = amount;
     }

     public Double getFee() {
        return fee;
     }

     public void setFee(Double fee) {
        this.fee = fee;
     }

     public String getLocation() {
        return location;
     }

     public void setLocation(String location) {
        this.location = location;
     }

     public String getType() {
        return type;
     }

     public void setType(String type) {
        this.type = type;
     }

     public LocalDateTime getTransactionDate() {
        return transactionDate;
     }

     public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
     }

     public Account getAccount() {
        return account;
     }

     public void setAccount(Account account) {
        this.account = account;
     }
}