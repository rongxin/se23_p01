package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ProductScannedActionListener extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private BarcodeScannerEmulatorDialog scanner;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;

	public ProductScannedActionListener(BarcodeScannerEmulatorDialog scanner, ShopApplication shopApplication,
			CheckoutWindow checkoutWindow) {
		super();
		this.scanner = scanner;
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String barcodeNumber = scanner.getScannedBarcodeNumber();
		System.out.println("Scanned: " + barcodeNumber);
		Product product = shopApplication.getProductByBarcode(barcodeNumber);
		if (product == null) {
			JOptionPane.showMessageDialog(null, "Unable to find product for barcode number:" + barcodeNumber);
		} else if (product.getAvailableQuantity() <= 0) {
			JOptionPane.showMessageDialog(null, "Product out of stock for barcode number:" + barcodeNumber);
		} else {
			System.out.println("Product scanned:" + product.getName());

			ItemTableModel model = (ItemTableModel) checkoutWindow.getListPurchaseItemPanel().getTable().getModel();
			model.addItem(product);
		}


	}

}
