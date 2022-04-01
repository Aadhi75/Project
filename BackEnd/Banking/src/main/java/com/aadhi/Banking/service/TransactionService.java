package com.aadhi.Banking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aadhi.Banking.entity.Customer;
import com.aadhi.Banking.entity.Transaction;
import com.aadhi.Banking.repository.CustomerRepository;
import com.aadhi.Banking.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Boolean deposite(Transaction transaction) {

		String accountNum = transaction.getAccountNum();
		Customer customer = customerRepository.findByAccountNo(accountNum);

		if (customer != null) {

			double balance = customer.getBalance();
			balance = balance + transaction.getAmount();
			customer.setBalance(balance);
			customerRepository.save(customer);

			String date = LocalDate.now().toString();
			transaction.setDate(date);

			transactionRepository.save(transaction);
			return true;

		}

		return false;
	}

	public List<Transaction> getAll(String accountNum) {
		return transactionRepository.findAllByAccountNum(accountNum);
		
		
	}

	public Boolean withdraw(Transaction transaction) {
		
		String accountNum = transaction.getAccountNum();
		Customer customer = customerRepository.findByAccountNo(accountNum);

		if (customer != null) {

			double balance = customer.getBalance();
			
			if((balance - transaction.getAmount())<0) {
				return false;
			}
			else {
				balance = balance - transaction.getAmount();
			}
			customer.setBalance(balance);
			customerRepository.save(customer);

			String date = LocalDate.now().toString();
			transaction.setDate(date);

			transactionRepository.save(transaction);
			return true;

		}

		return false;
	}

	public Boolean fundTransfer(List<Transaction> transactions) {
		for (Transaction transaction : transactions) {

			if (transaction.getType().equals("CREDIT")) {

				deposite(transaction);
				return true;
			}
			else if (transaction.getType().equals("DEBIT")) {
				withdraw(transaction);
				return true;
			}
			

		}
		return false;
		
		
	}

}