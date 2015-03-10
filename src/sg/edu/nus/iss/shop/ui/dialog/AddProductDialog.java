package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.ShopApplication;

public class AddProductDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JComboBox<String> categoryCombo;
	private JTextField nameField;
	private JTextArea descriptionField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField barCodeNumberField;
	private JTextField reorderThresholdField;
	private JTextField reorderQuantityField;
	private JLabel messageLabel;
	private Category[] categories;

	public AddProductDialog(ShopApplication shopApplication) {
		super(shopApplication.getMainWindow(), " Add Product ");
		this.shopApplication = shopApplication;
		categories = this.shopApplication.getCategories();
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		return mainPanel;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input product details.");
		p.add(messageLabel);

		return p;
	}

	private GridBagConstraints createCellConstraint(int x, int y) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = 1;
		gc.gridheight = 1;

		boolean isLeftMostColumn = x == 0;
		gc.anchor = isLeftMostColumn ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		gc.fill = isLeftMostColumn ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Product "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = createCellConstraint(0, 0);
		JLabel productCategoryLabel = new JLabel("Product Category:");
		p.add(productCategoryLabel, gc);

		gc = createCellConstraint(0, 1);
		JLabel productTitleLabel = new JLabel("Product Name:");
		p.add(productTitleLabel, gc);

		gc = createCellConstraint(0, 2);
		JLabel productDescriptionLabel = new JLabel("Product Description:");
		p.add(productDescriptionLabel, gc);

		gc = createCellConstraint(0, 3);
		JLabel productQuantityLabel = new JLabel("Quantity:");
		p.add(productQuantityLabel, gc);

		gc = createCellConstraint(0, 4);
		JLabel productPriceLabel = new JLabel("Price:");
		p.add(productPriceLabel, gc);

		gc = createCellConstraint(0, 5);
		JLabel productBarCodeLabel = new JLabel("Barcode Number:");
		p.add(productBarCodeLabel, gc);

		gc = createCellConstraint(0, 6);
		JLabel productReorderThresholdLabel = new JLabel("Reorder Threshold:");
		p.add(productReorderThresholdLabel, gc);

		gc = createCellConstraint(0, 7);
		JLabel productOrderQuantityJLabel = new JLabel("Order Quantity:");
		p.add(productOrderQuantityJLabel, gc);

		// column 2
		gc.anchor = GridBagConstraints.LAST_LINE_START;

		gc = createCellConstraint(1, 0);
		// TODO hardcoded category list
		String[] categoryNames = new String[] { "Clothing", "Mugs", "Stationary", "Diary" };
		categoryCombo = new JComboBox<>(categoryNames);

		categoryCombo.setToolTipText("Please choose a category");

		p.add(categoryCombo, gc);

		gc = createCellConstraint(1, 1);
		nameField = new JTextField(20);
		nameField.setToolTipText("Please input the product name.");
		p.add(nameField, gc);

		gc = createCellConstraint(1, 2);
		descriptionField = new JTextArea(5, 20);
		descriptionField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		descriptionField.setToolTipText("Please input product description.");
		JScrollPane productDescriptionFieldScroll = new JScrollPane(descriptionField);
		productDescriptionFieldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		p.add(productDescriptionFieldScroll, gc);

		gc = createCellConstraint(1, 3);
		quantityField = new JTextField(20);
		quantityField.setToolTipText("Please input available product quantity.");
		p.add(quantityField, gc);

		gc = createCellConstraint(1, 4);
		priceField = new JTextField(20);
		priceField.setToolTipText("Please input product price.");
		p.add(priceField, gc);

		gc = createCellConstraint(1, 5);
		barCodeNumberField = new JTextField(20);
		barCodeNumberField.setToolTipText("Please input the barcode number of product.");
		p.add(barCodeNumberField, gc);

		gc = createCellConstraint(1, 6);
		reorderThresholdField = new JTextField(20);
		reorderThresholdField.setToolTipText("Please input threshold for reorder.");
		p.add(reorderThresholdField, gc);

		gc = createCellConstraint(1, 7);
		reorderQuantityField = new JTextField(20);
		reorderQuantityField.setToolTipText("Please input quanity when reorder this product");
		p.add(reorderQuantityField, gc);

		return p;
	}


	@Override
	protected boolean performOkAction() {
		// String productCategory =
		// productCategoryCombo.getSelectedItem().toString();
		String productName = nameField.getText().trim();
		String productDescription = descriptionField.getText().trim();
		String productQuantity = quantityField.getText().trim();

		if ((productName.length() == 0) || (productQuantity.length() == 0)) {
			messageLabel.setText("Please input all necessary fields.");
			messageLabel.setForeground(Color.RED);
			return false;
		}

		// TODO call add product logic
		// shopApplication.addProduct (productName, productQuantity);
		return true;
	}
}

