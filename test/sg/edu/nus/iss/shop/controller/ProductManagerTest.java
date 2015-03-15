package sg.edu.nus.iss.shop.controller;


import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.Before;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductManagerTest extends TestCase{
	private ProductManager productManager;
	Product testProduct;
	@Before
	public void setup() {
		
		productManager = ProductManager.getProductManager();
	}
	
	//Test if objects created are same
	@Test
    public void testObjectInitialization() {
		ProductManager newProductManager = productManager;
		assertSame("Objects should be the same",productManager,newProductManager);
	}
	
	//Test Create Product
	@Test
	public void testCreateProduct() {
		try { 
			testProduct = productManager.addProduct(new Category("STA","Stationary"),"Testing Product","Testing Product 123",100,11.00,"19111",30,50);
		} catch (Exception e) {
			fail("failed to create category");
		}	
		assertNotNull(testProduct);
	}	
	
	//Test Retrieve Product by Barcode Number
	@Test
	public void testRetrieveProductByBarcode() throws ApplicationGUIException {
			assertSame(testProduct,productManager.getProductByBarcode("19111"));
	}
	
	//Test retreive all Products
	@Test
	public void testRetrieveAllProducts() throws ApplicationGUIException {
		List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
		if (allProducts == null || allProducts.size() == 0 || allProducts.isEmpty()) {
			fail("Cannot find a category");
		}else {
			assertNotNull(allProducts);
		}
	}

}
