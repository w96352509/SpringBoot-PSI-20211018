package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
	private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
	
	 @RequestMapping("/")
	 public String index(Model model , @RequestParam(name = "customer_id" ,required = false) Long customer_id) {
		 Order order = new Order();
		 model.addAttribute("order",order);
		 model.addAttribute("customers", customerRepository.findAll());
		 model.addAttribute("employees", employeeRepository.findAll());
		 model.addAttribute("orders", orderRepository.findAll());
		 return "order";
	}
	 @RequestMapping("/add")
	 public String add(Order order) {
		 orderRepository.save(order);
		 return"redirect:/order/"; 
	 }
	 
	 @RequestMapping("/edit/{oid}")
	 public String edit(Model model , @PathVariable("oid") Long oid) {
		 Order order = orderRepository.findById(oid).get();
		 model.addAttribute("order",order);
		 model.addAttribute("customers", customerRepository.findAll());
		 model.addAttribute("employees", employeeRepository.findAll());
		 return"order-update";
	 }
	 
	 @RequestMapping("/update/{oid}")
	 public String update(Order order,@PathVariable("") Long oid) {
		 order.setId(oid); // 重要! 不加入會變成新增
		 orderRepository.save(order);
		 return"redirect:/order/";
	 }
	 
	 @RequestMapping("/delete/{oid}")
	 public String delete(@PathVariable("") Long oid) {
		 orderRepository.deleteById(oid);
		 return"redirect:/order/";
	 }
	
}
