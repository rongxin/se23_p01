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
		//pm.addProduct(new Category("CAT", "CAT"), "product1", 100, 100, "1111", 10, 100);
	}
	
	@Test
	public void testSingleton() {
		TransactionManager tm2 = TransactionManager.getInstance();
		assertSame("Objects should be the same", tm, tm2);
	}

	@Test
	public void testConvertCashToPoints(){
		int points = tm.convertCashToPoints(100);
		assertEquals("100 points should be 10 points", points, 10, 0);
		
		points = tm.convertCashToPoints(110);
		assertEquals("110 points should be 11 points", points, 11, 0);
		
		points = tm.convertCashToPoints(199);
		assertEquals("199 points should be 19 points", points, 19, 0);
		
		points = tm.convertCashToPoints(555);
		assertEquals("555 points should be 55 points", points, 55, 0);
	}
	
	@Test
	public void testCustomerTransaction(){
		
	}
	
	@Test
	public void testCancelTransaction(){
		/*tr = tm.StartTransaction();
		assertNotNull("Transaction should not be null", tr);
		tm.cancelTransaction(tr);
		//Lol*/
	}
}
