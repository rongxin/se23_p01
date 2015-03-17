package sg.edu.nus.iss.shop.controller;


import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductManagerTest extends TestCase{
	private ProductManager productManager;
	private Product testProduct;
	private Category category;
	@Before
	public void setup() throws Exception {
		category = new Category("STA","Stationary");
	
	}
	
	//Test if objects created are same
	@Test
    public void testObjectInitialization() {
		productManager = ProductManager.getProductManager();
		ProductManager newProductManager = productManager;
		assertSame("Objects should be the same",productManager,newProductManager);
	}
	
	//Test Create Product
	@Test
	public void testCreateProduct() {
		try { 
			testProduct = ProductManager.getProductManager().addProduct(category,"Testing Product","Testing Product 123",100,11.00,"19111",30,50);
			assertNotNull(testProduct.getProductId());
			assertEquals("19111",testProduct.getBarcodeNumber());
		} catch (Exception e) {
			fail("failed to create category");
		}	
		assertNotNull(testProduct);
	}	
	
	//Test Retrieve Product by Barcode Number
	@Test
	public void testRetrieveProductByBarcode() throws ApplicationGUIException {
			assertSame(testProduct, ProductManager.getProductManager().getProductByBarcode("19111"));
	}
	
	//Test Retrieve  all Products
	@Test
	public void testRetrieveAllProducts() throws ApplicationGUIException {
		List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
		if (allProducts == null || allProducts.size() == 0 || allProducts.isEmpty()) {
			fail("Cannot find a category");
		}else {
			assertNotNull(allProducts);
		}
	}
	
	//Test Retrieve all Products by Category
	@Test
	public void testRetrieveAllProductsByCategory() throws ApplicationGUIException {
		List<Product> allProducts = ProductManager.getProductManager().getProductsForCategory(category);
		assertNotNull(allProducts);
	}
	
	@After
	public void tearDown() throws Exception {
		category = null;
	}

}
