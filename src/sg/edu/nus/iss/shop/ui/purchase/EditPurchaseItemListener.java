package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

/**
 *
 * @author Xia Rongxin
 *
 */
public class EditPurchaseItemListener implements ActionListener {
	private EditPurchaseItemDialog editDialog;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;
	private Product product;
	private ItemTableModel itemModel;

	public EditPurchaseItemListener(ShopApplication shopApplication, CheckoutWindow checkoutWindow, Product product,
			ItemTableModel model) {
		this.shopApplication=shopApplication;
		this.checkoutWindow=checkoutWindow;
		this.product=product;
		itemModel = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (editDialog == null) {
			editDialog = new EditPurchaseItemDialog(shopApplication, checkoutWindow, product, itemModel);
			editDialog.pack();
			editDialog.setLocationByPlatform(true);
		}
		editDialog.setVisible(true);
	}

}

