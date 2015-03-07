package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JLabel messageLabel;

	public AddCategoryDialog(ShopApplication shopApplication) {
		super(shopApplication.getMainWindow(), "Add Category");
		this.shopApplication = shopApplication;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Add Category", SwingConstants.CENTER);
		title.setFont(new Font("Arial", 1, 28));

		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(createFormLabelsPanel(), BorderLayout.WEST);
		mainPanel.add(createFormInputFieldsPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ");
		messageLabel.setText("Please input category code and name.");
		p.add(messageLabel);
		setMargin(p);
		return p;
	}

	private JPanel createFormLabelsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));

		JLabel categoryCodeLabel = new JLabel("Category Code:");
		p.add(categoryCodeLabel);
		JLabel categoryNameLabel = new JLabel("Category Name:");
		p.add(categoryNameLabel);

		setMargin(p);
		return p;
	}

	private void setMargin(JPanel p) {
		p.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private JPanel createFormInputFieldsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));

		categoryCodeField = new JTextField(3);
		categoryCodeField
		.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField);

		categoryNameField = new JTextField(20);
		categoryNameField.setToolTipText("Please input name for the category");
		p.add(categoryNameField);

		setMargin(p);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String categoryCode = categoryCodeField.getText().trim();
		String categoryName = categoryNameField.getText().trim();
		if ((categoryCode.length() == 0) || (categoryName.length() == 0)) {
			messageLabel.setText("Category code and  name are compulsory.");
			messageLabel.setForeground(Color.RED);
			return false;
		}
		shopApplication.addCategory (categoryCode, categoryName);
		return true;
	}
}

