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
import sg.edu.nus.iss.shop.util.ILogger;
import sg.edu.nus.iss.shop.util.Logger;

/**
 * @author User
 *
 */
public class DiscountManager {
	private static final String DISCOUNTCODE_NOT_EXIST_ERROR_MESSAGE = "Discount code doesn't exist!";
	private static final String INVALID_DISCOUNT_CODE_ERROR_MESSAGE = "Invalid discount code";
	private static final String INVALID_DESCRIPTION_ERROR_MESSAGE = "Invalid discount description";
	private static final String INVALID_DISCOUNT_PERCENTAGE_ERROR_MESSAGE = "Invalid discount percentage";
	private static final String INVALID_STARTDATE_ERROR_MESSAGE = "Invalid discount start date";
	private static final String INVALID_DISCOUNTINDAYS_ERROR_MESSAGE = "Invalid discountInDays";
	private static final String INVALID_APPLICABLETOMEMBER_ERROR_MESSAGE = "Invalid applicableToMember";
	private static final String MEMBER_FIRST_PURCHASE_DISCOUNT_EXIST = "Member first purchase discount already exists!";
	private static final String MEMBER_SUBSEQUENT_DISCOUNT_EXIST = "Member subsequent discount already exists!";
	private static final String DISCOUNTCODE_NOT_MATCH_APPLICABLETOMEMBER = "Discount code does not match applicableToMember";
	private static final String DUPLICATE_DISCOUNTCODE_ERROR_MESSAGE = "Discount code already exists!";
	private static final String PUBLIC_DISCOUNT_START_DAY_ERROR = "The start day of public discount can not be ALWAYS!";
	private static final String PUBLIC_DISCOUNT_IN_DAYS_ERROR = "The valid days of public discount can not be ALWAYS!";
	private static final String DISCOUNT_PERCENTAGE_ERROR = "The discount percentage can not be less than zero!";
	private static final String INVALID_MEMBER_DISCOUNT_ERROR_MESSAGE = "Invalid member discount.";
	private static DiscountManager theOnlyDiscountManager;

