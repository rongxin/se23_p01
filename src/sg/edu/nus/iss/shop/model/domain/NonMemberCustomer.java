package sg.edu.nus.iss.shop.model.domain;

public class NonMemberCustomer extends Customer {
	private final static String NON_MEMBER_CUSTOMER_ID = "NON_MEMBER_CUSTOMER";
	public NonMemberCustomer(){
		super(NonMemberCustomer.NON_MEMBER_CUSTOMER_ID);
	}
}
