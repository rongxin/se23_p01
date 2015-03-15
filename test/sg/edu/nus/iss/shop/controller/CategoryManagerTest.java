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
		CategoryManager newCategoryManager = categoryManager;
		assertSame("Objects should be the same",categoryManager,newCategoryManager);
	}
	
	//Test Create Category 
	@Test
	public void testCreateCategory() {
		try { 
			Category cat= CategoryManager.getCategoryManager().createCategory("STA", "Sationary");
			assertNotNull(cat.getCode());
			assertEquals("STA",cat.getCode());
		} catch (Exception e) {
			fail("failed to create category" + ": " + e.toString());
		}
		
	}
	
	//Test Retrieve Invalid Category
	@Test
	public void testGetInvalidCategory() {
		Category newCategory = new Category("MUG", "Mugger");
		//Test Empty Category
		try { 
			assertNotSame(newCategory,categoryManager.getCategory("STA"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("failed to retrieve category" + ": " + e.toString());
		}	
	}
	
	//Test Retrieve Valid Category
	@Test
	public void testGetValidCategory() {
		Category newCategory = new Category("STA", "Sationary");
		try { 
			assertSame(newCategory,categoryManager.getCategory("STA"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("failed to retrieve category" + ": " + e.toString());
		}
	}
	
	//Test Retrieve all Category
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
