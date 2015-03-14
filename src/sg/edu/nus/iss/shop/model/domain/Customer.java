package sg.edu.nus.iss.shop.model.domain;

import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.shop.controller.DiscountManager;

public class Customer {
	private String id;

	public Customer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Customer customer = (Customer) object;
		return this.getId().equals(customer.getId());
	}

	public Discount getMaxDiscount(){
		Discount maxDiscount = null;
		
		List<Discount> discountList = DiscountManager.getDiscountManager().getAllDiscounts();
		Iterator<Discount> iter = discountList.iterator();
		while(iter.hasNext()){
			Discount discount = iter.next();
			if (discount == null || discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()){
				maxDiscount = discount;
			}
		}
		
		return maxDiscount;
	}
}
