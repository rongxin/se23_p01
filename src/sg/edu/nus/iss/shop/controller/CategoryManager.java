/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.controller;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

import java.util.LinkedList;
import java.util.List;

public class CategoryManager {

	private static CategoryManager theOnlyCategoryManager;

	private CategoryManager() {

	}

	public static CategoryManager getCategoryManager() {
		if (CategoryManager.theOnlyCategoryManager == null) {
			CategoryManager.theOnlyCategoryManager = new CategoryManager();
		}
		return CategoryManager.theOnlyCategoryManager;
	}
	
	
	/**
	 * Method to create category 
	 * @param code category code (MAX Input is 3 Character)
	 * @param name category name
	 * @return category object           
	 * */
	public Category createCategory(String code,String name) throws ApplicationGUIException {
		return null;
	}
	

	/**
	 * Method to retrieve category
	 * @param code category code 
	 * @return category object            
	 * */
	public Category getCategory(String code) {
		return null;
	}
	
	/**
	 * Method to retrieve vendors for a specific category
	 * @param code category code 
	 * @return vendor listing for a specific category type           
	 * */
	public List<Vendor> getVendorListForCategory(String code){
		return new LinkedList<Vendor>();
	}

}
