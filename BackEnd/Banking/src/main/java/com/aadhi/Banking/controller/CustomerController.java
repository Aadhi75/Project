package com.aadhi.Banking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aadhi.Banking.entity.Customer;
import com.aadhi.Banking.entity.Login;
import com.aadhi.Banking.entity.Transaction;
import com.aadhi.Banking.service.CustomerService;
import com.aadhi.Banking.service.TransactionService;

@RestController

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/customer")
	public Customer save(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping("/customer")
	public Customer update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}

	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable Long id) {
		customerService.delete(id);

	}

	@GetMapping("/customer/{id}")
	public Optional<Customer> getOne(@PathVariable Long id) {
		return customerService.getOne(id);
	}

	@GetMapping("/customer")
	public List<Customer> getAll() {
		return customerService.getAll();
	}

	@PostMapping("/login")
	public Customer authenticate(@RequestBody Login login) {
		return customerService.authenticate(login);
	}
	
	@PostMapping("/deposite")
	public Boolean deposite(@RequestBody Transaction transaction) {
		return transactionService.deposite(transaction);
	}
	
	@PostMapping("/withdraw")
	public Boolean withdraw(@RequestBody Transaction transaction) {
		return transactionService.withdraw(transaction);
	}
	
	@PostMapping("/fundTransfers")
	public Boolean fundTransfer(@RequestBody List<Transaction> transactions) {
		return transactionService.fundTransfer(transactions);
	}
	
	
	
	@GetMapping("/transaction/{accountNum}")
	public List<Transaction> getAllTransactions(@PathVariable String accountNum) {
		return transactionService.getAll(accountNum);
	}

}