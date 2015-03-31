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
	private Product existingProduct;
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
	public void testCreateProduct() throws ApplicationGUIException {
		newCategory = new Category("STA", "Stationary");
		existingProduct = ProductManager.getProductManager()
				.getProductByBarcode("2017");

		// Product already exists
		if (existingProduct != null) {
			assertTrue("Duplicate Barcode Number", existingProduct
					.getBarcodeNumber().equals("2017"));
		} else {
			try {
				testProduct = ProductManager.getProductManager().addProduct(
						newCategory, "Testing Product Beta",
						"Testing Product Beta #1", 100, 11.00, "2017", 30, 50);
				assertNotNull(testProduct.getProductId());
				assertEquals("2017", testProduct.getBarcodeNumber());
			} catch (Exception e) {
				fail("failed to create category");
			}
			assertNotNull(testProduct);
		}

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
		Product validProd = ProductManager.getProductManager()
				.getProductByBarcode("2017");
		assertNotNull(validProd.getBarcodeNumber(), validProd);
		assertEquals("2017", validProd.getBarcodeNumber());
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

	// Test Retrieve all Product that has low inventory
	@Test
	public void testRetrieveLowInvetoryProducts()
			throws ApplicationGUIException {
		List<Product> lowInventoryProducts = ProductManager.getProductManager()
				.getProductsWithLowInventory();
		assertNotNull(lowInventoryProducts);
		if (!lowInventoryProducts.isEmpty() && lowInventoryProducts != null) {
			assertNotNull(lowInventoryProducts);
			for (Product prod : lowInventoryProducts) {
				System.out.println(prod.getProductId());
				System.out.println("Current:" + prod.getAvailableQuantity());
				System.out.println("Threshold:" + prod.getOrderThreshold());
			}
		}

	}

	// Test Retrieve adjust product quantity
	public void testAdjustProductQuantity() throws ApplicationGUIException {
		int oldQty = 0;
		int deductQty = 10;
		// Retrieve Existing Product
		Product existingProduct = ProductManager.getProductManager()
				.getProductById("CLO/1");
		if (existingProduct != null) {
			assertNotNull("Prouct is Null", existingProduct);
			// Store Old Quantity in local var
			oldQty = existingProduct.getAvailableQuantity();
			// Adjust Quantity
			existingProduct = ProductManager.getProductManager()
					.adjustQuantity(existingProduct, deductQty);
			assertEquals(oldQty, existingProduct.getAvailableQuantity()
					+ deductQty);
		}
	}
}
