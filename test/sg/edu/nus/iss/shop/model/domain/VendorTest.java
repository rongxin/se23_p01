package sg.edu.nus.iss.shop.model.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.VendorManager;

public class VendorTest {

	@Test
	public void categoryLazyLoadTest() {
		String vendorName = "ZhuBin" + new Random().nextLong();
		Vendor vendor = new Vendor(vendorName, "Test Vendor");
		List<Category> categories;
		try {
			categories = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			Assert.fail("Exception occurred when getting all categories");
			return;
		}
		if (categories == null || categories.size() == 0) {
			Assert.fail("Cannot find a category");
			return;
		}
		try {
			vendor = VendorManager.getVendorManager().addVendor(vendor.getName(), vendor.getDescription(), categories);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to add a new vendor");
			return;
		}
		if (vendor == null) {
			Assert.fail("Failed to return the new vendor");
			return;
		}
		Vendor retrievedVendor = VendorManager.getVendorManager().getVendorByName(vendorName);
		List<Category> vendorCategories = retrievedVendor.getCategories();
		Assert.assertEquals(categories.size(), vendorCategories.size());

		categories.removeAll(vendorCategories);
		Assert.assertEquals(categories.size(), 0);
	}
	
	@Test
	public void getVendorFromCategoryTest(){
		List<Category> categoryList = new LinkedList<Category>();
		try{
			categoryList = CategoryManager.getCategoryManager().getAllCategories();
		}
		catch(Exception e){
			e.printStackTrace();
			return ;
		}
		Iterator<Category> it = categoryList.iterator();
		while (it.hasNext()){
			Category category = it.next();
			System.out.println(category.getVendorList().size());
		}
	}
	
	@Test
	public void equalTest(){
		Vendor vendor1 = new Vendor("ZhuBin", "Test Vendor");
		Vendor vendor2 = new Vendor("ZhuBin", "Test Vendor");
		Assert.assertEquals(vendor1, vendor2);
	}
	
	@Test
	public void getCategoriesTest(){
		List<Vendor> vendorList = VendorManager.getVendorManager().getAllVendors();
		if (vendorList.isEmpty()){
			Assert.fail("there is no vendor existing in systme");
			return ;
		}
		List<Category> categoryList = vendorList.get(0).getCategories();
		Assert.assertNotEquals(categoryList.size(), 0);
	}
}
