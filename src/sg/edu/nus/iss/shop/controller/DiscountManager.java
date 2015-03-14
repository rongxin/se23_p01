/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Discount;

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

	public List<Discount> getAllDiscounts() throws ApplicationGUIException {
		List<Discount> discountList = new LinkedList<Discount>();
		List<Object> objectList = null;
		
		try{
			objectList = PersistentService.getService().retrieveAll(Discount.class);
		}catch(Exception e){
			throw new ApplicationGUIException(e.toString());
		}
		
		if (objectList != null && objectList.isEmpty()){
			Iterator<Object> iter = objectList.iterator();
			while(iter.hasNext()){
				discountList.add((Discount)iter.next());
			}
		}
		return discountList;
	}

	public Discount getMaxDiscount(Customer customer) throws ApplicationGUIException {
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
