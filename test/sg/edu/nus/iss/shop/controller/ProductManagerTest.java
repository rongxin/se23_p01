package sg.edu.nus.iss.shop.controller;


import junit.framework.TestCase;

import org.junit.Test;
import org.junit.Before;

import sg.edu.nus.iss.shop.model.domain.Category;

public class ProductManagerTest extends TestCase{
	private ProductManager productManager;
	
	@Before
	public void setup() {
		 productManager = ProductManager.getProductManager();
	}
	
	//Test if objects created are same
	@Test
    public void testObjectInitialization() {
		ProductManager newProductManager = ProductManager.getProductManager();
		assertSame("Objects should be the same",productManager,newProductManager);
	}
	
	//Test Create Product
	@Test
	public void testCreateProduct() {
		try { 
			 productManager.addProduct(new Category("STA","Stationary"),"Testing Product",100,11.00,"19111",30,50);
		} catch (Exception e) {
			fail("failed to create category");
		}
	}	

}
