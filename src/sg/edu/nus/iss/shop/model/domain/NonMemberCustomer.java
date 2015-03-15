package sg.edu.nus.iss.shop.model.domain;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
			List<Discount> publicDiscountList = DiscountManager.getDiscountManager().getValidPublicDiscountList();
			Iterator<Discount> iter = publicDiscountList.iterator();
			while(iter.hasNext()){
				Discount discount = iter.next();
				if (maxDiscount == null || discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()) {
					maxDiscount = discount;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return maxDiscount;
	}
}