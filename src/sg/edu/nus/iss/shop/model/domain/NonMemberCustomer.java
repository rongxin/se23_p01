package sg.edu.nus.iss.shop.model.domain;

import sg.edu.nus.iss.shop.controller.DiscountManager;

public class NonMemberCustomer extends Customer {
	
	private final static String NON_MEMBER_CUSTOMER_ID = "PUBLIC";
	
	public NonMemberCustomer() {
		//super(NonMemberCustomer.NON_MEMBER_CUSTOMER_ID + new Date().getTime() + new Random().nextInt());
		//because public customer id is public data file
		super(NonMemberCustomer.NON_MEMBER_CUSTOMER_ID);
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