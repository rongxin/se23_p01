package sg.edu.nus.iss.shop.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorManagerTest {

	/** add a new vendor in all categories **/
	@Test
	public void addVendorTest1() {
		List<Category> categories;
		try {
			categories = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			Assert.fail("Exception occurred when getting all categories");
			return;
		}
		if (categories == null || categories.size() == 0) {
			org.junit.Assert.fail("Cannot find a category");
			return;
		}
		HashMap<Category, Integer> oldCount = new HashMap<Category, Integer>();
		Iterator<Category> it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> existingVendors = category.getVendorList();
			oldCount.put(category, existingVendors.size());
		}
		Vendor newVendor = new Vendor("ZhuBin" + new Random().nextLong(), "Test Vendor");
		try {
			VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), categories);
		} catch (Exception e) {
			Assert.fail("not able to add new vendor");
			return;
		}
		it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> newVendors = category.getVendorList();
			int oldVendorCount = oldCount.get(category);
			int newVendorCount = newVendors.size();
			System.out.println("old: " + oldVendorCount + " new: " + newVendorCount);
			Assert.assertEquals(oldVendorCount + 1, newVendorCount);
		}

	}

	/*** add same vendor for same Category, exception is expected ***/
	@Test
	public void addSameVendorForSameCategoryTest() {
		List<Category> categories;
		try {
			categories = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			Assert.fail("Exception occurred when getting all categories");
			return;
		}
		if (categories == null || categories.size() == 0) {
			Assert.fail("did not find any categories");
			return;
		}
		Vendor newVendor = new Vendor("Zhu Bin" + new Random().nextLong(), "Test Vendor");
		int categoryCountBeforeFirstAdd = newVendor.getCategories().size();
		try {
			VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), categories);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed to add a new vendor");
			return;
		}
		int categoryCountAfterFirstAdd = newVendor.getCategories().size();
		Assert.assertEquals(categoryCountBeforeFirstAdd + categories.size(), categoryCountAfterFirstAdd);

		try {
			VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), categories);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed to add a new vendor");
			return;
		}
		int categoryCountAfterSecondAdd = newVendor.getCategories().size();
		Assert.assertEquals(categoryCountAfterFirstAdd, categoryCountAfterSecondAdd);
	}

	@Test
	public void TestRetrieveVendor() {
		List<Category> allCategories;
		try {
			allCategories = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			Assert.fail("Exception occurred when getting all categories");
			return;
		}
		if (allCategories == null || allCategories.size() == 0) {
			Assert.fail("Cannot find a category");
			return;
		}
		String vendorName = "ZhuBin" + new Random().nextLong();
		String vendorDescription = "Test Vendor " + new Random().nextLong();
		try {
			VendorManager.getVendorManager().addVendor(vendorName, vendorDescription, allCategories);
		} catch (Exception e) {
			Assert.fail("failed to add a member");
			return;
		}
		Vendor retrievedVendor = VendorManager.getVendorManager().getVendorByName(vendorName);
		Assert.assertEquals(vendorDescription, retrievedVendor.getDescription());

		List<Category> retrievedVendorCategories = retrievedVendor.getCategories();
		Assert.assertEquals(allCategories.size(), retrievedVendorCategories.size());
		allCategories.removeAll(retrievedVendorCategories);
		Assert.assertEquals(allCategories.size(), 0);
	}

	@Test
	public void getAllVendorsTest() {
		List<Vendor> vendorList = VendorManager.getVendorManager().getAllVendors();
		Assert.assertNotEquals(vendorList.size(), 0);
	}

	@Test
	public void getVendorByNameTest() {
		List<Vendor> vendorList = VendorManager.getVendorManager().getAllVendors();
		if (vendorList.isEmpty()) {
			Assert.fail("No vendor exists in system");
			return;
		}
		Vendor vendor = VendorManager.getVendorManager().getVendorByName(vendorList.get(0).getName());
		Assert.assertNotNull(vendor);
	}
	
	
	/***add a few vendors for a category, count vendors****/
	@Test
	public void addVendorTest2(){
		List<Category> categoryList;		
		try{
			categoryList = CategoryManager.getCategoryManager().getAllCategories();
		}
		catch(Exception e){
			e.printStackTrace();
			Assert.fail("Exception occurred when retrieving categories");
			return ;
		}
		if (categoryList == null || categoryList.isEmpty()){
			Assert.fail("Cannot find any existing categories");
		}
		Category category = categoryList.get(new Random().nextInt(categoryList.size()));
		int existingVendorCount = category.getVendorList().size();
		int newVendorNum = new Random().nextInt(50) + 1;
		List<Category> addCategoryList = new LinkedList<Category>();
		addCategoryList.add(category);
		for (int i=0;i<newVendorNum;i++){
			try{
				VendorManager.getVendorManager().addVendor("Zhu Bin "+ new Random().nextLong(), "Test Vendor", addCategoryList);
			}
			catch(Exception e){
				e.printStackTrace();
				Assert.fail("Exception occurred when adding a new vendor");
				return ;
			}
		}
		Assert.assertEquals(existingVendorCount + newVendorNum, category.getVendorList().size());
	}
	
	/***add a few vendors for a few categories, get all vendors, compare vendor count***/
	@Test
	public void getAllVendorsTest3(){
		List<Category> categoryList;		
		try{
			categoryList = CategoryManager.getCategoryManager().getAllCategories();
		}
		catch(Exception e){
			e.printStackTrace();
			Assert.fail("Exception occurred when retrieving categories");
			return ;
		}
		if (categoryList == null || categoryList.isEmpty()){
			Assert.fail("Cannot find any existing categories");
		}
		int existingVendorCount = VendorManager.getVendorManager().getAllVendors().size();
		int newVendorNum = new Random().nextInt(50) + 1;
		for (int i=0; i<newVendorNum;i++){
			List<Category> newAddCategories = new LinkedList<Category>();
			for (int j=0;j<categoryList.size();j++){
				int randomInt = new Random().nextInt(2);
				if (randomInt == 1){
					newAddCategories.add(categoryList.get(j));
				}
			}
			try{
				VendorManager.getVendorManager().addVendor("Zhu Bin"+ new Random().nextLong(), "Test Vendor getAllVendorsTest3", newAddCategories);
			}
			catch(Exception e){
				e.printStackTrace();
				Assert.fail("Exception occurred when adding a vendor");
				return ;
			}
		}
		Assert.assertEquals(existingVendorCount+newVendorNum, VendorManager.getVendorManager().getAllVendors().size());
	}
}
