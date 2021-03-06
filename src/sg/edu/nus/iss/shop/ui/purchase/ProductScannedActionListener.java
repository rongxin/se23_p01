package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

/**
 *
 * @author Xia Rongxin
 *
 */
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
		Product product = shopApplication.getProductByBarcode(barcodeNumber);
		if (product == null) {
			MessageHelper.showErrorMessage("Unable to find product for barcode number:"
					+ barcodeNumber);
		} else if (product.getAvailableQuantity() <= 0) {
			MessageHelper.showErrorMessage("Product out of stock for barcode number:"
					+ barcodeNumber);
		} else {
			ItemTableModel model = (ItemTableModel) checkoutWindow.getListPurchaseItemPanel().getTable().getModel();
			JButton editButton = new JButton(IconHelper.createImageIcon("edit.png"));
			editButton.addActionListener(new EditPurchaseItemListener(shopApplication, checkoutWindow, product, model));
			model.addItem(product, editButton);

			updatePurchaseInfo(product);


		}


	}

	private void updatePurchaseInfo(Product product) {
		List<Product> productsInCart = checkoutWindow.getProducts();
		productsInCart.add(product);

		Double totalPrice = PriceHelper.getTotalPrice(productsInCart);

		Discount discount = checkoutWindow.getCustomer().getMaxDiscount();
		Double discountPrice = new Double(0);
		if (discount != null) {
			checkoutWindow.setDiscount(discount.getDiscountPercentage());
			double discountPercentage = discount.getDiscountPercentage() / 100.00;
			discountPrice = totalPrice * discountPercentage;
		}

		Double totalAmountAfterDiscount = totalPrice - discountPrice;
		checkoutWindow.updatePurchaseInfo(totalPrice, discountPrice, totalAmountAfterDiscount);
	}



}
