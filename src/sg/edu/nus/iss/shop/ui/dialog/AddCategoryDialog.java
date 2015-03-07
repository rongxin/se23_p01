package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.ui.ShopApplication;

public class AddCategoryDialog extends OkCancelDialog {
	private ShopApplication shopApplication;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;


	public AddCategoryDialog(ShopApplication shopApplication) {
		super(shopApplication.getMainWindow(), "Add Category");
		this.shopApplication = shopApplication;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 2));
		p.add(new JLabel("Category Code"));
		p.add(new JLabel("Category Name"));
		categoryCodeField = new JTextField(20);
		p.add(categoryCodeField);
		categoryNameField = new JTextField(20);
		p.add(categoryNameField);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String categoryCode = categoryCodeField.getText();
		String categoryName = categoryNameField.getText();
		if ((categoryCode.length() == 0) || (categoryName.length() == 0)) {
			return false;
		}
		shopApplication.addCategory (categoryCode, categoryName);
		return true;
	}
}

