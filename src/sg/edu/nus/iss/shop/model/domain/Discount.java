package sg.edu.nus.iss.shop.model.domain;

/**
 * @author TaoTong
 *
 */
public class Discount {
	private String discountCode;
	private String description;
	private String startDate;
	private String discountInDays;
	private int discountPercentage;
	private String applicableToMember;
	
	public Discount(String discountCode, String description, String startDate,
			String discountInDays, int discountPercentage,
			String applicableToMember) {
		super();
		this.discountCode = discountCode;
		this.description = description;
		this.startDate = startDate;
		this.discountInDays = discountInDays;
		this.discountPercentage = discountPercentage;
		this.applicableToMember = applicableToMember;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getApplicableToMember() {
		return applicableToMember;
	}

	public void setApplicableToMember(String applicableToMember) {
		this.applicableToMember = applicableToMember;
	}
}
