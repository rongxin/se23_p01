package sg.edu.nus.iss.shop.model.domain;

public class MemberSubsequentDiscount extends Discount {
	
	private static final String START_DATE = "ALWAYS";
	private static final String DISCOUNT_IN_DAYS = "ALWAYS";
	private static final String APPLICABLE_TO_MEMBER = "M";
	
	public MemberSubsequentDiscount(String discountCode, String description,
			int discountPercentage) {
		super(discountCode, description, discountPercentage);
	}

	public static String getStartDate() {
		return START_DATE;
	}

	public static String getDiscountInDays() {
		return DISCOUNT_IN_DAYS;
	}

	public static String getApplicableToMember() {
		return APPLICABLE_TO_MEMBER;
	}
}
