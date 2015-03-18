package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ProductScannedActionListener extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private BarcodeScannerEmulatorDialog scanner;
	private ShopApplication shopApplication;
	private Map<Product, Integer> scannedItems;

	public ProductScannedActionListener(BarcodeScannerEmulatorDialog scanner, ShopApplication shopApplication,
			Map<Product, Integer> scannedItems) {
		super();
		this.scanner = scanner;
		this.shopApplication = shopApplication;
		this.scannedItems = scannedItems;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String scannedId = scanner.getScannedBarcodeNumber();
		System.out.println("Scanned: " + scannedId);
		Product product = shopApplication.getProductByBarcode(scannedId);
		if (product == null) {
			System.out.println("Product not found!");
		} else {
			System.out.println("Product found!");
		}

		// anyway, hardcode a product to simulate the scanned item here
		Product scannedProduct = new Product("CLO/10", "test scan product", "test", 10, 11.45, "12345", 10, 10);

	}

}
