package sg.edu.nus.iss.shop.model.domain;

public class Member extends Customer {
	private String name;
	private int loyalPoints;

	public Member(String id, String name) {
		this(id, name, -1);
	}

	public Member(String id, String name, int loyalPoints) {
		super(id);
		this.name = name;
		this.loyalPoints = loyalPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoyalPoints() {
		return loyalPoints;
	}

	public void setLoyalPoints(int loyalPoints) {
		this.loyalPoints = loyalPoints;
	}
	
	public boolean isFirstPurchase(){
		if (this.getLoyalPoints() < 0){
			return true;
		}else{
			return false;
		}
	}
	
//	@Override
//	public Discount getMaxDiscount() {
//		
//		return super.getMaxDiscount();
//	}
}
