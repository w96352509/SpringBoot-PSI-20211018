package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.entity.view.Inventory;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Inventory> inventories =productRepository.queryInventory();
		model.addAttribute("products",inventories);
		
		return "inventory";
	}
	
}
