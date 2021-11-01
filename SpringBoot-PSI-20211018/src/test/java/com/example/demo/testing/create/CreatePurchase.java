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
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreatePurchase {

	@Autowired
	SupplierRepository supplierRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	PurchaseItemRepository purchaseItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	void start() {
		// 資料預備
		Supplier s1 = supplierRepository.findById(1L).get();// 購買客戶
		Employee e2 = employeeRepository.findById(2L).get();// 服務員工
		Product p1 = productRepository.findById(1L).get();// 購買商品
		Product p2 = productRepository.findById(2L).get();

		// 建立採購單
		Purchase purchase = new Purchase();
		purchase.setDate(new Date());

		// 配置採購單的關聯(看圖)
		purchase.setSupplier(s1);
		purchase.setEmployee(e2);
		// --------------------

		// 建立採購單細目1
	PurchaseItem item1 = new PurchaseItem();
		item1.setAmount(500);
		// 配置採購單細目關聯
		item1.setPurchase(purchase);
		item1.setProduct(p1);

		// 建立採購單細目1
		PurchaseItem item2 = new PurchaseItem();
			item2.setAmount(700);
			// 配置採購單細目關聯
			item2.setPurchase(purchase);
			item2.setProduct(p2);
		
		//保存
	    purchaseRepository.save(purchase);
		purchaseItemRepository.save(item1);
		purchaseItemRepository.save(item2);

	}

}
