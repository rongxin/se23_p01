package sg.edu.nus.iss.shop.model.domain;

public class FirstPurchaseDiscount extends Discount {
	
	private String startDate = "ALWAYS";
	private String discountInDays = "ALWAYS";
	private String applicableToMember = "M";
	
	public FirstPurchaseDiscount(String discountCode, String description, int discountPercentage) {
		super(discountCode, description, discountPercentage);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDiscountInDays() {
		return discountInDays;
	}

	public void setDiscountInDays(String discountInDays) {
		this.discountInDays = discountInDays;
	}

	public String getApplicableToMember() {
		return applicableToMember;
	}

	public void setApplicableToMember(String applicableToMember) {
		this.applicableToMember = applicableToMember;
	}
	
//	public boolean isApplicable(Customer customer){
//		if (customer instanceof Member && ((Member) customer).isFirstPurchase()){
//				return true;
//		}else{
//			return false;
//		}
//	}
}
