package sg.edu.nus.iss.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
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
	public int convertPointsToCash(int points) {
		// Add the IF part if you want to validate a 100 points based number
		/*
		 * if (points % 100 != 0){ throw new
		 * Exception("Points should be 100 based number"); }
		 */

		// Remove the non 100 points value
		// Disabled this if you don't want to use the 100 based points value
		points = (int) (Math.floor(points / RATE_POINTS_TO_CASH_MIN_POINTS) * RATE_POINTS_TO_CASH_MIN_POINTS);

		// Change value to double if you don't want to use the 100 based points
		// value
		int cash = (int) (points / RATE_POINTS_TO_CASH);
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
	public double calculateCashToPay(int loyalPointsUsed, double finalAmount){
		double cash;
		cash = convertPointsToCash(loyalPointsUsed);
		int maxPoints = maxNumberOfPointsForAmount(finalAmount);
		if (maxPoints < loyalPointsUsed) {
			//throw new Exception(
			//		"You don't need to use that many amount of points you only need: "
			//				+ maxPoints);
		}

		return finalAmount - cash;
	}

	/**
	 * End the transaction and update the DB
	 * @param customer
	 * @param products
	 * @param discount
	 * @param loyalPointsUsed
	 * @return
	 * @throws Exception
	 */
	public Transaction endTransaction(Customer customer,
			Hashtable<Product, Integer> products, int discount,
			int loyalPointsUsed) throws Exception {
		// Getting the all transaction to get the next transaction ID
		ArrayList<Transaction> list = getAllTransaction();
		Transaction t = new Transaction(list.size() + 1, new Date());

		// Setting the customer.
		t.setCustomer(customer);
		t.setDiscount(discount);
		t.setLoyaltyPointsUsed(loyalPointsUsed);
		t.setCashPayed(calculateCashToPay(loyalPointsUsed, t.getFinalPrice()));

		// Setting the transaction details.
		for (Product key : products.keySet()) {
			t.changeProductQuantity(key, products.get(key));
		}

		endTransaction(t);
		return t;
	}

	private void endTransaction(Transaction transaction)
			throws ApplicationGUIException {
		// Update Product DB
		updateProductsInTransactioDetails(transaction);

		// Update Transaction DB
		updateTransaction(transaction);

		// Update Member DB
		updateMemberPoints(transaction);
	}

	private boolean updateMemberPoints(Transaction transaction)
			throws ApplicationGUIException {
		try {
			// This is not OO at all
			if (transaction.getCustomer() instanceof Member) {
				MemberManager mm = MemberManager.getMemberManager();
				Member m = (Member) transaction.getCustomer();

				mm.reduceLoyalPoints(m, transaction.getLoyaltyPointsUsed());

				int points = convertCashToPoints(transaction.getCashPayed());
				mm.adjustLoyalPoints(m, points);
			}
			return true;
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

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

	public ArrayList<Transaction> getAllTransaction() {
		try {
			PersistentService.getService().retrieveAll(Transaction.class);
			return new ArrayList<Transaction>();
		} catch (IOException | InvalidDataFormat | InvalidDomainObject e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Transaction> getAllTransaction(Date startDate, Date endDate) {
		ArrayList<Transaction> allTransaction = getAllTransaction();
		ArrayList<Transaction> rangeTransactions = new ArrayList<Transaction>();

		for (Transaction t : allTransaction) {
			if (startDate.before(t.getDate()) && endDate.after(t.getDate())) {
				rangeTransactions.add(t);
			}
		}

		return rangeTransactions;
	}
}
