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
			t.ChangeProductQuantity(product, 1);
			ArrayList<TransactionDetail> list = t.getTransactionDetails();
			assertFalse("List should not be Empty", list.isEmpty());

			TransactionDetail transactionDetail = findTransactionDetail(
					product, list);
			assertEquals("Value should be the same",
					transactionDetail.getQuantity(), 1);
			t.ChangeProductQuantity(product, 5);
			// assertNotEquals("List should not be the same",
			// list.get("Product").getQuantity(), 5);
			list = t.getTransactionDetails();
			assertEquals("Value should be the same",
					list.get(list.indexOf(transactionDetail)).getQuantity(), 5);
			Product product2 = new Product("product2", "product2", "product2", 1, 1, "1", 1, 1);
			t.ChangeProductQuantity(product2, 10);
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
			t.ChangeProductQuantity(product, 1);
			ArrayList<TransactionDetail> list = t.getTransactionDetails();
			assertFalse("List should not be Empty", list.isEmpty());
			t.ChangeProductQuantity(product, 0);
			list = t.getTransactionDetails();
			assertTrue("List should be Empty", list.isEmpty());
		} catch (Exception e) {
			fail("should not crash");
		}
	}

	@Test
	public void testInvalidQuantity() {
		try {
			t.ChangeProductQuantity(product, -1);
			fail("Should crash");
		} catch (Exception e) {

		}
	}

}
