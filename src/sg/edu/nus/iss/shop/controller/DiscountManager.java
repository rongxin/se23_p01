/**
 * @author	taotong
 */
package sg.edu.nus.iss.shop.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;

/**
 * @author User
 *
 */
public class DiscountManager {
	private static final String DISCOUNTCODE_NOT_EXIST_ERROR_MESSAGE = "Discount code doesn't exist!";
//	private static final String MEMBER_FIRST_PURCHASE_DISCOUNT_EXIST = "Member first purchase discount already exists!";
//	private static final String MEMBER_SUBSEQUENT_DISCOUNT_EXIST = "Member subsequent discount already exists!";
//	private static final String PUBLIC_DISCOUNT_START_DAY_ERROR = "The start day of public discount can not be ALWAYS!";
//	private static final String PUBLIC_DISCOUNT_IN_DAYS_ERROR = "The valid days of public discount can not be ALWAYS!";
	private static final String DISCOUNT_PERCENTAGE_ERROR = "The discount percentage can not be less than zero!";
	private static DiscountManager theOnlyDiscountManager;

	private DiscountManager() {

	}

	public static DiscountManager getDiscountManager() {
		if (theOnlyDiscountManager == null) {
			DiscountManager.theOnlyDiscountManager = new DiscountManager();
		}
		return theOnlyDiscountManager;
	}
	
	public Discount getDiscountByCode(String discountCode) throws ApplicationGUIException {
		Discount discount = null;
		
		try {
			discount = PersistentService.getService().retrieveObject(Discount.class, discountCode);
		} catch (InvalidDomainObject e) {
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
		} catch (InvalidDataFormat e) {
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
		}
		return discount;
	}
	
	public Discount editDiscount(String previousDiscountCode,int newDiscountPercentage) throws ApplicationGUIException {
		Discount previousDiscount = null;
		
		try {
			previousDiscount = this.getDiscountByCode(previousDiscountCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
		}
		
		if (previousDiscount == null){
			throw new ApplicationGUIException(DiscountManager.DISCOUNTCODE_NOT_EXIST_ERROR_MESSAGE);
		}
		
		if (newDiscountPercentage < 0) {
			throw new ApplicationGUIException(DiscountManager.DISCOUNT_PERCENTAGE_ERROR);
		}

		previousDiscount.setDiscountPercentage(newDiscountPercentage);

		try {
			PersistentService.getService().saveRecord(previousDiscount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
		}
		
		return previousDiscount;
	}
	
	public List<Discount> getAllDiscounts() {
//		List<Discount> discountList = new LinkedList<Discount>();
//		List<Object> objectList = null;
//
//		try {
//			objectList = PersistentService.getService().retrieveAll(Discount.class);
//		} catch (Exception e) {
//			throw new Exception(e.toString());
//		}
//
//		if (objectList != null && objectList.isEmpty()) {
//			Iterator<Object> iter = objectList.iterator();
//			while (iter.hasNext()) {
//				discountList.add((Discount) iter.next());
//			}
//		}
		List<Discount> discountList = null;
		
		try {
			discountList = PersistentService.getService().retrieveAll(Discount.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			e.printStackTrace();
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
