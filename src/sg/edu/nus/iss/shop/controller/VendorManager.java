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
	private static final int VENDOR_NAME_MAX_LENGTH = 20;
	private static final int VENDOR_DESCRIPTION_MIN_LENGTH = 5;
	private static final int VENDOR_DESCRIPTION_MAX_LENGTH = 40;
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid vendor name";
	private static final String INVALID_DESCRIPTION_ERROR_MESSAGE = "Invalid vendor description";
	private static final String NIL_CATEGORY_ERROR_MESSAGE = "At least one category is needed";
	private static final String SAVE_VENDOR_FAIL_ERROR_MESSAGE = "Failed to save the vendor";
	
	private static VendorManager theOnlyVendorManager;

	private VendorManager() {

	}

	public static VendorManager getVendorManager() {
		if (VendorManager.theOnlyVendorManager == null) {
			VendorManager.theOnlyVendorManager = new VendorManager();
		}
		return VendorManager.theOnlyVendorManager;
	}

	/* return distinctive vendors for all categories***/
	public List<Vendor> getAllVendors() {
		List<Category> categoryList = CategoryManager.getCategoryManager().getAllCategories();
		List<Vendor> vendorList = new LinkedList<Vendor>();
		Iterator<Category> it = categoryList.iterator();
		while (it.hasNext()){
			Category category = it.next();
			List<Vendor> categoryVendorList = this.listVendorForCategory(category);
			vendorList.removeAll(categoryVendorList);
			vendorList.addAll(categoryVendorList);
		}
		return vendorList;
	}

	public List<Vendor> listVendorForCategory(Category category) {
		if (category == null){
			return new LinkedList<Vendor>();
		}
		List<Vendor> vendorList = new LinkedList<Vendor>();
		List<Object> objectList;
		try{
			objectList = PersistentService.getService().retrieveVendors(category);
		}
		catch(Exception e){
			return new LinkedList<Vendor>();
		}
		
		Iterator<Object> it = objectList.iterator();
		while (it.hasNext()){
			Vendor vendor  = (Vendor)it.next();
			vendorList.add(vendor);
		}
		return vendorList;
	}
	
	/** returned vendor with null categories**/
	public Vendor getVendorByName(String name){
		if (name == null || name.trim().length()==0){
			return null;
		}
		List<Vendor> allVendors = this.getAllVendors();
		Iterator<Vendor> it = allVendors.iterator();
		while (it.hasNext()){
			Vendor existingVendor = it.next();
			if (existingVendor.getName().equals(name)){
				return existingVendor;
			}
		}
		return null;
	}

	/** if the vendor already exists, new description will not be taken 
	 *  for existing vendor, only new categories will be added
	 * **/
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
		Vendor vendor = this.getVendorByName(name);
		if (vendor == null){ // new vendor
			vendor = new Vendor(name, description);
			try{
				PersistentService.getService().saveRecord(vendor);
			}
			catch(Exception e){
				throw new ApplicationGUIException(VendorManager.SAVE_VENDOR_FAIL_ERROR_MESSAGE);
			}
		}
		else {			// existing vendor
			List<Category> existingCategories = vendor.getCategories();
			categories.removeAll(existingCategories);
			vendor.setCategories(categories);
			try{
				PersistentService.getService().saveRecord(vendor);
			}
			catch(Exception e){
				throw new ApplicationGUIException(VendorManager.SAVE_VENDOR_FAIL_ERROR_MESSAGE);
			}
		}
		return vendor;
	}

}
