package sg.edu.nus.iss.shop.model.domain;

/**
 * @author TaoTong
 *
 */
public class Discount {
	private String discountCode;
	private String description;
	private int discountPercentage;
	
	public Discount(String discountCode, String description,
			int discountPercentage) {
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
	
}
