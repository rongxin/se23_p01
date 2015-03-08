package sg.edu.nus.iss.shop.model.domain;

public class Product {

	private String productId;
	private String name;
	private String description;
	private int availableQuantity;
	private double price;
	private String barcodeNumber;
	private int orderThreshold;
	private int orderQuantity;

	/**
	 * Constructor for Product
	 */
	public Product(String productId, String name, String description,
			int availableQuantity, double price, String barcodeNumber,
			int orderThreshold, int orderQuantity) {

		this.productId = productId;
		this.name = name;
		this.description = description;
		this.availableQuantity = availableQuantity;
		this.price = price;
		this.barcodeNumber = barcodeNumber;
		this.orderThreshold = orderThreshold;
		this.orderQuantity = orderQuantity;
	}
	
	/**
	 * Method to get Product ID
	 * @return Product ID
	 * */
	public String getProductId() {
		return this.productId;
	}
	
	/**
	 * Setter Method for Product Id
	 * @param productId Product Id to be set
	* */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * Method to get Product name
	 * @return Product name
	 * */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter Method for Product name
	 * @param name Product name to be set
	* */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method to get Product description
	 * @return Product description
	 * */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Setter Method for Product description
	 * @param description Product description to be set
	* */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method to get Product current available quantity
	 * @return Product available quantity
	 * */
	public int getAvailableQuantity() {
		return this.availableQuantity;
	}
	
	/**
	 * Setter Method for Product available quantity
	 * @param availableQuantity Product available quantity to be set
	* */
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	/**
	 * Method to get Product price
	 * @return Product price
	 * */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Setter Method for Product price 
	 * @param price Product price to be set
	* */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Method to get Product Barcode number
	 * @return Product Barcode number
	 * */
	public String getBarcodeNumber() {
		return this.barcodeNumber;
	}
	
	/**
	 * Setter Method for Product Barcode number
	 * @param barcodeNumber Product Barcode number to be set
	* */
	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	
	/**
	 * Method to get Product order threshold
	 * @return Product order threshold
	 * */
	public int getOrderThreshold() {
		return this.orderThreshold;
	}
	
	/**
	 * Setter Method for Product order threshold
	 * @param orderThreshold Product order threshold to be set
	* */
	public void setOrderThreshold(int orderThreshold) {
		this.orderThreshold = orderThreshold;
	}
	
	/**
	 * Method to get Product order quantity
	 * @return Product order quantity
	 * */
	public int getOrderQuantity() {
		return this.orderQuantity;
	}
	
	/**
	 * Setter Method for Product order quantity
	 * @param orderQuantity Product order quantity to be set
	* */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
