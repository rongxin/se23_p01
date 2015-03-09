package sg.edu.nus.iss.shop.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorManagerTest {

	/** normal case **/
	@Test
	public void addVendorTest() {
		List<Category> categories = CategoryManager.getCategoryManager().getAllCategories();
		if (categories == null || categories.size() == 0) {
			org.junit.Assert.fail("Cannot find a category");
			return;
		}
		HashMap<Category, List<Vendor>> oldCount = new HashMap<Category, List<Vendor>>();
		Iterator<Category> it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> existingVendors = VendorManager.getVendorManager().listVendorForCategory(category);
			oldCount.put(category, existingVendors);
		}
		Vendor newVendor = new Vendor("Zhu Bin " + new Random().nextLong(), "Test Vendor");
		try {
			VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), categories);
		} catch (Exception e) {
			Assert.fail("not able to add new vendor");
			return;
		}
		it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> newVendors = VendorManager.getVendorManager().listVendorForCategory(category);
			List<Vendor> existingVendors = oldCount.get(category);
			newVendors.removeAll(existingVendors);
			if (newVendors.size() != 1) {
				Assert.fail("did not find new vendor for category" + category.getCode());
				return;
			}
			Assert.assertEquals(newVendor, newVendors.get(0));
		}

	}
	
	/***add same vendor for same Category, exception is expected***/
	@Test
	public void addSameVendorForSameCategoryTest(){
		List<Category> categories = CategoryManager.getCategoryManager().getAllCategories();
		if (categories.size() == 0){
			Assert.fail("Cannot find a category");
			return;
		}
		Boolean tested = false;
		Iterator<Category> it = categories.iterator();
		while(it.hasNext()){
			Category category = it.next();
			List<Category> categoryList = new LinkedList<Category>();
			categoryList.add(category);
			List<Vendor> existingVendors = VendorManager.getVendorManager().listVendorForCategory(category);
			Iterator<Vendor> itVendor = existingVendors.iterator();
			while (itVendor.hasNext()){
				Vendor vendor = itVendor.next();
				try{
					VendorManager.getVendorManager().addVendor(vendor.getName(), vendor.getDescription(), categoryList);
					Assert.fail("Exception did not occur when adding a same vendor for a category");
				}
				catch (ApplicationGUIException e){
				}
			}
			if (!tested){
				Assert.fail("Adding same vendor for same category is NOT tested");
			}
		}
	}
}
