package sg.edu.nus.iss.shop.ui.discount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class EditDiscountListener implements ActionListener {
	private ShopApplication shopApplication;
	private Discount discount;
	private ListDiscountPanel listPanel;
	private EditDiscountDialog editDialog;

	public EditDiscountListener(ShopApplication shopApplication, ListDiscountPanel listDiscountPanel, Discount discount) {
		this.shopApplication = shopApplication;
		listPanel = listDiscountPanel;
		this.discount = discount;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (editDialog == null) {
			editDialog = new EditDiscountDialog(shopApplication, listPanel, discount);
			editDialog.pack();
			editDialog.setLocationByPlatform(true);
		}
		editDialog.setVisible(true);
	}

}
