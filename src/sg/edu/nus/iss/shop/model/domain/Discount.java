package sg.edu.nus.iss.shop.model.domain;

/**
 * @author TaoTong
 *
 */

public abstract class Discount {

	private String discountCode;
	private String description;
	private int discountPercentage;
//	private String startDate;
//	private String discountInDays;
	
//	public Discount(String discountCode, String description,
//			int discountPercentage, String startDate, String discountInDays,
//			String applicableToMember) {
//		this.discountCode = discountCode;
//		this.description = description;
//		this.discountPercentage = discountPercentage;
//		this.startDate = startDate;
//		this.discountInDays = discountInDays;
//	}
//
//	public String getDiscountCode() {
//		return discountCode;
//	}
//
//	public void setDiscountCode(String discountCode) {
//		this.discountCode = discountCode;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public int getDiscountPercentage() {
//		return discountPercentage;
//	}
//
//	public void setDiscountPercentage(int discountPercentage) {
//		this.discountPercentage = discountPercentage;
//	}
//
//	public String getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(String startDate) {
//		this.startDate = startDate;
//	}
//
//	public String getDiscountInDays() {
//		return discountInDays;
//	}
//
//	public void setDiscountInDays(String discountInDays) {
//		this.discountInDays = discountInDays;
//	}
	
	public Discount(String discountCode, String description, int discountPercentage) {
		super();
		this.discountCode = discountCode;
		this.description = description;
		this.discountPercentage = discountPercentage;
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
	
	abstract public void setStartDate(String startDate);
	abstract public void setDiscountInDays(String discountInDays);
	abstract public void setApplicableToMember(String applicableToMember);
	abstract public String getStartDate();
	abstract public String getDiscountInDays();
	abstract public String getApplicableToMember();
//	abstract public boolean isApplicable(Customer customer);
}
