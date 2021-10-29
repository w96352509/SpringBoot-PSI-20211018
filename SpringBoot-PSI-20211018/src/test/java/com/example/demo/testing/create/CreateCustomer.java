package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateCustomer {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void start() {
		Customer c1 = new Customer();
		c1.setName("CC1");
		Customer c2 = new Customer();
		c2.setName("CC2");
		customerRepository.save(c1);
		customerRepository.save(c2);
	}
}
