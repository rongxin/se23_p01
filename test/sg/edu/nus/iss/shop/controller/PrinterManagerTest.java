package sg.edu.nus.iss.shop.controller;

import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Product;

public class PrinterManagerTest {
	
	@Test
	public void testPrintProduct() {
		PrinterManager.getInstance().printProductBarCode(new Product(null, null, null, 0, 0, "6325135152", 0, 0));
	}
}
