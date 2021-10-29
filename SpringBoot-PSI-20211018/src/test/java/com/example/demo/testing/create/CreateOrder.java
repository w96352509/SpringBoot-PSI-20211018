package com.example.demo.testing.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateOrder {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	void start() {
		// 資料預備
		Customer c1 = customerRepository.findById(1L).get();// 購買客戶
		Employee e1 = employeeRepository.findById(2L).get();// 服務員工
		Product p1 = productRepository.findById(1L).get();// 購買商品
		Product p2 = productRepository.findById(2L).get();

		// 建立訂單
		Order order = new Order();
		order.setDate(new Date());

		// 配置訂單的關聯(看圖)
		order.setCustomer(c1);
		order.setEmployee(e1);
		// --------------------

		// 建立訂單細目1
		OrderItem item1 = new OrderItem();
		item1.setAmount(5);
		// 配置訂單細目關聯
		item1.setOrder(order);
		item1.setProduct(p1);

		// 建立訂單細目2
		OrderItem item2 = new OrderItem();
		item2.setAmount(5);
		// 配置訂單細目關聯
		item2.setOrder(order);
		item2.setProduct(p2);
		
		//保存
		orderRepository.save(order);
		orderItemRepository.save(item1);
		orderItemRepository.save(item2);

	}

}
