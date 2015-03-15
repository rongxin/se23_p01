package sg.edu.nus.iss.shop.model.domain;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.shop.controller.DiscountManager;

public class Member extends Customer {
	private String name;
	private int loyalPoints;

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
	
	public boolean isFirstPurchase(){
		if (this.getLoyalPoints() < 0){
			return true;
		}else{
			return false;
		}
	}
	
	public Discount getMaxDiscount() {
		Discount maxDiscount = null;
		
		try {
			List<Discount> publicDiscountList = DiscountManager.getDiscountManager().getValidPublicDiscountList();
			Iterator<Discount> iter = publicDiscountList.iterator();
			while(iter.hasNext()){
				Discount discount = iter.next();
				
				if (maxDiscount == null || discount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()) {
					maxDiscount = discount;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Discount firstPurchaseDiscount = DiscountManager.getDiscountManager().getFirstPurchaseDiscountList();
			if (maxDiscount == null || firstPurchaseDiscount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()) {
				maxDiscount = firstPurchaseDiscount;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Discount subsequentDiscount = DiscountManager.getDiscountManager().getSubsequentDiscountList();
			if (maxDiscount == null || subsequentDiscount.getDiscountPercentage() > maxDiscount.getDiscountPercentage()) {
				maxDiscount = subsequentDiscount;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return maxDiscount;
	}
	
//	@Override
//	public Discount getMaxDiscount() {
//		
//		return super.getMaxDiscount();
//	}
}
