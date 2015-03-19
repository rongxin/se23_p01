package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.nondomain.TransactionRecord;
//import org.junit.Test;

//import sg.edu.nus.iss.shop.model.domain.Category;
//import sg.edu.nus.iss.shop.model.domain.Transaction;

public class TransactionManagerTest {
	private TransactionManager tm;
	PersistentService service;

	// private MemberManager mm;
	// private ProductManager pm;
	// private Transaction tr;

	@Before
	public void setUp() throws Exception {
		tm = TransactionManager.getInstance();
		service = PersistentService.getService();
		// mm = MemberManager.getMemberManager();
		// pm = ProductManager.getProductManager();
		// pm.addProduct(new Category("CAT", "CAT"), "product1", 100, 100,
		// "1111", 10, 100);
	}

	@Test
	public void testSingleton() {
		TransactionManager tm2 = TransactionManager.getInstance();
		assertSame("Objects should be the same", tm, tm2);
	}

	@Test
	public void testConvertCashToPoints() {
		int points = tm.convertCashToPoints(100);
		assertEquals("100 dollars should be 10 points", points, 10, 0);

		points = tm.convertCashToPoints(110);
		assertEquals("110 dollars should be 11 points", points, 11, 0);

		points = tm.convertCashToPoints(199);
		assertEquals("199 dollars should be 19 points", points, 19, 0);

		points = tm.convertCashToPoints(555);
		assertEquals("555 dollars should be 55 points", points, 55, 0);
	}

	@Test
	public void testConvertPointToCash() {
		int points = tm.convertPointsToCash(100);
		assertEquals("100 points should be 5 dollars", 5, points, 0);

		points = tm.convertPointsToCash(210);
		assertEquals("210 points should be 10 dollars", 10, points, 0);

		points = tm.convertPointsToCash(199);
		assertEquals("199 points should be 5 dollars", 5, points, 0);

		points = tm.convertPointsToCash(555);
		assertEquals("555 points should be 27 points", 25, points, 0);
	}

	@Test
	public void testMaxNumberOfPointsForAmount() {
		int points = tm.maxNumberOfPointsForAmount(4);
		assertEquals("4 dollars should need be 100 points", 100, points, 0);

		points = tm.maxNumberOfPointsForAmount(5);
		assertEquals("5 dollars should need be 100 points", 100, points, 0);

		points = tm.maxNumberOfPointsForAmount(6);
		assertEquals("6 dollars should need be 200 points", 200, points, 0);

		points = tm.maxNumberOfPointsForAmount(143);
		assertEquals("143 dollars should need be 2900 points", 2900, points, 0);
	}

	@Test
	public void testCalculateCashToPay() {
		double points = tm.calculateCashToPay(100, 4);
		assertEquals("100 points = $5 > 4$ return should be 0", 0, points, 0);

		points = tm.calculateCashToPay(100, 6);
		assertEquals("100 points = $5 < 6$ return should be 6 - 5 = 1", 1,
				points, 0);

		points = tm.calculateCashToPay(200, 4);
		assertEquals("200 points = $10 > 4$ return should be 0", 0, points, 0);

		points = tm.calculateCashToPay(300, 23);
		assertEquals("300 points = $15 < 23$ return should be 23 - 15 = 8", 8,
				points, 0);
	}

	@Test
	public void testTransactionRecords() throws ParseException {
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			TransactionRecord tr1 = new TransactionRecord(1, "clo/1", "11", 1,
					ft.parse("2015-03-14"));
			TransactionRecord tr2 = new TransactionRecord(1, "clo/2", "11", 1,
					ft.parse("2015-02-14"));
			TransactionRecord tr3 = new TransactionRecord(2, "clo/3", "11", 1,
					ft.parse("2015-01-14"));
			TransactionRecord tr4 = new TransactionRecord(3, "clo/4", "11", 1,
					ft.parse("2014-12-14"));

			List<TransactionRecord> list = new ArrayList<TransactionRecord>();
			list.add(tr1);
			list.add(tr2);
			list.add(tr3);
			list.add(tr4);

			// ArrayList<Transaction> l = tm.parseTransactions(list);
			// assertEquals("List should have 3 items", l.size(), 3);
			// Transaction t = l.get(0);
			// assertEquals("List should have 2 items",
			// t.getTransactionDetails()
			// .size(), 2);

			// l = tm.getAllTransaction(ft.parse("2015-01-13"),
			// ft.parse("2015-04-14"));
			// assertEquals("List should have 2 items", 2, l.size());
		} catch (Exception e) {
			fail("Fail!!");
		}
	}

	@Test
	public void testGetRangeTransactions() throws Exception {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Product product = new Product("CLO/1", "Centenary Jumper",
				"A releally nice momento", 1, 1, "1", 1, 1);
		Transaction trans = new Transaction(1, new Member("1", "Stacy"),
				ft.parse("2015-03-14"));
		trans.changeProductQuantity(product, 1);
		assertTrue(trans.getTransactionDetails().size() > 0);
		service.saveRecord(trans);

		Product product1 = new Product("CLO/2", "Centenary Jumper",
				"A releally nice momento", 1, 1, "1", 1, 1);
		Transaction trans1 = new Transaction(2, new Member("1", "Stacy"),
				ft.parse("2015-01-14"));
		trans1.changeProductQuantity(product, 1);
		trans1.changeProductQuantity(product1, 1);
		assertTrue(trans1.getTransactionDetails().size() > 0);
		service.saveRecord(trans1);

		ArrayList<Transaction> l;
		l = tm.getAllTransaction();
		assertEquals("List should have 2 items", 2, l.size());
		l = tm.getAllTransaction(ft.parse("2015-04-13"), ft.parse("2015-04-14"));
		assertEquals("Range List should have 1 items", 1, l.size());
	}
}
