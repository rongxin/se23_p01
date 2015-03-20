package sg.edu.nus.iss.shop.model.domain;

/**
 * @author TaoTong
 *
 */

public abstract class Discount {

	public static final String APPLICABLETOMEMBER = "M";
	public static final String APPLICABLETOALL = "A";
	public static final String ALWAY_VALID_DAYS = "ALWAYS";
	public static final String ALWAY_VALID_START_DATE = "ALWAYS";
	public static final String FIRST_PURCHASE_CODE = "MEMBER_FIRST";
	public static final String SUBSEQUENT_CODE = "MEMBER_SUBSEQ";
	
	private String discountCode;
	private String description;
	private int discountPercentage;
	
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
}
