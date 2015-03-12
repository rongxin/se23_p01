/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Date;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;

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
	
	public Discount getMaxDiscount(){
		Discount maxDiscount = null;
		
		List<Discount> discountList = DiscountManager.getDiscountManager().getAllDiscounts();
		Iterator<Discount> iter = discountList.iterator();
		while(iter.hasNext()){
			Discount discount = iter.next();
			
			if (discount.getStartDate() == "ALWAYS" ){
				
			}
//			discount.getStartDate()
			if (maxDiscount == null){
				maxDiscount = discount;
			}else if(discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()){
				maxDiscount = discount;
			}
		}	
		return maxDiscount;
	}
	
	
	private String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
