package sg.edu.nus.iss.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;
import sg.edu.nus.iss.shop.model.nondomain.TransactionRecord;

/**
 * 
 * @author Oscar Castro Araya Singleton Class
 */
public class TransactionManager {
	private static TransactionManager transactionManager = new TransactionManager();
	private static final double RATE_CASH_TO_POINTS = 10;
	private static final double RATE_POINTS_TO_CASH = 20;
	private static final double RATE_POINTS_TO_CASH_MIN_POINTS = 100;

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
	 * Return conversion of points earn by using the cash amount
	 * 
	 * @param cash
	 *            Amount to be converted to points
	 * @return number of points earn by paying the cash amount
	 */
	public int convertCashToPoints(double cash) {
		double points = 0;
		points = cash / RATE_CASH_TO_POINTS;

		return (int) Math.floor(points);
	}

	/**
	 * Return the cash amount can be redeemed by the number of points pass in
	 * the method
	 * 
	 * @param points
	 *            Number of points to be converted to cash amount
	 * @return The cash earn
	 */
	public double convertPointsToCash(int points) {
		// Add the IF part if you want to validate a 100 points based number
		/*
		 * if (points % 100 != 0){ throw new
		 * Exception("Points should be 100 based number"); }
		 */

		// Remove the non 100 points value
		// Disabled this if you don't want to use the 100 based points value
		// points = (int) (Math.floor(points / RATE_POINTS_TO_CASH_MIN_POINTS) *
		// RATE_POINTS_TO_CASH_MIN_POINTS);

		// Change value to double if you don't want to use the 100 based points
		// value
		double cash = (double) (points / RATE_POINTS_TO_CASH);
		return cash;
	}

	/**
	 * Calculate the max number of points that can be used to pay the amount
	 * pass in the method
	 * 
	 * @param amount
	 *            To be calculated in term of points
	 * @return max number of points that can be redeemed
	 */
	public int maxNumberOfPointsForAmount(double amount) {
		int points = 0;
		points = (int) (amount * RATE_POINTS_TO_CASH);
		points = (int) Math.ceil(points / RATE_POINTS_TO_CASH_MIN_POINTS);
		points = (int) (points * RATE_POINTS_TO_CASH_MIN_POINTS);

		return points;
	}

	/**
	 * Calculate the Amount to be pay by using the certain number of points for
	 * an specific amount
	 * 
	 * @param loyalPointsUsed
	 *            Points to be redeemed
	 * @param finalAmount
	 *            Total price before redeeming
	 * @return Amount to be pay after redeeming the points
	 */
	public double calculateCashToPay(int loyalPointsUsed, double finalAmount) {
		double cash;
		cash = convertPointsToCash(loyalPointsUsed);
		// int maxPoints = maxNumberOfPointsForAmount(finalAmount);
		// System.out.println(maxPoints);
		// if (maxPoints <= loyalPointsUsed) {
		if (finalAmount <= cash) {
			// throw new Exception(
			// "You don't need to use that many amount of points you only need: "
			// + maxPoints);
			return 0;
		}

		return finalAmount - cash;
	}

	/**
	 * End the transaction and update the DB
	 * 
	 * @param customer
	 * @param products
	 * @param discount
	 * @param loyalPointsUsed
	 * @return
	 * @throws Exception
	 */
	public Transaction endTransaction(Customer customer,
			Hashtable<Product, Integer> products, double discountedAmount,
			int loyalPointsUsed) throws Exception {
		// Getting the all transaction to get the next transaction ID
		ArrayList<Transaction> list = getAllTransaction();
		Transaction t = new Transaction(list.size() + 1, new Date());

		// Setting the customer.
		t.setCustomer(customer);
		t.setDiscount(discountedAmount);
		t.setLoyaltyPointsUsed(loyalPointsUsed);

		// Setting the transaction details.
		for (Product key : products.keySet()) {
			t.changeProductQuantity(key, products.get(key));
		}
		t.setCashPayed(calculateCashToPay(loyalPointsUsed, t.getFinalPrice()));

		endTransaction(t);
		return t;
	}

	/**
	 * Update the DB following the below <li>The products quantity <li>The
	 * Transactions <li>Member Points
	 * 
	 * @param transaction
	 * @throws ApplicationGUIException
	 */
	private void endTransaction(Transaction transaction)
			throws ApplicationGUIException {
		// Update Product DB
		updateProductsInTransactioDetails(transaction);

		// Update Transaction DB
		updateTransaction(transaction);

		// Update Member DB
		updateMemberPoints(transaction);
	}

