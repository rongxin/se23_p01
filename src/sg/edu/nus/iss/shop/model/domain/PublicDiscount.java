package sg.edu.nus.iss.shop.model.domain;

public class PublicDiscount extends Discount {
	private String startDate;
	private String discountInDays;
	private String applicableToMember = "A";
	
	public PublicDiscount(String discountCode, String description,
			int discountPercentage, String startDate, String discountInDays) {
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

	public String getApplicableToMember() {
		return applicableToMember;
	}

	public void setApplicableToMember(String applicableToMember) {
		this.applicableToMember = applicableToMember;
	}
	
//	public boolean isApplicable(Customer customer){
//		LocalDate currentDate = LocalDate.now();
//		LocalDate startDate = LocalDate.parse(super.getStartDate());
//		LocalDate expiryDate = startDate.plusDays(Integer.parseInt(super.getDiscountInDays()));
//		
//		if (currentDate.isBefore(startDate) || currentDate.isAfter(expiryDate)){
//			return false;
//		}else{
//			return true;
//		}
//	}
}
