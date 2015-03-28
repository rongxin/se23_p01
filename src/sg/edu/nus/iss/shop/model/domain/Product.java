/**
 * @author  robincher
 */
package sg.edu.nus.iss.shop.model.domain;

import sg.edu.nus.iss.shop.controller.CategoryManager;

public class Product {

	private String productId;
	private String name;
	private String description;
	private int availableQuantity;
	private double price;
	private String barcodeNumber;
	private int orderThreshold;
	private int orderQuantity;
	private Category category;

	/**
	 * Constructor for Product
	 * @param productId product ID
	 * @param name product name
	 * @param description product description
	 * @param availableQuantity product available quantity
	 * @param price product price
	 * @param barcodeNumber product Barcode number
	 * @param orderThreshold product order threshold
	 * @param orderQuantity product order quantity
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
		return productId;
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
		return name;
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
		return description;
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
		return availableQuantity;
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
		return price;
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
		return barcodeNumber;
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
		return orderThreshold;
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
		return orderQuantity;
	}

	/**
	 * Setter Method for Product order quantity
	 * @param orderQuantity Product order quantity to be set
	 * */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcodeNumber == null) ? 0 : barcodeNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	/**
	 * Overriding equals() to compare two Complex objects
	 * @param object object to be compared
	 * @return boolean return true/false based on the comparison by product id, name and barcode number
	 * */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (barcodeNumber == null) {
			if (other.barcodeNumber != null)
				return false;
		} else if (!barcodeNumber.equals(other.barcodeNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	/**
	 * Method to get Category
	 * @return category for this Product      
	 * */
	public Category getCategory() {
		//Lazy load Category
		loadCategory();
		return this.category;
	}
	
	/**
	 * Method to set category for this product
	 * @param category for this product   
	 * */
	public void setCategory(Category category) {
 		this.category = category;
 	}
	
	/**
	 * Method to load category by calling CategoryManager
	 * */
	private void loadCategory() {
		this.category = null;
		try {
			//Retrieve Category for this particular category code
			category = CategoryManager.getCategoryManager().getCategory(this.getProductId().substring(0, getProductId().length()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setCategory(this.category);
	}
}
