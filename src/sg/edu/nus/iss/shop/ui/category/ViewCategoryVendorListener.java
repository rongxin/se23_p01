package sg.edu.nus.iss.shop.ui.category;

/**
 * @author Xia Rongxin
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ViewCategoryVendorListener implements ActionListener {

	private Category category;
	private ShopApplication shopApplication;
	private VendorWindow vendorWindow;

	public ViewCategoryVendorListener(ShopApplication shopApplication, Category category) {
		this.shopApplication = shopApplication;
		this.category = category;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (vendorWindow == null) {
			vendorWindow = new VendorWindow(shopApplication, category);
			vendorWindow.pack();
			vendorWindow.setLocationByPlatform(true);
			vendorWindow.setLocationRelativeTo(null);
		}

		vendorWindow.setVisible(true);
	}


}
