/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.controller;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;


public class CategoryManagerTest extends TestCase{
	private static CategoryManager categoryManager;
	
	@Before
	public void setup() {
		categoryManager = CategoryManager.getCategoryManager();
	}
	
	//Test if objects created are same
	@Test
    public void testObjectInitialization() {
		CategoryManager newCategoryManager = CategoryManager.getCategoryManager();
		assertSame("Objects should be the same",categoryManager,newCategoryManager);
	}
	
	//Test Create Category 
	@Test
	public void testCreateCategory() {
		try { 
			categoryManager.createCategory("STA", "Sationary");
			assertNotNull(categoryManager.getCategory("STA"));
		} catch (Exception e) {
			fail("failed to create category" + ": " + e.toString());
		}
		
	}
	
	//Test Create Category
	@Test
	public void testGetCategory() {
		//Test Null Category
		try { 
			assertNull(categoryManager.getCategory("XXX"));
		} catch (Exception e) {
			fail("failed to create category" + ": " + e.toString());
		}
		//Test Valid Category
		try { 
			assertNotNull(categoryManager.getCategory("1234"));
		} catch (Exception e) {
			fail("failed to create category" + ": " + e.toString());
		}
	}
	
	//Test retreive all Category
	@Test
	public void testRetrieveAllCategories() throws ApplicationGUIException {
		List<Category> categories = CategoryManager.getCategoryManager().getAllCategories();
		if (categories == null || categories.size() == 0 || categories.isEmpty()) {
			fail("Cannot find a category");
		}else {
			assertNotNull(categories);
		}
	}

}
