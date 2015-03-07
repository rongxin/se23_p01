package sg.edu.nus.iss.shop.controller;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorManager {
	
	private static VendorManager theOnlyVendorManager;
	
	private VendorManager(){
		
	}
	
	public static VendorManager getVendorManager(){
		if (VendorManager.theOnlyVendorManager == null){
			VendorManager.theOnlyVendorManager = new VendorManager();
		}
		return VendorManager.theOnlyVendorManager;
	}
	
	public List<Vendor> getAllVendors(){
		return new LinkedList<Vendor>();
	}
	
	public List<Vendor> listVendorForCategory(Category category){
		return new LinkedList<Vendor>();
	}
	
	public Vendor addVendor(String name, String description, List<Category> categories) throws ApplicationGUIException{
		return null;
	}
	
}
