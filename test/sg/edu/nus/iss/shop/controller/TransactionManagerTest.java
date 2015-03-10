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
		pm.addProduct(new Category("CAT", "CAT"), "product1", 100, 100, "1111", 10, 100);
	}
	
	@Test
	public void testSingleton() {
		TransactionManager tm2 = TransactionManager.getInstance();
		assertSame("Objects should be the same", tm, tm2);
	}

	@Test
	public void testNonCustomerTransaction(){
		tr = tm.StartTransaction();
		//Start transaction without Customer
		tr = tm.addProduct(tr, "1111", 1);
		assertNotNull("Object should not be null", tr);
		tr = tm.addProduct(tr, "1111", 1000);
		assertNull("Object should be null", tr);
	}
	
	@Test
	public void testCustomerTransaction(){
		
	}
	
	@Test
	public void testCancelTransaction(){
		tr = tm.StartTransaction();
		assertNotNull("Transaction should not be null", tr);
		tm.cancelTransaction(tr);
		//Lol
	}
}
