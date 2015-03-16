package sg.edu.nus.iss.shop.model.domain;

import java.util.Date;
import java.util.Random;

import sg.edu.nus.iss.shop.controller.DiscountManager;

public class NonMemberCustomer extends Customer {
	private final static String NON_MEMBER_CUSTOMER_ID = "NON_MEMBER_CUSTOMER";

	public NonMemberCustomer() {
		super(NonMemberCustomer.NON_MEMBER_CUSTOMER_ID + new Date().getTime() + new Random().nextInt());
	}
	
	public boolean isFirstPurchase(){
		return true;
	}
	
	public Discount getMaxDiscount() {
		Discount maxDiscount = null;
		
		try {
			maxDiscount = DiscountManager.getDiscountManager().getMaxValidPublicDiscount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxDiscount;
	}
}