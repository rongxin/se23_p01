package sg.edu.nus.iss.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;

/**
 * 
 * @author Oscar Castro Araya Singleton Class
 */
public class TransactionManager {
	private static TransactionManager transactionManager = new TransactionManager();
	private static final double RATE_CASH_TO_POINTS = 10;
	private static final double RATE_POINTS_TO_CASH = 20;
	private static final double RATE_POINTS_TO_CASH_MIN = 100;
	
	private TransactionManager() {
		// Not public constructor allowed
	}

	/**
	 * 
	 * @return
	 */
	public static TransactionManager getInstance() {
		return transactionManager;
	}

	/**
	 * Start a Transaction with a customer Member
	 * @deprecated
	 * @param customer
	 * @return
	 */
	private Transaction StartTransaction(Customer customer) {
		Transaction t = new Transaction(0, customer, new Date());
		return t;
	}

	/**
	 * Add product to the transaction and set the quantity to 1
	 * 
	 * @param transaction
	 * @param barCode
	 * @deprecated
	 * @BugFixed 9Mar2015 Oscar Castro: Should return something more than a
	 *           boolean
	 * 
	 */
	private Transaction addProduct(Transaction transaction, String barCode) {
		return editProductQuantity(transaction, barCode, 1);
	}

	/**
	 * Set the new quantity of the product If product doesn't exist it creates
	 * the product with the new quantity.
	 * 
	 * @param transaction
	 * @param barCode
	 * @param quantity
	 * @deprecated
	 * @BugFixed 9Mar2015 Oscar Castro: Should return something more than a
	 *           boolean
	 * 
	 */
	private Transaction editProductQuantity(Transaction transaction,
			String barCode, int quantity) {
		try {
			Product product;
			ProductManager pm = ProductManager.getProductManager();
			product = pm.getProductByBarcode(barCode);
			if (product.getAvailableQuantity() > quantity) {
				transaction.changeProductQuantity(product, quantity);
			} else {
				return null;
			}
			return transaction;
		} catch (Exception e) {
			// We should return object message.
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * I don't know what to do here, lol
	 * @deprecated
	 * @param transaction
	 * @return
	 */
	private boolean cancelTransaction(Transaction transaction) {
		// transaction = null;
		// System.gc();
		return true;
	}

	/**
	 * Is that enough with double? Do you need something else?
	 * @deprecated
	 * @param transaction
	 * @return
	 */
	private double checkOut(Transaction transaction) {
		// double total = 0;
		// Call Discount Manager to calculate the Discount.
		// DiscountManager dm;
		// dm = DiscountManager.getInstance();

		// Return the discount

		return 0;
	}
	
	public int convertCashToPoints(double cash){
		double points = 0;
		points = cash / RATE_CASH_TO_POINTS;
			
		return (int) Math.floor(points);
	}
	
	public int convertPointsToCash(int points){
		//Add the IF part if you want to validate a 100 points based number
		/*
		if (points % 100 != 0){
			throw new Exception("Points should be 100 based number");
		}
		*/
		
		//Remove the non 100 points value
		//Disabled this if you don't want to use the 100 based points value
		points = (int) (Math.floor(points / RATE_POINTS_TO_CASH_MIN) * RATE_POINTS_TO_CASH_MIN);
		
		points = (int) (points / RATE_POINTS_TO_CASH);
		return 0;
	}
	
	/**
	 * Return the transaction
	 * 
	 * @param transaction
	 * @param cash
	 * @param loyaltyPoints
	 * @return
	 * @throws ApplicationGUIException
	 */
	private Transaction endTransaction(Transaction transaction, double cash)
			throws ApplicationGUIException {
		// Get the final price
		// double total = checkOut(transaction);

		// Update Product DB
		updateProductsInTransactioDetails(transaction);

		// Update Transaction DB

		// Update Member DB
		updateMemberPoints(transaction, transaction.getLoyaltyPointsUsed(), cash);
		return null;
	}

	public Transaction entTransaction(Customer customer,
			Hashtable<Product, Integer> products, int discount,
			double payByCash, int loyalPointsUsed) throws Exception {
		// Getting the all transaction to get the next transaction ID
		ArrayList<Transaction> list = getAllTransaction();
		Transaction t = new Transaction(list.size() + 1, new Date());

		// Setting the customer.
		t.setCustomer(customer);
		t.setDiscount(discount);
		t.setLoyaltyPointsUsed(loyalPointsUsed);

		for (Product key : products.keySet()) {
			t.changeProductQuantity(key, products.get(key));
		}
		
		endTransaction(t, payByCash);
		return t;
	}

	public ArrayList<Transaction> getAllTransaction() {
		return new ArrayList<Transaction>();
	}

	public ArrayList<Transaction> getAllTransaction(Date startDate, Date endDate) {
		return new ArrayList<Transaction>();
	}

	private boolean updateProductsInTransactioDetails(Transaction transaction)
			throws ApplicationGUIException {
		try {
			ProductManager pm = ProductManager.getProductManager();
			for (TransactionDetail transactionDetail : transaction
					.getTransactionDetails()) {
				pm.adjustQuantity(transactionDetail.getProduct(),
						transactionDetail.getQuantity());

			}
			return true;
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	private boolean updateMemberPoints(Transaction transaction,
			int loyaltyPoints, double cash) throws ApplicationGUIException {
		try {
			// This is not OO at all
			if (transaction.getCustomer() instanceof Member) {
				MemberManager mm = MemberManager.getMemberManager();
				Member m = (Member) transaction.getCustomer();

				mm.reduceLoyalPoints(m, loyaltyPoints);

				// mm.increaseMemberPoints(transaction.getCustomer(), cash);
			}
			return true;
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public double ValidatePayment(int loyalPointsUsed, int finalAmount){
		double convertedCashFromLoyalPoints;
		convertedCashFromLoyalPoints = loyalPointsUsed / RATE_POINTS_TO_CASH;
		
		return finalAmount - convertedCashFromLoyalPoints;
	}
}
