/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Discount;

/**
 * @author User
 *
 */
public class DiscountManager {
	private static DiscountManager theOnlyDiscountManager;
	private static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date.";

	private DiscountManager() {

	}

	public static DiscountManager getDiscountManager() {
		if (theOnlyDiscountManager == null) {
			DiscountManager.theOnlyDiscountManager = new DiscountManager();
		}
		return theOnlyDiscountManager;
	}

	public List<Discount> getAllDiscounts() {
		return new LinkedList<Discount>();
	}

	public Discount getMaxDiscount(Customer customer) {
		Discount maxDiscount = null;
		List<Discount> discountList = DiscountManager.getDiscountManager().getAllDiscounts();
		Iterator<Discount> iter = discountList.iterator();

		while (iter.hasNext()) {
			Discount discount = iter.next();
			if (discount.isApplicable(customer)) {
				if (maxDiscount == null || discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()) {
					maxDiscount = discount;
				}
			}
		}

		return maxDiscount;
	}
}
