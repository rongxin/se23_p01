package sg.edu.nus.iss.shop.model.domain;

import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.shop.controller.MemberManager;

/**
 * 
 * @author Oscar Castro Araya
 * @version 1.0 Change into ArrayList because Rongxing doesn't want me to use hashmap :(
 * He only want to make my life in pain!!! :P
 * 
 */
public class Transaction {
	private int id;
	private Customer customer;
	private ArrayList<TransactionDetail> transactionDetails;
	private Date date;
	private double discount;
	private int loyaltyPointsUsed;

	public Transaction(int id, Date date){
		super();
		this.id = id;
		this.date = date;
		this.transactionDetails = new ArrayList<TransactionDetail>();
		this.loyaltyPointsUsed = 0;
	}
	
	public Transaction(int id, Customer customer, Date date) {
		this(id, date);
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public double getDiscount(){
		return this.discount;
	}
	
	public int getLoyaltyPointsUsed() {
		return loyaltyPointsUsed;
	}

	public void setLoyaltyPointsUsed(int loyaltyPointsUsed) {
		this.loyaltyPointsUsed = loyaltyPointsUsed;
	}
	
	/**
	 * 
	 * @return The total amount to pay before applying discount
	 */
	public double getTotalPrice(){
		double totalPrice = 0;
		for (TransactionDetail transactionDetail : this.transactionDetails) {
			totalPrice += transactionDetail.getTotalPrice();
		}
		return totalPrice;
	}
	
	/**
	 * 
	 * @return Final price after discount is applied.
	 */
	public double getFinalPrice(){
		return this.getTotalPrice() - this.discount;
	}

	/**
	 * Should return a copy of the list and not the actual list
	 * 
	 * @return copy of the list of transaction Details.
	 * @Bug 4Mar2015 Oscar Castro: Clone is only cloning the list, not the
	 *      actual items in the list. References are still changing. This is
	 *      causing an error in the test.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TransactionDetail> getTransactionDetails() {
		return (ArrayList<TransactionDetail>) transactionDetails.clone();
	}

	/**
	 * Update the list of products, if quantity is 0, we should remove the item
	 * from the list
	 * 
	 * @param product
	 * @param newQuantity
	 * @throws Exception
	 *             if newQuantity is 
	 *             <li>Less than 0 for existing products</li> 
	 *             <li>0 or less for new products</li>
	 * @BugFixed 4Mar2015 Oscar Castro: Item is not being deleted if quantity is
	 *           0
	 * @BugFixed 4Mar2015 Oscar Castro: Exception is not thrown for negative
	 *           quantity
	 */
	public void changeProductQuantity(Product product, int newQuantity)
			throws Exception {
		//Find Transaction Detail with the selected product
		TransactionDetail transactionDetail;
		transactionDetail = findTransactionDetail(product);
		
		if (transactionDetail != null) {
			if (newQuantity > 0) {
				transactionDetail.setQuantity(newQuantity);
			} else if (newQuantity == 0) {
				transactionDetails.remove(transactionDetail);
			} else {
				throw new Exception("New Quantity should not be less than 0");
			}
		} else {
			if (newQuantity > 0) {
				transactionDetails.add(new TransactionDetail(this,
						product, newQuantity));
			} else {
				throw new Exception("New Quantity should be more than 0");
			}
		}
		//return transactionDetail;
	}
	
	/**
	 * Find the product in the list of products
	 * @param product
	 * @return product found in the list or null if not found
	 * 
	 * @BugFixed 7Mar2015 Oscar Castro: Comparing the products references.
	 * Should it compare the product codes?
	 */
	private TransactionDetail findTransactionDetail(Product product){
		for (TransactionDetail transactionDetail : transactionDetails) {
		    if (transactionDetail.getProduct().getProductId() == product.getProductId())
		    	return transactionDetail;
		}
		return null;
	}
}
