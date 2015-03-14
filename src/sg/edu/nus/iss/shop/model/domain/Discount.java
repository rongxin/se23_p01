package sg.edu.nus.iss.shop.model.domain;

/**
 * @author TaoTong
 *
 */

public abstract class Discount {

	private String discountCode;
	private String description;
	private int discountPercentage;
	private String startDate;
	private String discountInDays;
	
	public Discount(String discountCode, String description,
			int discountPercentage, String startDate, String discountInDays,
			String applicableToMember) {
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
		this.startDate = startDate;
		this.discountInDays = discountInDays;
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

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
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

	abstract public boolean isApplicable(Customer customer);
}
