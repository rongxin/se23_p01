package sg.edu.nus.iss.shop.model.domain;

import java.time.LocalDate;

public class PublicDiscount extends Discount {

	private static final String APPLICABLE_TO_MEMBER = "A";
	
	public PublicDiscount(String discountCode, String description,
			int discountPercentage, String startDate, String discountInDays) {
		super(discountCode, description, discountPercentage, startDate, discountInDays,
				APPLICABLE_TO_MEMBER);
	}
	
	public boolean isApplicable(Customer customer){
		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = LocalDate.parse(super.getStartDate());
		LocalDate expiryDate = startDate.plusDays(Integer.parseInt(super.getDiscountInDays()));
		
		if (currentDate.isBefore(startDate) || currentDate.isAfter(expiryDate)){
			return false;
		}else{
			return true;
		}
	}
}
