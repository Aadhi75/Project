package com.aadhi.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aadhi.Banking.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByEmailAndPassword(String email, String password);

	Customer findByAccountNo(String accountNum);

}