package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
     
	 @RequestMapping("/")
	 public String index() {
		 return "purchase";
	}
	
}
