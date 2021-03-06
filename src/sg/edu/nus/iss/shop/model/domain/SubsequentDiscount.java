package sg.edu.nus.iss.shop.model.domain;

public class SubsequentDiscount extends Discount {

	private String startDate = ALWAY_VALID_START_DATE;
	private String discountInDays = ALWAY_VALID_DAYS;
	private String applicableToMember = APPLICABLETOMEMBER;

	public SubsequentDiscount(String discountCode, String description,
			int discountPercentage) {
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

}
