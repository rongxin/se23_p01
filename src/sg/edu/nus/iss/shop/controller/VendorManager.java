package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorManager {
	private static final int VENDOR_NAME_MIN_LENGTH = 5;
	private static final int VENDOR_NAME_MAX_LENGTH = 40;
	private static final int VENDOR_DESCRIPTION_MIN_LENGTH = 5;
	private static final int VENDOR_DESCRIPTION_MAX_LENGTH = 100;
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid vendor name";
	private static final String INVALID_DESCRIPTION_ERROR_MESSAGE = "Invalid vendor description";
	private static final String NIL_CATEGORY_ERROR_MESSAGE = "At least one category is needed";
	private static final String[] INVALID_STR_VENDOR_NAME = { ",", "\"", "\'", ":"};
	private static final String[] INVALID_STR_VENDOR_DESCRIPTION = { "," };

	private static VendorManager theOnlyVendorManager;

	private VendorManager() {

	}

	public static VendorManager getVendorManager() {
		if (VendorManager.theOnlyVendorManager == null) {
			VendorManager.theOnlyVendorManager = new VendorManager();
		}
		return VendorManager.theOnlyVendorManager;
	}

	/* return distinctive vendors for all categories** */
	public List<Vendor> getAllVendors() {
		List<Category> categoryList;
		try {
			categoryList = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			categoryList = new LinkedList<Category>();
		}
		List<Vendor> vendorList = new LinkedList<Vendor>();
		Iterator<Category> it = categoryList.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> categoryVendorList = category.getVendorList();
			vendorList.removeAll(categoryVendorList);
			vendorList.addAll(categoryVendorList);
		}
		return vendorList;
	}

	/** returned vendor with null categories **/
	public Vendor getVendorByName(String name) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		List<Vendor> allVendors = this.getAllVendors();
		Iterator<Vendor> it = allVendors.iterator();
		while (it.hasNext()) {
			Vendor existingVendor = it.next();
			if (existingVendor.getName().equals(name)) {
				return existingVendor;
			}
		}
		return null;
	}

	/**
	 * if the vendor already exists, new description will not be taken for
	 * existing vendor, only new categories will be added
	 * **/
	public Vendor addVendor(String name, String description, List<Category> categories) throws ApplicationGUIException {
		if (!isVendorNameValid(name)) {
			throw new ApplicationGUIException(VendorManager.INVALID_NAME_ERROR_MESSAGE);
		}
		name = name.trim();

		if (!isVendorDescriptionValid(description)) {
			throw new ApplicationGUIException(VendorManager.INVALID_DESCRIPTION_ERROR_MESSAGE);
		}
		description = description.trim();
		
		if (categories == null || categories.size() == 0) {
			throw new ApplicationGUIException(VendorManager.NIL_CATEGORY_ERROR_MESSAGE);
		}
		
		Vendor vendor = this.getVendorByName(name);
		if (vendor == null) { // new vendor
			vendor = new Vendor(name, description);
			saveVendorForCategories(vendor, categories);
		} else { // existing vendor, need to filter out exiting categories
			List<Category> existingCategories = vendor.getCategories();
			List<Category> newCategories = new LinkedList<Category>();
			Iterator<Category> it = categories.iterator();
			while (it.hasNext()) {
				Category category = it.next();
				if (!existingCategories.contains(category)) {
					newCategories.add(category);
				}
			}
			saveVendorForCategories(vendor, newCategories);
		}
		return getVendorByName(name);
	}

	/*** vendor must not be existing for the category ***/
	private void saveVendorForCategories(Vendor vendor, List<Category> categories) {
		Iterator<Category> it = categories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			try {
				saveVendorForSingleCategory(vendor, category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveVendorForSingleCategory(Vendor vendor, Category category) throws Exception {
		try {
			PersistentService.getService().saveVendor(vendor, category);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private boolean isVendorNameValid(String vendorName) {
		if (vendorName == null || vendorName.trim().length() < VendorManager.VENDOR_NAME_MIN_LENGTH || vendorName.trim().length() > VendorManager.VENDOR_NAME_MAX_LENGTH) {
			return false;
		}
		
		for (int i=0;i<INVALID_STR_VENDOR_NAME.length;i++){
			String invalidStr = INVALID_STR_VENDOR_NAME[i];
			if (vendorName.contains(invalidStr)){
				return false;
			}
		}
		return true;
	}
	
	private boolean isVendorDescriptionValid(String vendorDescription) {
		if (vendorDescription == null || vendorDescription.trim().length() < VendorManager.VENDOR_NAME_MIN_LENGTH || vendorDescription.trim().length() > VendorManager.VENDOR_NAME_MAX_LENGTH) {
			return false;
		}
		
		for (int i=0;i<INVALID_STR_VENDOR_DESCRIPTION.length;i++){
			String invalidStr = INVALID_STR_VENDOR_DESCRIPTION[i];
			if (vendorDescription.contains(invalidStr)){
				return false;
			}
		}
		return true;
	}
}
