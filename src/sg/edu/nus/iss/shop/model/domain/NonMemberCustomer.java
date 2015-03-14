package sg.edu.nus.iss.shop.model.domain;

import java.util.Date;
import java.util.Random;

public class NonMemberCustomer extends Customer {
	private final static String NON_MEMBER_CUSTOMER_ID = "NON_MEMBER_CUSTOMER";

	public NonMemberCustomer() {
		super(NonMemberCustomer.NON_MEMBER_CUSTOMER_ID + new Date().getTime() + new Random().nextInt());
	}
	
	public boolean isFirstPurchase(){
		return true;
	}
}
