package sg.edu.nus.iss.shop.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

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
}
