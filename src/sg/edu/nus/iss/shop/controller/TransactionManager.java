package sg.edu.nus.iss.shop.controller;

import java.util.Date;
import java.util.List;

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

	private TransactionManager() {
		// Not public constructor allowed
	}

	public static TransactionManager getInstance() {
		return transactionManager;
	}

	/**
	 * Start a Transaction with a customer Member
	 * 
	 * @param customer
	 * @return
	 */
	public Transaction StartTransaction(Customer customer) {
		Transaction t = new Transaction(0, customer, new Date());
		return t;
	}

	/**
	 * Add product to the transaction and set the quantity to 1
	 * 
	 * @param transaction
	 * @param barCode
	 * 
	 * @BugFixed 9Mar2015 Oscar Castro: Should return something more than a
	 *           boolean
	 * 
	 */
	public Transaction addProduct(Transaction transaction, String barCode) {
		return editProductQuantity(transaction, barCode, 1);
	}

	/**
	 * Set the new quantity of the product If product doesn't exist it creates
	 * the product with the new quantity.
	 * 
	 * @param transaction
	 * @param barCode
	 * @param quantity
	 * 
	 * @BugFixed 9Mar2015 Oscar Castro: Should return something more than a
	 *           boolean
	 * 
	 */
	public Transaction editProductQuantity(Transaction transaction,
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
	 * 
	 * @param transaction
	 * @return
	 */
	public boolean cancelTransaction(Transaction transaction) {
		// transaction = null;
		// System.gc();
		return true;
	}

	/**
	 * Is that enough with double? Do you need something else?
	 * 
	 * @param transaction
	 * @return
	 */
	public double checkOut(Transaction transaction) {
		double total = 0;
		// Call Discount Manager to calculate the Discount.
		DiscountManager dm;
		// dm = DiscountManager.getInstance();

		// Return the discount

		return 0;
	}

	/**
	 * Is boolean enough? Do you prefer something else?
	 * 
	 * @param transaction
	 * @param cash
	 * @param loyaltyPoints
	 * @return
	 * @throws ApplicationGUIException 
	 */
	public Transaction endTransaction(Transaction transaction, double cash,
			int loyaltyPoints) throws ApplicationGUIException {
		// Get the final price
		double total = checkOut(transaction);

		// validate price against cash and loyalty points.

		// Update Product DB
		updateProductsInTransactioDetails(transaction);

		// Update Transaction DB

		// Update Member DB
		updateMemberPoints(transaction, loyaltyPoints, cash);
		return null;
	}

	public List<Transaction> getAllTransaction(Date startDate, Date endDate) {
		return null;
	}

	private boolean updateProductsInTransactioDetails(Transaction transaction) throws ApplicationGUIException {
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
			int loyaltyPoints, double cash) {
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
			return false;
		}
	}
}
