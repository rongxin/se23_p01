package sg.edu.nus.iss.shop.model.domain;

public class MemberFirstPurchaseDiscount extends Discount {

	private static final String START_DATE = "ALWAYS";
	private static final String DISCOUNT_IN_DAYS = "ALWAYS";
	private static final String APPLICABLE_TO_MEMBER = "M";
	
	public MemberFirstPurchaseDiscount(String discountCode, String description,
			int discountPercentage) {
		super(discountCode, description, discountPercentage, START_DATE, DISCOUNT_IN_DAYS,
				APPLICABLE_TO_MEMBER);
	}
	
	public boolean isApplicable(Customer customer){
		if (customer instanceof Member && ((Member) customer).isFirstPurchase()){
				return true;
		}else{
			return false;
		}
	}
}
