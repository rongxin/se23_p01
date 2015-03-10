package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}

	private GridBagConstraints createCellConstraint(int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = 1;
		gc.gridheight = 1;

		boolean isLeftMostColumn = x == 0;
		gc.anchor = isLeftMostColumn ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		// gc.fill = isLeftMostColumn ? GridBagConstraints.BOTH :
		// GridBagConstraints.HORIZONTAL;

		Insets westInset = new Insets(5, 0, 5, 5);
		Insets eastInset = new Insets(5, 5, 5, 0);
		gc.insets = isLeftMostColumn ? westInset : eastInset;
		gc.weightx = isLeftMostColumn ? 0.1 : 1.0;
		gc.weighty = 1.0;
		return gc;
	}

	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Category "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		createCellConstraint(0, 0);
		gc = createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Category Code: ");
		p.add(categoryCodeLabel, gc);

		gc = createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Category Name: ");
		p.add(categoryNameLabel, gc);

		// column 2
		gc = createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;

		categoryCodeField = new JTextField(3);
		categoryCodeField.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField, gc);

		gc = createCellConstraint(1, 1);
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
		shopApplication.addCategory (categoryCode, categoryName);
		return true;
	}
}

