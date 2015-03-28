package sg.edu.nus.iss.shop.ui.category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sg.edu.nus.iss.shop.model.domain.Category;

public class ViewCategoryVendorListener implements ActionListener {

	private Category category;

	public ViewCategoryVendorListener(Category category) {
		this.category = category;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "vendor for  category:" + category.getCode());
	}

}
