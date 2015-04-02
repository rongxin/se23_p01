package sg.edu.nus.iss.shop.model.domain;

/**
 * 
 * @author Oscar Castro Araya
 * @version 1.0
 * Class to store a single line of product in the invoice.
 */
public class TransactionDetail {
	private Transaction transaction;
	private Product product;
	private int quantity;
	
	/**
	 * Default Constructor
	 * @param transaction
	 * @param product
	 * @param quantity
	 */
	public TransactionDetail(Transaction transaction, Product product, int quantity) {
		this.transaction = transaction;
		this.product = product;
		this.quantity = quantity;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice(){
		return this.quantity * this.product.getPrice();
	}
}
