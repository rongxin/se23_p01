/**
 * @author  robincher
 */
package sg.edu.nus.iss.shop.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;
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
	public Product addProduct(Category category, String name, String description,
			int availableQuantity, double price, String barcodeNumber,
			int orderThreshold, int orderQuantity)
					throws ApplicationGUIException {

		Product newProduct;

		if (category == null){
			throw new ApplicationGUIException(ProductManager.INVALID_CATEGORY_ERROR_MESSAGE);
		}
		if (name == null || name.trim().length()< 0){
			throw new ApplicationGUIException(ProductManager.INVALID_NAME_ERROR_MESSAGE);
		}
		if (description == null || description.trim().length()< 0){
			throw new ApplicationGUIException(ProductManager.INVALID_NAME_ERROR_MESSAGE);
		}
		if (availableQuantity <= 0){
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

		//Retrieve existing products with the given category to be added
		List<Product> productsWithCategory= ProductManager.getProductManager().getProductsForCategory(category);
		int maxId = 0;
		//If no existing product for such category
		if( productsWithCategory.isEmpty() ) {
			newProduct = new Product(category.getCode()+"/1",name,description,
					availableQuantity,price,barcodeNumber,orderThreshold,orderQuantity);
		} else {
			Iterator<Product> it =  productsWithCategory.iterator();
			while (it.hasNext()) {
				int tempNum = Integer.parseInt(it.next().getProductId().substring(4)); // Code format eg MUG/1
				if (tempNum > maxId) {
					maxId = tempNum;
				}
			}
			newProduct = new Product(category.getCode()+"/"+(maxId+1),name,description,
					availableQuantity,price,barcodeNumber,orderThreshold,orderQuantity);
		}

		//Save Product
		try {
			PersistentService.getService().saveRecord(newProduct);
			return newProduct;
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
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
			existingProduct = PersistentService.getService().retrieveObject(Product.class, productId);
		}catch (Exception e) {
			e.printStackTrace();
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
		//Retrieve all products
		List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
		Product product = null;
		
		if(allProducts != null && !allProducts.isEmpty()) {
			for(Product prod : allProducts) {
				if(prod.getBarcodeNumber().equals(barcodeNumber)) {
					product = prod;
					break;
				}
			}
		}
		return product;
	}

	/**
	 * Method to retrieve all products from data source
	 * @return listing of all products
	 * @throws ApplicationGUIException Exception while retrieving all products
	 * */
	public List<Product> getAllProducts() throws ApplicationGUIException{
		List<Product> allProducts = new LinkedList<Product>();
		try {
			allProducts  = PersistentService.getService().retrieveAll(Product.class);
		}catch (Exception e){
			e.printStackTrace();
			throw new ApplicationGUIException(e.toString());
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

		if(allProducts != null && !allProducts.isEmpty()) {
			for(Product prod : allProducts) {
				if(prod.getAvailableQuantity() <= prod.getOrderThreshold()) {
					lowInventoryProducts.add(prod);	
				}
				
			}
		}
		return lowInventoryProducts;
	}
	
	/**
	 * Method to check if given products has low inventory
	 * @param productList List of product to be check if it has low inventory
	 * @return listing of product with low inventory
	 * @throws ApplicationGUIException Exception while retrieving all products
	 * */
	public List<Product> getProductsWithLowInventory(List<Product> productList) throws ApplicationGUIException {
		List<Product> lowInventoryProducts = new LinkedList<Product>();

		if(productList!= null && !productList.isEmpty()) {
			for(Product prod : productList) {
				//Refresh product to reflect their updated quantity upon checkout
				Product existingProduct = ProductManager.getProductManager().getProductById(prod.getProductId());
				if(existingProduct.getAvailableQuantity() <= existingProduct.getOrderThreshold()) {
					lowInventoryProducts.add(existingProduct);	
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

		if(!allProducts.isEmpty() && allProducts != null) {
			Iterator<Product> it = allProducts.iterator();
			String productID = "";
			while (it.hasNext()) {
				Product product = it.next();
				productID = product.getProductId();
				//Compare Current ProductID first 3 Characters with Category Code
				if(productID.substring(0,category.getCode().length()).equals(category.getCode())) {
					productsWithCategory.add(product);
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
				e.printStackTrace();
				throw new ApplicationGUIException(e.toString());
			}
		}
		return existingProduct;

	}

}
