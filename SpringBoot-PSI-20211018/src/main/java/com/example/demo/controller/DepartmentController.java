package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
     
	 @RequestMapping("/")
	 public String index() {
		 return "department";
	}
	
}
