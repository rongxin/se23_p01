/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.MemberFirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.MemberSubsequentDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;

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
}
