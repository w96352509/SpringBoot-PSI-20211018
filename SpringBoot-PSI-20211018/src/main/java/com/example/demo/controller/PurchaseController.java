package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.validator.InventoryValidator;
import com.example.demo.validator.InventoryValidator2;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
     
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	@Autowired
	private ProductRepository productRepository ;
	@Autowired
	private InventoryValidator2 inventoryValidator2;
	
	 @RequestMapping("/")
	 public String index(Model model
			 ,@RequestParam(name = "supplier_id" , required = false) Long supplier_id) {
         Purchase purchase = new Purchase();
         if (supplier_id == null) {
           model.addAttribute("purchases",purchaseRepository.findAll());
		}else {
			Supplier supplier = supplierRepository.getById(supplier_id);
			purchase.setSupplier(supplier);
			model.addAttribute("purchases",purchaseRepository.findBySupplier(supplier));
		}
		 model.addAttribute("purchase",purchase);
		 model.addAttribute("suppliers", supplierRepository.findAll());
		 model.addAttribute("employees", employeeRepository.findAll());
		 return "purchase";
	 }
	
	 @RequestMapping("/add")
	 public String add(Purchase purchase) {
		 purchaseRepository.save(purchase);
		 return "redirect:/purchase/";
	 }
	 
	 @RequestMapping("/delete/{id}")
	 public String delete(@PathVariable("id") Long id) {
		 purchaseRepository.deleteById(id);
		 return "redirect:/purchase/";
	 }
	 
	 @RequestMapping("/edit/{id}")
	 public String edit(Model model,@PathVariable("id") Long id) {
		 Purchase purchase=purchaseRepository.findById(id).get();
		 model.addAttribute("purchase",purchase);
		 model.addAttribute("suppliers", supplierRepository.findAll());
		 model.addAttribute("employees", employeeRepository.findAll());
		 return "purchase-update";
	 }
	 @RequestMapping("/update/{id}")
	 public String update(Purchase purchase,@PathVariable("id") Long id) {
		 purchase.setId(id);
		 purchaseRepository.save(purchase);
		 return "redirect:/purchase/"; 
	 }
//-----------------------------------------------------------------------------	 
   	 @RequestMapping("{oid}/view/item")
	 public String viewItem(Model model ,@PathVariable("oid") Long oid) {
		 PurchaseItem purchaseItem = new PurchaseItem();
   		 Purchase purchase =purchaseRepository.findById(oid).get();
   		 model.addAttribute("purchase", purchase);
   		 model.addAttribute("purchaseItem", purchaseItem);
   		 model.addAttribute("products", productRepository.findAll());
   		 return "purchaseItem";
	 }
   	 @RequestMapping("{oid}/add/item")
   	 public String addItem(PurchaseItem purchaseItem,Model model,BindingResult result,@PathVariable("oid") Long oid) {
   	    // 驗證資料
   		    inventoryValidator2.validate(purchaseItem, result);
   		    if (result.hasErrors()) {
   		    	Purchase purchase =purchaseRepository.findById(oid).get();
   	 			model.addAttribute("purchase", purchase);
   	 			model.addAttribute("purchaseItem", purchaseItem);
   	 			model.addAttribute("products", productRepository.findAll());	
			    return "purchaseItem";
   		    }
   		    Purchase purchase =purchaseRepository.findById(oid).get();
 			purchaseItem.setPurchase(purchase);
 			purchaseItemRepository.save(purchaseItem);
 		return "redirect:/purchase/" + oid + "/view/item";
 	  }
   	@RequestMapping("/{oid}/edit/item/{iid}")
	public String editItem(Model model, @PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		Purchase purchase = purchaseRepository.findById(oid).get();
		PurchaseItem purchaseItem = purchaseItemRepository.findById(iid).get();
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("products", productRepository.findAll());
		return "purchaseitem";
	}
	
	@RequestMapping("/{oid}/delete/item/{iid}")
	public String deleteItem(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		purchaseItemRepository.deleteById(iid);
		return "redirect:/purchase/" + oid + "/view/item";
	}
   	 }





	 
	 


