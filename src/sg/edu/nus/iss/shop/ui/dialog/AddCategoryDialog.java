package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
		JPanel p = createAddCategoryPanel();

		JPanel mainPanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Add Category", SwingConstants.CENTER);
		title.setFont(new Font("Arial", 1, 28));

		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(p, BorderLayout.CENTER);
		mainPanel.add(new JLabel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 16));
		return mainPanel;
	}

	private JPanel createAddCategoryPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 2));

		JLabel categoryCodeLabel = new JLabel("Category Code:");
		p.add(categoryCodeLabel);
		categoryCodeField = new JTextField(3);
		categoryCodeField
		.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField);

		JLabel categoryNameLabel = new JLabel("Category Name:");
		p.add(categoryNameLabel);
		categoryNameField = new JTextField(20);
		categoryNameField.setToolTipText("Please input name for the category");
		p.add(categoryNameField);

		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

