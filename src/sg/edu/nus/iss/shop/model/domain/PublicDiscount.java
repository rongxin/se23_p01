package sg.edu.nus.iss.shop.model.domain;

public class PublicDiscount extends Discount {
	private static final String APPLICABLE_TO_MEMBER = "A";
	
	public PublicDiscount(String discountCode, String description,
			String startDate, String discountInDays, int discountPercentage,
			String applicableToMember) {
		super(discountCode, description, startDate, discountInDays, discountPercentage,
				APPLICABLE_TO_MEMBER);
	}
}
