package sg.edu.nus.iss.shop.model.domain;

public class PublicDiscount extends Discount {
	
	private String startDate;
	private String discountInDays;
	private static final String APPLICABLE_TO_MEMBER = "A";
	
	public PublicDiscount(String discountCode, String description,
			int discountPercentage,String startDate,String discountInDays) {
		super(discountCode, description, discountPercentage);
		this.startDate = startDate;
		this.discountInDays = discountInDays;
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

	public static String getApplicableToMember() {
		return APPLICABLE_TO_MEMBER;
	}
	
}
