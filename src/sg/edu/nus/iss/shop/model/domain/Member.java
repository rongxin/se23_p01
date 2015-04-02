package sg.edu.nus.iss.shop.model.domain;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.util.ILogger;
import sg.edu.nus.iss.shop.util.Logger;

public class Member extends Customer {
	private String name;
	private int loyalPoints;
	private ILogger log = Logger.getLog();
	
	//lazy loading for loyal point if needed
	public Member(String id) {
		super(id);
	}
	
	public Member(String id, String name) {
		this(id, name, -1);
	}

	public Member(String id, String name, int loyalPoints) {
		super(id);
		this.name = name;
		this.loyalPoints = loyalPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoyalPoints() {
		return loyalPoints;
	}

	public void setLoyalPoints(int loyalPoints) {
		this.loyalPoints = loyalPoints;
	}

	public boolean isFirstPurchase() {
		if (this.getLoyalPoints() == -1) {
			return true;
		}
		return false;
	}

	public Discount getMaxDiscount() {
		Discount maxDiscount = null;

		try {
			maxDiscount = DiscountManager.getDiscountManager().getMaxValidPublicDiscount();
		} catch (Exception e) {
//			e.printStackTrace();
			log.log(e.toString());
		}

		if (this.isFirstPurchase()) {
			try {
				Discount firstPurchaseDiscount = DiscountManager
						.getDiscountManager().getFirstPurchaseDiscountList();
				if (maxDiscount == null
						|| firstPurchaseDiscount.getDiscountPercentage() > maxDiscount
								.getDiscountPercentage()) {
					maxDiscount = firstPurchaseDiscount;
				}
			} catch (Exception e) {
				log.log("get max first purchase discount for member" + e.toString());
			}
		} else {
			try {
				Discount subsequentDiscount = DiscountManager
						.getDiscountManager().getSubsequentDiscountList();
				if (maxDiscount == null
						|| subsequentDiscount.getDiscountPercentage() > maxDiscount
								.getDiscountPercentage()) {
					maxDiscount = subsequentDiscount;
				}
			} catch (Exception e) {
				log.log("get max first purchase discount for member" + e.toString());
			}
		}
		
		if (maxDiscount == null) {
			maxDiscount = new PublicDiscount("MAX_DISCOUNT","Max Discount",0,"ALWAYS","ALWAYS");
		}
		
		return maxDiscount;
	}
}