	/**
	 * Update the Member Points
	 * 
	 * @param transaction
	 * @return true if success
	 * @throws ApplicationGUIException
	 */
	private boolean updateMemberPoints(Transaction transaction)
			throws ApplicationGUIException {
		try {
			// This is not OO at all
			if (transaction.getCustomer() instanceof Member) {
				MemberManager mm = MemberManager.getMemberManager();
				Member m = (Member) transaction.getCustomer();

				mm.reduceLoyalPoints(m, transaction.getLoyaltyPointsUsed());
				// System.out.println(transaction.getCashPayed());
				int points = convertCashToPoints(transaction.getCashPayed());
				// System.out.println(points);
				mm.adjustLoyalPoints(m, -(points));
			}
			return true;
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Add the Transaction to the DB
	 * 
	 * @param transaction
	 * @return
	 * @throws ApplicationGUIException
	 */
	private boolean updateTransaction(Transaction transaction)
			throws ApplicationGUIException {
		try {
			// Update Transaction DB
			PersistentService.getService().saveRecord(transaction);
			return true;
		} catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
	}

	/**
	 * Update the Products quantity
	 * 
	 * @param transaction
	 * @return
	 * @throws ApplicationGUIException
	 */
	private boolean updateProductsInTransactioDetails(Transaction transaction)
			throws ApplicationGUIException {
		try {
			ProductManager pm = ProductManager.getProductManager();
			for (TransactionDetail transactionDetail : transaction
					.getTransactionDetails()) {
				pm.adjustQuantity(transactionDetail.getProduct(),
						transactionDetail.getQuantity());
				// System.out.println(transactionDetail.getProduct() + " " +
				// transactionDetail.getQuantity());
			}
			return true;
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Parse List of TransactionRecord to List of Transactions
	 * 
	 * @param transList
	 * @return
	 * @throws ApplicationGUIException
	 */
	private ArrayList<Transaction> parseTransactions(
			List<TransactionRecord> transList) throws ApplicationGUIException {
		// Oscar: Using a hash to maintain the Transaction ID
		HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
		TransactionRecord transRecord;
		for (Object tr : transList) {
			transRecord = (TransactionRecord) tr;
			//System.out.println("Parsing " + transRecord.getProductId());
			// Oscar: Adding the transaction to the list of transactions.
			if (!transactions.containsKey(transRecord.getId())) {
				// Transaction doesn't exist create a new one.
				Transaction t = new Transaction(transRecord.getId(),
						transRecord.getTransactionDate());
				Customer m = MemberManager.getMemberManager().getMemberById(transRecord.getCustomerId());
				if (m == null){
					m = MemberManager.getMemberManager().generateNonMember();
				}
				t.setCustomer(m);
				
				transactions.put(t.getId(), t);
				
				// } else {
				// Transaction exists in hash, do nothing
			}
			try {
				// Get the product
				Product p = ProductManager.getProductManager().getProductById(
						transRecord.getProductId());
				// This guy is Throwing a generic Exception, need to change to a
				// more defined Exception
				//System.out.println("Product " + p);
				// Update the transaction with the product and quantity.
				transactions.get(transRecord.getId()).changeProductQuantity(p,
						transRecord.getQuantity());
			} catch (ApplicationGUIException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("No product found: "
						+ transRecord.getProductId());
				throw e;
			}
		}
		ArrayList<Transaction> finalList = new ArrayList<Transaction>();
		for (Transaction t : transactions.values()) {
			finalList.add(t);
		}
		return finalList;
	}

	/**
	 * Return all Transactions in the DB
	 * 
	 * @return
	 * @throws ApplicationGUIException
	 */
	public ArrayList<Transaction> getAllTransaction()
			throws ApplicationGUIException {
		try {
			List<TransactionRecord> transList;
			transList = PersistentService.getService().retrieveAll(
					Transaction.class);
			return parseTransactions(transList);
		} catch (IOException | InvalidDataFormat | InvalidDomainObject e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Should I return null or throw exception??
			// return null;
			throw new ApplicationGUIException(e.getMessage());
		}
	}

	/**
	 * Return all transactions applicable for the specific range.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ApplicationGUIException
	 */
	public ArrayList<Transaction> getAllTransaction(Date startDate, Date endDate)
			throws ApplicationGUIException {
		ArrayList<Transaction> allTransaction = getAllTransaction();
		ArrayList<Transaction> rangeTransactions = new ArrayList<Transaction>();

		for (Transaction t : allTransaction) {
			if (startDate.before(t.getDate()) && endDate.after(t.getDate())) {
				rangeTransactions.add(t);
				// System.out.println("inc " + startDate + " < " + t.getDate() +
				// " < " + endDate);
			} else {
				// System.out.println("exc " + startDate + " < " + t.getDate() +
				// " < " + endDate);
			}
		}

		return rangeTransactions;
	}
}
