/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;

public class DiscountManager {
	private static DiscountManager theOnlyDiscountManager;
	private static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date.";
	
	public static DiscountManager getTheOnlyDiscountManager() {
		if (theOnlyDiscountManager == null){
			DiscountManager.theOnlyDiscountManager = new DiscountManager();
		}	
		return theOnlyDiscountManager;
	}
	
	
}
