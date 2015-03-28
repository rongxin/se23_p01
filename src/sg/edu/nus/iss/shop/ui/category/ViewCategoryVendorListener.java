package sg.edu.nus.iss.shop.ui.category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ViewCategoryVendorListener implements ActionListener {

	private Category category;
	private ShopApplication shopApplication;

	public ViewCategoryVendorListener(ShopApplication shopApplication, Category category) {
		this.shopApplication = shopApplication;
		this.category = category;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VendorWindow window = new VendorWindow(shopApplication, category);
		initializeWindow(window);
	}

	private void initializeWindow(JFrame window) {
		window.pack();
		window.setLocationByPlatform(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
