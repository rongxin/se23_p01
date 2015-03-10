package sg.edu.nus.iss.shop.controller;

import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;

/**
 * 
 * @author Oscar Castro Araya Singleton Class
 */
public class TransactionManager {
	private static TransactionManager transactionManager = new TransactionManager();

	private TransactionManager() {
		// Not public constructor allowed
	}

	public static TransactionManager getInstance() {
		return transactionManager;
	}

	public Transaction StartTransaction(Member member) {
		Transaction t = new Transaction(0, member, new Date());
		return t;
	}

	/**
	 * Is this for increasing the quantity? or adding a new product?
	 * @param transaction
	 * @param barCode
	 * @param quantity
	 * 
	 * @Bug 9Mar2015 Oscar Castro: Should return something more than a boolean
	 * 
	 */
	public boolean addProduct(Transaction transaction, String barCode, int quantity) {
		try {
			Product product;
			ProductManager pm = ProductManager.getProductManager();
			product = pm.getProductByBarcode(barCode);
			transaction.ChangeProductQuantity(product, quantity);
			return true;
		} catch (Exception e) {
			//We should return object message.
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * What is the difference with addProduct
	 * @param transaction
	 * @param barCode
	 * @param quantity
	 * @return
	 */
	public boolean adjustProduct(Transaction transaction, String barCode,
			int quantity) {
		try {
			Product product;
			ProductManager pm = ProductManager.getProductManager();
			product = pm.getProductByBarcode(barCode);
			transaction.ChangeProductQuantity(product, quantity);
			return true;
		} catch (Exception e) {
			//We should return object message.
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean cancelTransaction(Transaction transaction) {
		transaction = null;
		System.gc();
		return true;
	}
	
	/**
	 * Is that enough with double? Do you need something else?
	 * @param transaction
	 * @return
	 */
	public double checkOut(Transaction transaction){
		double total = 0;
		//Call Discount Manager to calculate the Discount.
		
		//Return the discount
		
		return 0;
	}

	/**
	 * Is boolean enough? Do you prefer something else?
	 * @param transaction
	 * @param cash
	 * @param loyaltyPoints
	 * @return
	 */
	public boolean endTransaction(Transaction transaction, double cash,
			int loyaltyPoints) {
		//Get the final price
		double total = checkOut(transaction);
		
		//validate price against cash and loyalty points.
		
		//Update Product DB
		
		//Update Transaction DB
		
		//Update Member DB
		return false;
	}
	
	public List<Transaction> getAllTransaction(Date startDate, Date endDate){
		return null;
	}
}
