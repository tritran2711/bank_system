package com.example.bank_system.controller;

import com.example.bank_system.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/counts")
    public Map<String, Long> getCounts(){
        return statisticsService.getCounts();
    }

    @GetMapping("/transactions/count")
    public Map<String, Long> getCounttransactionByAccount(@RequestParam Long accountId){
        return statisticsService.getCounttransactionByAccount(accountId);
    }

    @GetMapping("/account-classification")
    public Map<String, Long> getAccountClassification(){
        return statisticsService.getAccountClassification();
    }

    @GetMapping("/transactions/count-by-type-all")
    public Map<String, Long> countAllTypes(){
        return statisticsService.countAllTypes();
    }

    @GetMapping("/transactions/count-by-type/account")
    public Map<String, Long> countTypeByAccount(@RequestParam Long accountId){
        return statisticsService.countTypeByAccount(accountId);
    }

    @GetMapping("/customers-by-location")
    public Map<String, Long> getCustomersByLocation(@RequestParam String location){
        return statisticsService.getCustomersByLocation(location);
    }

}
