/**
 * @author  robincher
 */
package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class ProductManager {

	private static ProductManager theOnlyProductManager;
	private static final String INVALID_NAME_ERROR_MESSAGE = "Invalid Name";
	private static final String INVALID_CATEGORY_ERROR_MESSAGE = "Invalid Category";
	private static final String INVALID_AVAILABLE_QUANTITY_ERROR_MESSAGE = "Invalid Available Quantity";
	private static final String INVALID_PRICE_ERROR_MESSAGE = "Invalid Price";
	private static final String INVALID_BARCODE_NUMBER_ERROR_MESSAGE = "Invalid Barcode Number";
	private static final String INVALID_ORDER_QUANTITY_ERROR_MESSAGE = "Invalid Order Quantity";
	private static final String INVALID_ORDER_THRESHOLD_ERROR_MESSAGE = "Invalid Order Threshold";
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
	 * @param barcodeNumber product Barcode number
	 * @param orderThreshold product order threshold
	 * @param orderQuantity product order quantity
	 * @return product object     
	 * @throws ApplicationGUIException Fields validation exceptions
	 * */
	public Product addProduct(Category category, String name,
			int avaiableQuantity, double price, String barcodeNumber,
			int orderThreshold, int orderQuantity)
			throws ApplicationGUIException {
		
		if (category == null){
			throw new ApplicationGUIException(ProductManager.INVALID_CATEGORY_ERROR_MESSAGE);
		}
		if (name == null || name.trim().length()< 0){
			throw new ApplicationGUIException(ProductManager.INVALID_NAME_ERROR_MESSAGE);
		}
		if (avaiableQuantity <= 0){
			throw new ApplicationGUIException(ProductManager.INVALID_AVAILABLE_QUANTITY_ERROR_MESSAGE );
		}
		if (price  <= 0){
			throw new ApplicationGUIException(ProductManager.INVALID_PRICE_ERROR_MESSAGE );
		}
		if (barcodeNumber== null || barcodeNumber.trim().length()< 0){
			throw new ApplicationGUIException(ProductManager.INVALID_BARCODE_NUMBER_ERROR_MESSAGE);
		}
		if (orderThreshold  <= 0){
			throw new ApplicationGUIException(ProductManager.INVALID_ORDER_THRESHOLD_ERROR_MESSAGE );
		}
		if (orderQuantity <= 0){
			throw new ApplicationGUIException(ProductManager.INVALID_ORDER_QUANTITY_ERROR_MESSAGE);
		}
		
		//Check if there an existing Product ID
		Product existingProduct = null;
		boolean existsProductId = true;
		int i = 1;
		while(existsProductId){
			//Check Product ID for duplication
			existingProduct = ProductManager.getProductManager().getProductById(category+"/"+Integer.toString(i));
			
			//Existing ProductID Found
			if (existingProduct != null) {
				i++;
			} else {
				//Insert into Data
				existsProductId = false;
			}
		}
		// Create new product
		return null;
	}
	
	/**
	 * Method to retrieve Product based on it's ID
	 * @param productId product ID
	 * @return product object   
	 * @throws ApplicationGUIException Exception while retrieving a product based on given productId        
	 * */
	public Product getProductById(String productId)throws ApplicationGUIException {
		Product existingProduct = null;
		try {
			existingProduct = (Product) PersistentService.getService().retrieveObject(Product.class, productId);
		}catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
		return existingProduct;
	}
	
	/**
	 * Method to retrieve Product based on its's barcode number
	 * @param barcodeNumber product barcode number
	 * @return product object           
	 * @throws ApplicationGUIException Exception while retrieving a product based on given barcode number
	 * */
	public Product getProductByBarcode(String barcodeNumber)throws ApplicationGUIException {
		Product existingProduct = null;
		try {
			existingProduct = (Product) PersistentService.getService().retrieveObject(Product.class, barcodeNumber);
		}catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
		return existingProduct;
	}
	
	/**
	 * Method to retrieve all products from data source
	 * @return listing of all products
	 * @throws ApplicationGUIException Exception while retrieving all products 
	 * */
	public List<Product> getAllProducts() throws ApplicationGUIException{
		List<Product> allProducts = new LinkedList<Product>();
		List<Object> objList = null;
		
		try {
			objList = PersistentService.getService().retrieveAll(Product.class);
		}catch (Exception e){
			throw new ApplicationGUIException(e.toString());
		}
		//Check if the objects are null or empty
		if(objList != null && !objList.isEmpty()) {
			Iterator<Object> it = objList.iterator();
			while (it.hasNext()) {
				 allProducts.add((Product) it.next());
			}
		} 
		return  allProducts;
	}
	
	/**
	 * Method to retrieve all products from data source that has low inventory
	 * @return listing of product with low inventory    
	 * @throws ApplicationGUIException Exception while retrieving all products 
	 * */
	public List<Product> getProductsWithLowInventory() throws ApplicationGUIException {
		//Retrieve all products
		List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
		List<Product> lowInventoryProducts = new LinkedList<Product>();
		
		if(!allProducts.isEmpty()) {
			Iterator<Product> it = allProducts.iterator();
			while (it.hasNext()) {
				//Compare Current Product Quantity against Order Threshold
				if(it.next().getAvailableQuantity() <= it.next().getOrderThreshold()) {
					lowInventoryProducts.add(it.next());
				}
			}	
		} 
		return lowInventoryProducts;
	}
	
	/**
	 * Method to retrieve all products from data source for a particular category
	 * @param category Category type for the products 
	 * @return listing of product with for a particular category
	 * @throws ApplicationGUIException Exception while retrieving all products 
	 * */
	public List<Product> getProductsForCategory(Category category) throws ApplicationGUIException {
		//Retrieve all products
		List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
		List<Product> productsWithCategory = new LinkedList<Product>();

		if(!allProducts.isEmpty()) {
			Iterator<Product> it = allProducts.iterator();
			String productID = "";
			while (it.hasNext()) {
				productID = it.next().getProductId();
				//Compare Current ProductID first 3 Characters with Category Code
				if(productID.substring(0,2).equals(category.getCode())) {
					productsWithCategory.add(it.next());
				}
			}	
		} 
		return productsWithCategory;
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
	 * @throws ApplicationGUIException Exception while retrieving a product based on given productId
	 * */
	public Product adjustQuantity(Product product, int quantity) throws ApplicationGUIException{
		Product existingProduct = ProductManager.getProductManager().getProductById(product.getProductId());
		if(existingProduct != null) {
			//Deduct product current quantity
			existingProduct.setAvailableQuantity(existingProduct.getAvailableQuantity() - quantity);
			//Save product with new quantity
			try {
				PersistentService.getService().saveRecord(existingProduct);
			}catch(Exception e){
				throw new ApplicationGUIException(e.toString());
			}
		} 
		return existingProduct;
	
	}

}
