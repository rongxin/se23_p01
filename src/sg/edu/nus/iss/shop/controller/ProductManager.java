/**
 * @author  robincher
 */
package sg.edu.nus.iss.shop.controller;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class ProductManager {

	private static ProductManager theOnlyProductManager;

	private ProductManager() {

	}

	public static ProductManager getProductManager() {
		if (ProductManager.theOnlyProductManager == null) {
			ProductManager.theOnlyProductManager = new ProductManager();
		}
		return ProductManager.theOnlyProductManager;
	}
	
	/**
	 * Method to create Product by reading from data source
	 * @param category category object
	 * @param name product name
	 * @param avaiableQuantity product avaiableQuantity
	 * @param price product price
	 * @param barcodeNumber product barcode number
	 * @param orderThreshold product order threshold
	 * @param orderQuantity product order quantity
	 * @return product object           
	 * */
	public Product addProduct(Category category, String name,
			int avaiableQuantity, double price, String barcodeNumber,
			int orderThreshold, int orderQuantity)
			throws ApplicationGUIException {

		// Create new category
		return null;
	}
	
	/**
	 * Method to retrieve Product based on it's ID
	 * @param productId product ID
	 * @return product object           
	 * */
	public Product getProductById(String productId) {
		return null;
	}
	
	/**
	 * Method to retrieve Product based on its's barcode number
	 * @param barcodeNumber product barcode number
	 * @return product object           
	 * */
	public Product getProductByBarcode(String barcodeNumber) {
		return null;
	}
	
	/**
	 * Method to retrieve all products from data source
	 * @return listing of all products
	 * */
	public List<Product> getAllProducts() {
		return new LinkedList<Product>();
	}
	
	/**
	 * Method to retrieve all products from data source that has low inventory
	 * @return listing of product with low inventory    
	 * */
	public List<Product> getProductsWithLowInventory() {
		return new LinkedList<Product>();
	}
	
	/**
	 * Method to retrieve all products from data source for a particular category
	 * @return listing of product with low inventory    
	 * */
	public List<Product> getProductsForCategory(Category category) {
		
		//retrieve from data source where substring(persistentobj.substring(3) == category.getName();) 
		return new LinkedList<Product>();
	}
	
	/**
	 * Method to generateOrder for all products that have low inventory
	 * @param product product object (for product that has low inventory)
	 * @param vendor vendor object
	 * @return order number
	 * */
	public long generateOrder(Product product, Vendor vendor) {
		return 0;
	}
	
	/**
	 * Method to adjust product quantity upon successful checkout(Transaction completed)
	 * @param product product object 
	 * @param quantity quantity purchases
	 * @return product object
	 * */
	public Product adjustQuantity(Product product, int quantity) {
		return null;
	}
}
