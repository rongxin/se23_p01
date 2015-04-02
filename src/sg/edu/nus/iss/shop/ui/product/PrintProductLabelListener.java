package sg.edu.nus.iss.shop.ui.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

/**
 *
 * @author Xia Rongxin
 *
 */
public class PrintProductLabelListener implements ActionListener {
	private ShopApplication shopApplication;
	private ListProductPanel listPanel;
	private Product product;

	public PrintProductLabelListener(ShopApplication shopApplication, ListProductPanel listProductPanel, Product product) {
		this.shopApplication = shopApplication;
		listPanel = listProductPanel;
		this.product = product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		shopApplication.printProductLabel(product);
	}

}
