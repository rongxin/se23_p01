package sg.edu.nus.iss.shop.controller;

import java.util.Date;
import sg.edu.nus.iss.shop.model.domain.Member;
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

	public static Transaction StartTransaction(Member member) {
		Transaction t = new Transaction(0, member, new Date());

		return t;
	}

	public static void addProduct(Transaction transaction, String product,
			int quantity) {

	}

	public static void adjustProduct(Transaction transaction, String product,
			int quantity) {

	}

	public static void cancelTransaction(Transaction transaction) {

	}

	public static void endTransaction(Transaction transaction, double cash,
			int loyaltyPoints) {

	}
}
