package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.view.Inventory;
import com.example.demo.repository.ProductRepository;

@Component
public class InventoryValidator2 implements Validator {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PurchaseItem purchaseItem =(PurchaseItem)target;
		if (purchaseItem.getAmount()==null || purchaseItem.getAmount()==0) {
			errors.rejectValue("amount", "purchaseItem.amount.required", "請輸入數量");
		}else {
			Inventory inventory = productRepository.findInventoryById(purchaseItem.getProduct().getId());
			int remaining = 0;
			try {
				// amount1 : 進貨/採購數量
				// amount2 : 銷售/訂單數量
				if(inventory.getAmount1() == null) {
					// 沒有進貨資料
					errors.rejectValue("amount", "inventory.amount.null", "此商品無進貨資料");
					return;
				}
				if(inventory.getAmount2() == null) {
					remaining = inventory.getAmount1();
				} else {
					//剩餘數量
					remaining = inventory.getAmount1() - inventory.getAmount2();
				}
				if(purchaseItem.getAmount() > remaining) {
					//採購數大於供應商傭有
					errors.rejectValue("amount", "inventory.amount.insufficient", "此商品庫存不足（目前庫存：" + remaining + "）");
				}
				
			} catch (Exception e) {
				errors.rejectValue("amount", "inventory.amount.error", "其他錯誤：" + e);
			}
			
		}
		}
	}
	
	
	

