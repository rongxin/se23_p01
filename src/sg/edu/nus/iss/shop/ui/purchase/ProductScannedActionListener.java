package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ProductScannedActionListener extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private BarcodeScannerEmulatorDialog scanner;
	private ShopApplication shopApplication;

	public ProductScannedActionListener(BarcodeScannerEmulatorDialog scanner, ShopApplication shopApplication) {
		super();
		this.scanner = scanner;
		this.shopApplication = shopApplication;
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
	}

}
