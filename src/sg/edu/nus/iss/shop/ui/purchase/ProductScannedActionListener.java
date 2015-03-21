package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import sg.edu.nus.iss.shop.model.domain.Discount;
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

			List<Product> productsInCart = checkoutWindow.getProducts();
			productsInCart.add(product);

			Double totalPrice = new Double(0);
			for (Product productInCart : productsInCart) {
				totalPrice = productInCart.getPrice() + totalPrice;
			}

			checkoutWindow.getPurchaseInfoPanel().getTotalAmountValueLabel()
			.setText("" + String.format("%1$,.2f", totalPrice));
			Discount discount = checkoutWindow.getCustomer().getMaxDiscount();
			Double discountPrice = new Double(0);
			if (discount == null) {
				System.err.println("Could not get discount:");
			} else {
				double discountPercentage = discount.getDiscountPercentage() / 100.00;
				System.out.println("Discount purcentage:" + discountPercentage);
				discountPrice = totalPrice * discountPercentage;
			}
			checkoutWindow.getPurchaseInfoPanel().getDiscountValueLabel()
			.setText("" + String.format("%1$,.2f", discountPrice));

			Double totalPayable = totalPrice - discountPrice;
			checkoutWindow.getPurchaseInfoPanel().getTotalPayableAmountValueLabel()
			.setText("" + String.format("%1$,.2f", totalPayable));

		}


	}

}
