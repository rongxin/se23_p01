package sg.edu.nus.iss.shop.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.ui.ShopApplication;

public class AddProductDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JComboBox productCategoryCombo;
	private JTextField productNameField;
	private JTextArea productDescriptionField;
	private JTextField productQuantityField;
	private JTextField productPriceField;
	private JTextField productBarCodeNumberField;
	private JTextField productReorderThresholdField;
	private JTextField productReorderQuantityField;
	private JLabel messageLabel;

	public AddProductDialog(ShopApplication shopApplication) {
		super(shopApplication.getMainWindow(), "Add Product");
		this.shopApplication = shopApplication;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Add Product", SwingConstants.CENTER);
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
		messageLabel.setText("Please input product details.");
		p.add(messageLabel);
		setMargin(p);
		return p;
	}

	private JPanel createFormLabelsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));

		JLabel productCategoryLabel = new JLabel("Product Category:");
		p.add(productCategoryLabel);
		JLabel productTitleLabel = new JLabel("Product Name:");
		p.add(productTitleLabel);
		JLabel productDescriptionLabel = new JLabel("Product Description:");
		p.add(productDescriptionLabel);
		JLabel productQuantityLabel = new JLabel("Quantity:");
		p.add(productQuantityLabel);
		JLabel productPriceLabel = new JLabel("Price:");
		p.add(productPriceLabel);
		JLabel productBarCodeLabel = new JLabel("Barcode Number:");
		p.add(productBarCodeLabel);
		JLabel productReorderThresholdLabel = new JLabel("Reorder Threshold:");
		p.add(productReorderThresholdLabel);
		JLabel productOrderQuantityJLabel = new JLabel("Order Quantity:");
		p.add(productOrderQuantityJLabel);

		setMargin(p);
		return p;
	}

	private void setMargin(JPanel p) {
		p.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private JPanel createFormInputFieldsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));

		productCategoryCombo = new JComboBox<>();
		productCategoryCombo.setToolTipText("Please choose a category");
		p.add(productCategoryCombo);

		productNameField = new JTextField(20);
		productNameField.setToolTipText("Please input the product name.");
		p.add(productNameField);

		productDescriptionField = new JTextArea(4, 20);
		productDescriptionField.setToolTipText("Please input product description.");
		p.add(productDescriptionField);

		productQuantityField = new JTextField(20);
		productQuantityField.setToolTipText("Please input available product quantity.");
		p.add(productQuantityField);

		productPriceField = new JTextField(20);
		productPriceField.setToolTipText("Please input product price.");
		p.add(productPriceField);

		productBarCodeNumberField = new JTextField(20);
		productBarCodeNumberField.setToolTipText("Please input the barcode number of product.");
		p.add(productBarCodeNumberField);

		productReorderThresholdField = new JTextField(20);
		productReorderThresholdField.setToolTipText("Please input threshold for reorder.");
		p.add(productReorderThresholdField);

		productReorderQuantityField = new JTextField(20);
		productReorderQuantityField.setToolTipText("Please input quanity when reorder this product");
		p.add(productReorderQuantityField);

		setMargin(p);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String productCategory = productCategoryCombo.getSelectedItem().toString();
		String productName = productNameField.getText().trim();
		String productDescription = productDescriptionField.getText().trim();
		String productQuantity = productQuantityField.getText().trim();
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

