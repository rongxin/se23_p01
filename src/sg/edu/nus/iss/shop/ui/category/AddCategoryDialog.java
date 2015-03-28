package sg.edu.nus.iss.shop.ui.category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.TextFieldLimit;

public class AddCategoryDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;
	private JLabel messageLabel;
	private ListCategoryPanel listPanel;

	public AddCategoryDialog(ShopApplication shopApplication, ListCategoryPanel listPanel) {
		super(shopApplication.getMainWindow().getMainPanel().getCategoryWindow(), "Add Category");
		this.shopApplication = shopApplication;
		this.listPanel = listPanel;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}


	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Category "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Category Code: ");
		p.add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Category Name: ");
		p.add(categoryNameLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		categoryCodeField = new JTextField(3);
		categoryCodeField.setDocument(new TextFieldLimit(3));
		categoryCodeField.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		categoryNameField = new JTextField(20);
		categoryNameField.setToolTipText("Please input name for the category");
		p.add(categoryNameField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input category code and name.");

		p.add(messageLabel);
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
		Category category = shopApplication.addCategory(categoryCode, categoryName);

		JButton vendorButton = new JButton("Vendors");
		vendorButton.addActionListener(new ViewCategoryVendorListener(category));
		listPanel.getTableModel().addToTable(category, vendorButton);
		return true;
	}
}

