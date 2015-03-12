/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.controller;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.dao.PersistentService;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CategoryManager {

	private static CategoryManager theOnlyCategoryManager;
	private static final String CATEGORY_EXISTS_ERROR_MESSAGE = "Category already exists";
	private static final String INVALID_CODE_ERROR_MESSAGE = "Invalid Code";
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid Name";
	private static final int FIXED_CODE_LENGTH = 3;
	
	//Private Constructor to prevent Object instantiation
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
		//Check if category code is of 3 Character or null
		if (code == null || code.trim().length() != CategoryManager.FIXED_CODE_LENGTH) {
			throw new ApplicationGUIException(
					CategoryManager.INVALID_CODE_ERROR_MESSAGE);
		}
		//Check if category name is null 
		if (name == null
				|| name.trim().length() <= 0) {
			throw new ApplicationGUIException(
					CategoryManager.INVALID_NAME_ERROR_MESSAGE);
		}
		
		//Check if there's an existing category
		Category existingCategory = CategoryManager.getCategoryManager().getCategory(code);
		if (existingCategory != null) {
			throw new ApplicationGUIException(
					CategoryManager.CATEGORY_EXISTS_ERROR_MESSAGE);
		}
		//Save Category 
		try {
			PersistentService.getService().saveRecord(new Category(code,name));
			return new Category(code,name);
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}	
		
	}
	

	/**
	 * Method to retrieve category by code
	 * @param code category code 
	 * @return category (null or existing category)           
	 * */
	public Category getCategory(String code) {
		Category existingCategory = null;
		List<Category> allCategories = CategoryManager.getCategoryManager().
				getAllCategories();
		Iterator<Category> it = allCategories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			if (category.getCode().equals(code)) {
				existingCategory = category;
				return existingCategory;
			}
		}
		return existingCategory;
	}
	
	/**
	 * Method to retrieve all product categories
	 * @return all product categories           
	 * */
	public List<Category> getAllCategories(){
		return new LinkedList<Category>();
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
