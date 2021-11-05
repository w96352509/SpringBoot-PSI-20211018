package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/")
	public String index(Model model) {
		Customer customer = new Customer();
		List<Customer> customers = customerRepository.findAll();
		model.addAttribute("customer", customer);
		model.addAttribute("customers", customers);
		return "customers";
	}

	@RequestMapping("/create")
	public String create(Customer customer) {
		customerRepository.save(customer);
		return "redirect:/customers/";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "customer-update";
	}
    @RequestMapping("update/{id}")
	public String update(Customer customer , @PathVariable("id") Long id) {
		customer.setId(id);
    	customerRepository.save(customer);
    	return"redirect:/customers/" ;
	}
    
    @RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
    	return"redirect:/customers/" ;
	}
	
}
