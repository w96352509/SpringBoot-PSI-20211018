package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
     
	 @RequestMapping("/")
	 public String index() {
		 return "inventory";
	}
	
}
