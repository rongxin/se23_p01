/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;

/**
 * @author User
 *
 */
public class DiscountManager {
	private static DiscountManager theOnlyDiscountManager;

	private DiscountManager() {

	}

	public static DiscountManager getDiscountManager() {
		if (theOnlyDiscountManager == null) {
			DiscountManager.theOnlyDiscountManager = new DiscountManager();
		}
		return theOnlyDiscountManager;
	}

	private List<Discount> getAllDiscounts() throws Exception {
		List<Discount> discountList = new LinkedList<Discount>();
		List<Object> objectList = null;

		try {
			objectList = PersistentService.getService().retrieveAll(Discount.class);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}

		if (objectList != null && objectList.isEmpty()) {
			Iterator<Object> iter = objectList.iterator();
			while (iter.hasNext()) {
				discountList.add((Discount) iter.next());
			}
		}
		return discountList;
	}

	public List<Discount> getPublicDiscountList() throws Exception {
		List<Discount> publicDiscountList = new LinkedList<Discount>();

		for (Discount discount : this.getAllDiscounts()) {
			if (discount instanceof PublicDiscount) {
				publicDiscountList.add(discount);
			}
		}
		return publicDiscountList;
	}

	public Discount getFirstPurchaseDiscountList() throws Exception {
		//only 1 discount object for 1st purchase discount
		for (Discount discount : this.getAllDiscounts()) {
			if (discount instanceof FirstPurchaseDiscount) {
				return discount;
			}
		}
		return null;
	}

	public Discount getSubsequentDiscountList() throws Exception {
		//only 1 discount object for subsequent purchase discount
		for (Discount discount : this.getAllDiscounts()) {
			if (discount instanceof SubsequentDiscount) {
				return discount;
			}
		}
		return null;
	}

	public Discount getMaxValidPublicDiscount() throws Exception {
		Discount maxValidPublicDiscount = null;
		
		for (Discount discount : this.getPublicDiscountList()) {
			LocalDate currentDate = LocalDate.now();
			LocalDate startDate = LocalDate.parse(discount.getStartDate());
			LocalDate expiryDate = startDate.plusDays(Integer.parseInt(discount.getDiscountInDays()));

			if (currentDate.isBefore(startDate) || currentDate.isAfter(expiryDate)) {
				continue;
			} else {
				if (maxValidPublicDiscount == null
						|| discount.getDiscountPercentage() > maxValidPublicDiscount.getDiscountPercentage()) {
					maxValidPublicDiscount = discount;
				}
			}
		}
		return maxValidPublicDiscount;
	}
	
}
