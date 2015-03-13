/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;

/**
 * @author User
 *
 */
public class DiscountManager {
	private static DiscountManager theOnlyDiscountManager;
	private static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date.";
	
	private DiscountManager(){
		
	}
	
	public static DiscountManager getDiscountManager() {
		if (theOnlyDiscountManager == null){
			DiscountManager.theOnlyDiscountManager = new DiscountManager();
		}	
		return theOnlyDiscountManager;
	}
	
	public List<Discount> getAllDiscounts() {
		return new LinkedList<Discount>();
	}
	
	public Discount getMaxDiscount(String customerId){
		Discount maxDiscount = null;
		Member member = MemberManager.getMemberManager().getMemberById(customerId);
		List<Discount> discountList = DiscountManager.getDiscountManager().getAllDiscounts();
		Iterator<Discount> iter = discountList.iterator();
		
		while(iter.hasNext()){
			Discount discount = iter.next();
			
			if (member == null){
				if (discount.getApplicableToMember() == "A"){
					LocalDate currentDate = LocalDate.now();
					LocalDate startDate = LocalDate.parse(discount.getStartDate());
					LocalDate expiryDate = startDate.plusDays(Integer.parseInt(discount.getDiscountInDays()));
					
					if (currentDate.isBefore(startDate) || currentDate.isAfter(expiryDate)){
						continue;
					}
					
					if (maxDiscount == null){
						maxDiscount = discount;
					}else if(discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()){
						maxDiscount = discount;
					}	
				}
			}else{
				if (discount.getApplicableToMember() == "A"){
					LocalDate currentDate = LocalDate.now();
					LocalDate startDate = LocalDate.parse(discount.getStartDate());
					LocalDate expiryDate = startDate.plusDays(Integer.parseInt(discount.getDiscountInDays()));
					if (currentDate.isBefore(startDate) || currentDate.isAfter(expiryDate)){
						continue;
					}
				}
				
				if (maxDiscount == null){
					maxDiscount = discount;
				}else if(discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()){
					maxDiscount = discount;
				}			
			}			
		}
		
		return maxDiscount;
	}
}
