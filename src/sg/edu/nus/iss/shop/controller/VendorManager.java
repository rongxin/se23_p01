package sg.edu.nus.iss.shop.controller;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorManager {
	private static final int VENDOR_NAME_MIN_LENGTH = 5;
	private static final int VENDOR_NAME_MAX_LENGTH = 20;
	private static final int VENDOR_DESCRIPTION_MIN_LENGTH = 5;
	private static final int VENDOR_DESCRIPTION_MAX_LENGTH = 40;
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid vendor name";
	private static final String INVALID_DESCRIPTION_ERROR_MESSAGE = "Invalid vendor description";
	private static final String NIL_CATEGORY_ERROR_MESSAGE = "At least one category is needed";
	
	private static VendorManager theOnlyVendorManager;

	private VendorManager() {

	}

	public static VendorManager getVendorManager() {
		if (VendorManager.theOnlyVendorManager == null) {
			VendorManager.theOnlyVendorManager = new VendorManager();
		}
		return VendorManager.theOnlyVendorManager;
	}

	public List<Vendor> getAllVendors() {
		return new LinkedList<Vendor>();
	}

	public List<Vendor> listVendorForCategory(Category category) {
		return new LinkedList<Vendor>();
	}

	public Vendor addVendor(String name, String description, List<Category> categories) throws ApplicationGUIException {
		if (name == null || name.trim().length()<VendorManager.VENDOR_NAME_MIN_LENGTH || name.trim().length()>VendorManager.VENDOR_NAME_MAX_LENGTH){
			throw new ApplicationGUIException(VendorManager.INVALID_NAME_ERROR_MESSAGE);
		}
		if (description == null || description.trim().length()<VendorManager.VENDOR_DESCRIPTION_MIN_LENGTH || description.trim().length() >VendorManager.VENDOR_DESCRIPTION_MAX_LENGTH){
			throw new ApplicationGUIException(VendorManager.INVALID_DESCRIPTION_ERROR_MESSAGE);
		}
		if (categories == null || categories.size()==0){
			throw new ApplicationGUIException(VendorManager.NIL_CATEGORY_ERROR_MESSAGE);
		}
		return null;
	}

}