	private ILogger log = Logger.getLog();

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
			throw new ApplicationGUIException(e.toString());
		} catch (InvalidDataFormat e) {
			throw new ApplicationGUIException(e.toString());
		} catch (IOException e) {
			throw new ApplicationGUIException(e.toString());
		}
		return discount;
	}
	
	public Discount addDiscount(String discountCode, String description,
			int discountPercentage, String startDate, String discountInDays,
			String applicableToMember)
					throws ApplicationGUIException {

		Discount newDiscount = null;
		
		if (discountCode == null){
			throw new ApplicationGUIException(DiscountManager.INVALID_DISCOUNT_CODE_ERROR_MESSAGE);
		}
		if (description == null || description.trim().length()< 0){
			throw new ApplicationGUIException(DiscountManager.INVALID_DESCRIPTION_ERROR_MESSAGE);
		}
		if (discountPercentage <= 0) {
			throw new ApplicationGUIException(DiscountManager.INVALID_DISCOUNT_PERCENTAGE_ERROR_MESSAGE);
		}
		if (startDate == null || (!startDate.equals(Discount.ALWAY_VALID_START_DATE) && LocalDate.parse(startDate).isBefore(LocalDate.now()) )) {
			throw new ApplicationGUIException(DiscountManager.INVALID_STARTDATE_ERROR_MESSAGE);
		}
		if(!discountInDays .equals(Discount.ALWAY_VALID_DAYS) && (!isInteger(discountInDays) || Integer.parseInt(discountInDays) < 0)){
			throw new ApplicationGUIException(DiscountManager.INVALID_DISCOUNTINDAYS_ERROR_MESSAGE);
		}
		if(!applicableToMember.equals(Discount.APPLICABLETOALL) && !applicableToMember.equals(Discount.APPLICABLETOMEMBER)){
			throw new ApplicationGUIException(DiscountManager.INVALID_APPLICABLETOMEMBER_ERROR_MESSAGE);
		}

		if (applicableToMember.equals(Discount.APPLICABLETOALL)){
			try {
				if (startDate.equals(Discount.ALWAY_VALID_START_DATE)) {
					throw new ApplicationGUIException(DiscountManager.PUBLIC_DISCOUNT_START_DAY_ERROR);
				}
				if (discountInDays.equals(Discount.ALWAY_VALID_DAYS)) {
					throw new ApplicationGUIException(DiscountManager.PUBLIC_DISCOUNT_IN_DAYS_ERROR);
				}
				for(int i = 0; i < DiscountManager.getDiscountManager().getPublicDiscountList().size();i++){
					if(discountCode.equals(DiscountManager.getDiscountManager().getPublicDiscountList().get(i).getDiscountCode())){
						throw new ApplicationGUIException(DiscountManager.DUPLICATE_DISCOUNTCODE_ERROR_MESSAGE);
					}
				}
				newDiscount = new PublicDiscount(discountCode,description,discountPercentage,startDate,discountInDays);
			} catch (Exception e) {
				log.log("get public discount list in add discount method" + e.toString());
			}
		}else if(applicableToMember.equals(Discount.APPLICABLETOMEMBER)){
			try {
				if (discountCode.equals("MEMBER_FIRST") && DiscountManager.getDiscountManager().getFirstPurchaseDiscountList() == null){
					newDiscount = new FirstPurchaseDiscount(discountCode,description,discountPercentage);
				}else if (discountCode.equals("MEMBER_FIRST") && DiscountManager.getDiscountManager().getFirstPurchaseDiscountList() != null){
					throw new ApplicationGUIException(DiscountManager.MEMBER_FIRST_PURCHASE_DISCOUNT_EXIST);
				}else if(discountCode.equals("MEMBER_SUBSEQ") && DiscountManager.getDiscountManager().getSubsequentDiscountList() == null){
					newDiscount = new SubsequentDiscount(discountCode,description,discountPercentage);
				}else if (discountCode.equals("MEMBER_SUBSEQ") && DiscountManager.getDiscountManager().getSubsequentDiscountList() != null){
					throw new ApplicationGUIException(DiscountManager.MEMBER_SUBSEQUENT_DISCOUNT_EXIST);
				}else if (!discountCode.equals("MEMBER_FIRST") && !discountCode.equals("MEMBER_SUBSEQ")) {
					throw new ApplicationGUIException(DiscountManager.DISCOUNTCODE_NOT_MATCH_APPLICABLETOMEMBER);
				}else{
					throw new ApplicationGUIException(DiscountManager.INVALID_MEMBER_DISCOUNT_ERROR_MESSAGE);
				}
			} catch (Exception e) {
				log.log("get first purchase discount in add discount method" + e.toString());
			} 
		}
		
		try {
			PersistentService.getService().saveRecord(newDiscount);
			return newDiscount;
		} catch (Exception e) {
			log.log("add discount :" + e.toString());
			throw new ApplicationGUIException(e.toString());
		}
	}
	
	public Discount editDiscount(String previousDiscountCode,int newDiscountPercentage) throws ApplicationGUIException {
		Discount previousDiscount = null;
		
		try {
			previousDiscount = this.getDiscountByCode(previousDiscountCode);
		} catch (Exception e) {
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
			throw new ApplicationGUIException(e.toString());
		}
		
		return previousDiscount;
	}
	
	public List<Discount> getAllDiscounts() {
		List<Discount> discountList = null;
		
		try {
			discountList = PersistentService.getService().retrieveAll(Discount.class);
		} catch (IOException e) {
			log.log("Get all discount" + e.toString());
		} catch (InvalidDataFormat e) {
			log.log("Get all discount" + e.toString());
		} catch (InvalidDomainObject e) {
			log.log("Get all discount" + e.toString());
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
	
//	private Date getDateFromString(String dateString) throws ApplicationGUIException{
//		DateFormat dateFormat = new SimpleDateFormat(discountDateFormat);
//		Date dateObject = new Date();
//		
//		try {
//			dateObject = dateFormat.parse(dateString);
//		} catch (ParseException e) {
//			log.log("discount startdate format error" + e.toString());
//			throw new ApplicationGUIException(DiscountManager.INVALID_STARTDATE_FORMAT_ERROR_MESSAGE);
//		}
//		return dateObject;
//	}
	
	private boolean isInteger(String discountInDays){
		boolean result = discountInDays.matches("[0-9]+");
		return result;
	}
}
