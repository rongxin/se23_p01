package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

public class TransactionManagerTest {
	private TransactionManager tm;
	
	@Before
	public void setUp() throws Exception {
		tm = TransactionManager.getInstance();
	}
	
	@Test
	public void testSingleton() {
		TransactionManager tm2 = TransactionManager.getInstance();
		assertSame("Objects should be the same", tm, tm2);
	}

	@Test
	public void testStartTransaction(){
		
	}
	
	@Test
	public void testAddProducts(){
		
	}
	
	@Test
	public void testAdjustProduct(){
		
	}
	
	@Test
	public void testCancelTransaction(){
		
	}
	
	@Test
	public void testEndTransaction(){
		
	}

}
