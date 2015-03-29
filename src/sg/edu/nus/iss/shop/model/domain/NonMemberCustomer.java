package sg.edu.nus.iss.shop.model.domain;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.util.Logger;

public class NonMemberCustomer extends Customer {
	private final static String NON_MEMBER_CUSTOMER_ID = "PUBLIC";
	private Logger log = Logger.getLog();
	
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
//			e.printStackTrace();
			log.log(e.toString());
		}
		
		return maxDiscount;
	}
}