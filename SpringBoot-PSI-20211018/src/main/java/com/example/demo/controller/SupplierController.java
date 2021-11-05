package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    
	@Autowired
	SupplierRepository supplierRepository;
	
	@RequestMapping("/")
	public String index(Model model ) {
	Supplier supplier = new Supplier();
	List<Supplier> suppliers = supplierRepository.findAll();
	model.addAttribute("supplier",supplier);	
	model.addAttribute("suppliers",suppliers);	
	return "supplier" ;
	}
	
	@RequestMapping("/create")
	public String create(Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model , @PathVariable("id") Long id) {
	model.addAttribute("supplier", supplierRepository.findById(id).get());
	return "supplier-update";	
	}
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id , Supplier supplier) {
	    supplier.setId(id);
	    supplierRepository.save(supplier);
		return "redirect:/supplier/";	
	}
	@RequestMapping("/delete/{id}")
	public String delete(Model model , @PathVariable("id") Long id) {
	supplierRepository.deleteById(id);
	return "redirect:/supplier/";	
	}
}
