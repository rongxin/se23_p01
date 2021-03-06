package sg.edu.nus.iss.shop.model.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TransactionTest {
	private Transaction t;
	Product product;
	
	private TransactionDetail findTransactionDetail(Product product,
			ArrayList<TransactionDetail> transactionDetails) {
		for (TransactionDetail transactionDetail : transactionDetails) {
			if (transactionDetail.getProduct() == product)
				return transactionDetail;
		}
		return null;
	}

	@Before
	public void setUp() throws Exception {
		t = new Transaction(1, new Member("1", "Oscar"), new Date());
		product = new Product("product", "product", "product", 1, 1, "1", 1, 1);
	}

	@Test
	public void testGettersSetters() {
		ArrayList<TransactionDetail> list = t.getTransactionDetails();
		assertNotNull("Transaction Details List should not be null", list);
		assertTrue("List should be Empty", list.isEmpty());
		assertNotSame("List should not be the same", list,
				t.getTransactionDetails());
	}

	@Test
	public void testAddingProducts() {
		try {
			t.changeProductQuantity(product, 1);
			ArrayList<TransactionDetail> list = t.getTransactionDetails();
			assertFalse("List should not be Empty", list.isEmpty());
			assertTrue(t.getTransactionDetails().size() >0);
			TransactionDetail transactionDetail = findTransactionDetail(
					product, list);
			assertEquals("Value should be the same",
					transactionDetail.getQuantity(), 1);
			t.changeProductQuantity(product, 5);
			list = t.getTransactionDetails();
			assertEquals("Value should be the same",
					list.get(list.indexOf(transactionDetail)).getQuantity(), 5);
			Product product2 = new Product("product2", "product2", "product2", 1, 1, "1", 1, 1);
			t.changeProductQuantity(product2, 10);
			assertEquals("List should not be the same", list.size(), 1);
			list = t.getTransactionDetails();
			assertEquals("List should now have 2", list.size(), 2);
		} catch (Exception e) {
			fail("Should not crash: " + e.getMessage());
		}
	}

	@Test
	public void testRemovingProducts() {
		try {
			t.changeProductQuantity(product, 1);
			ArrayList<TransactionDetail> list = t.getTransactionDetails();
			assertFalse("List should not be Empty", list.isEmpty());
			t.changeProductQuantity(product, 0);
			list = t.getTransactionDetails();
			assertTrue("List should be Empty", list.isEmpty());
		} catch (Exception e) {
			fail("should not crash");
		}
	}

	@Test
	public void testInvalidQuantity() {
		try {
			t.changeProductQuantity(product, -1);
			fail("Should crash");
		} catch (Exception e) {

		}
	}
	
	@Test
	public void XiangmingTest() throws Exception{
		Product product = new Product("CLO/1", "Centenary Jumper", "A releally nice momento", 1, 1, "1", 1, 1);
		Transaction trans = new Transaction(1, new Member("1", "Stacy"), new Date());
		trans.changeProductQuantity(product, 1);
		//TransactionDetail transDetail = new TransactionDetail(trans, product, 10); 
		
		assertTrue(trans.getTransactionDetails().size() == 1);
		//service.saveRecord(trans);

		Product product1 = new Product("CLO/2", "Centenary Jumper", "A releally nice momento", 1, 1, "1", 1, 1);
		Transaction trans1 = new Transaction(2, new Member("1", "Stacy"), new Date());
		trans1.changeProductQuantity(product, 1);
		trans1.changeProductQuantity(product1, 1);
//		TransactionDetail transDetail2 = new TransactionDetail(trans1, product, 10);
//		TransactionDetail transDetail3 = new TransactionDetail(trans1, product1, 1);
		assertTrue(trans1.getTransactionDetails().size() == 2);
		//service.saveRecord(trans1);
	}

}
