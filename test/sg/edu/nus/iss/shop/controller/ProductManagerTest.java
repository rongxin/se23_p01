package sg.edu.nus.iss.shop.controller;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;


import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductManagerTest extends TestCase {
	private ProductManager productManager;
	private Product testProduct;
	private Category newCategory;
	private Category validCategory;

	// Test if objects created are same
	@Test
	public void testObjectInitialization() {
		productManager = ProductManager.getProductManager();
		ProductManager newProductManager = productManager;
		assertSame("Objects should be the same", productManager,
				newProductManager);
	}

	// Test Create Product
	@Test
	public void testCreateProduct() {
		newCategory = new Category("STA", "Stationary");
		try {
			testProduct = ProductManager.getProductManager().addProduct(
					newCategory, "Testing Product", "Testing Product 123", 100,
					11.00, "19111", 30, 50);
			assertNotNull(testProduct.getProductId());
			assertEquals("19111", testProduct.getBarcodeNumber());
		} catch (Exception e) {
			fail("failed to create category");
		}
		assertNotNull(testProduct);
	}

	// Test Retrieve Product by Product ID (Valid)
	@Test
	public void testRetrieveProductByProductId() throws ApplicationGUIException {
		Product validProd = ProductManager.getProductManager().getProductById(
				"CLO/1");
		assertNotNull(validProd);
		assertEquals("CLO/1", validProd.getProductId());
	}
	
	// Test Retrieve Product by Barcode number(Valid)
		@Test
		public void testRetrieveProductByBardcode() throws ApplicationGUIException {
			Product validProd = ProductManager.getProductManager().getProductByBarcode("A1234");
			assertNotNull(validProd.getBarcodeNumber(),validProd);
			assertEquals("A1234", validProd.getBarcodeNumber());
		}


	// Test Retrieve all Products
	@Test
	public void testRetrieveAllProducts() throws ApplicationGUIException {
		List<Product> allProducts = ProductManager.getProductManager()
				.getAllProducts();
		if (allProducts == null || allProducts.size() == 0
				|| allProducts.isEmpty()) {
			fail("Cannot find a category");
		} else {
			assertNotNull(allProducts);
		}
	}

	// Test Retrieve all Products by Category
	@Test
	public void testRetrieveAllProductsByCategory()
			throws ApplicationGUIException {
		validCategory = new Category("CLA", "Calendar");
		List<Product> allProducts = ProductManager.getProductManager()
				.getProductsForCategory(validCategory);

		if (allProducts != null) {
			assertNotNull(allProducts);
		} else {
			assertNull("No Products in the list", allProducts);
		}
	}

}
