package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.ui.ShopApplication;

public class AddCategoryDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
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
		GridLayout layout = new GridLayout(0, 2);
		p.setLayout(layout);

		JLabel categoryCodeLabel = new JLabel("Category Code");
		setMargin(categoryCodeLabel);
		p.add(categoryCodeLabel);
		categoryCodeField = new JTextField();
		categoryCodeField
		.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField);

		JLabel categoryNameLabel = new JLabel("Category Name");
		setMargin(categoryNameLabel);

		p.add(categoryNameLabel);
		categoryNameField = new JTextField(20);
		categoryNameField.setToolTipText("Please input name for the category");
		p.add(categoryNameField);


		return p;
	}

	private void setMargin(JComponent component) {
		component.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
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

