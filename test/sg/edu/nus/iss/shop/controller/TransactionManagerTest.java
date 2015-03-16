package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Transaction;

public class TransactionManagerTest {
	private TransactionManager tm;
	private MemberManager mm;
	private ProductManager pm;
	private Transaction tr;

	@Before
	public void setUp() throws Exception {
		tm = TransactionManager.getInstance();
		mm = MemberManager.getMemberManager();
		pm = ProductManager.getProductManager();
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
}
