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
	public void addVendorTest() {
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
		Vendor newVendor = new Vendor("Zhu Bin " + new Random().nextLong(), "Test Vendor");
		try {
			VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), categories);
		} catch (Exception e) {
			Assert.fail(e.toString());
			return;
		}
		Iterator<Category> it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Category> individualCategory = new LinkedList<Category>();
			individualCategory.add(category);
			try {
				VendorManager.getVendorManager().addVendor(newVendor.getName(), newVendor.getDescription(), individualCategory); // try
																																	// to
																																	// add
																																	// the
																																	// vendor
																																	// into
																																	// individual
																																	// category
																																	// again
				Assert.fail("Exception did not occur when adding same vendor for same category");
			} catch (Exception e) {
			}
		}
	}

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
}
