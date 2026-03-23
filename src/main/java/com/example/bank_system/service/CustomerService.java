package com.example.bank_system.service;
import com.example.bank_system.entity.Customer;
import com.example.bank_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {

        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer old = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if(customer.getName() != null){
            old.setName(customer.getName());
        }

        if(customer.getEmail() != null){
            old.setEmail(customer.getEmail());
        }

        if(customer.getPhone() != null){
            old.setPhone(customer.getPhone());
        }

        if(customer.getAddress() != null){
            old.setAddress(customer.getAddress());
        }

        return customerRepository.save(old);
    }

}

