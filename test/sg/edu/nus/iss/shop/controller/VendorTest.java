package sg.edu.nus.iss.shop.controller;

import java.util.List;
import java.util.Random;

import org.junit.Assert;

import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorTest {
	
	@Test
	public void categoryLazyLoadTest(){
		String vendorName = "ZhuBin" + new Random().nextLong();
		Vendor vendor = new Vendor(vendorName, "Test Vendor");
		List<Category> categories = CategoryManager.getCategoryManager().getAllCategories();
		if (categories == null || categories.size() == 0){
			Assert.fail("Cannot find a category");
			return ;
		}
		try{
			vendor = VendorManager.getVendorManager().addVendor(vendor.getName(), vendor.getDescription(), categories);
		}
		catch(Exception e){
			Assert.fail("Failed to add a new vendor");
			return ;
		}
		if (vendor == null){
			Assert.fail("Failed to return the new vendor");
			return ;
		}
		Vendor retrievedVendor = VendorManager.getVendorManager().getVendorByName(vendorName);
		List<Category> vendorCategories = retrievedVendor.getCategories();
		Assert.assertEquals(categories.size(), vendorCategories.size());
		
		categories.removeAll(vendorCategories);
		Assert.assertEquals(categories.size(), 0);
	}
}
