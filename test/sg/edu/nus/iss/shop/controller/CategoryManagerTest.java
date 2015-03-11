/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.controller;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class CategoryManagerTest extends TestCase{
	private CategoryManager categoryManager;
	
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
		} catch (Exception e) {
			fail("failed to create category");
		}
	}
}
