package sg.edu.nus.iss.shop.model.domain;

public class PublicDiscount extends Discount {

	private static final String APPLICABLE_TO_MEMBER = "A";
	
	public PublicDiscount(String discountCode, String description,
			int discountPercentage, String startDate, String discountInDays) {
		super(discountCode, description, discountPercentage, startDate, discountInDays,
				APPLICABLE_TO_MEMBER);
	}

	public String getApplicableToMember() {
		return APPLICABLE_TO_MEMBER;
	}
}
