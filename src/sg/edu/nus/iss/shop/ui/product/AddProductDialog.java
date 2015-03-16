package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

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
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.panel.ListProductPanel;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class AddProductDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> categoryCombo;
	private JTextField nameField;
	private JTextArea descriptionField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField barCodeNumberField;
	private JTextField reorderThresholdField;
	private JTextField reorderQuantityField;
	private JLabel messageLabel;
	private JPanel listPanel;

	public AddProductDialog(ShopApplication shopApplication, ListProductPanel listPanel) {
		super(shopApplication, shopApplication.getMainWindow(), " Add Product ");
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
		return mainPanel;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input product details.");
		p.add(messageLabel);

		return p;
	}


	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Product "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel productCategoryLabel = new JLabel("Product Category:");
		p.add(productCategoryLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel productTitleLabel = new JLabel("Product Name:");
		p.add(productTitleLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		JLabel productDescriptionLabel = new JLabel("Product Description:");
		p.add(productDescriptionLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		JLabel productQuantityLabel = new JLabel("Quantity:");
		p.add(productQuantityLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		JLabel productPriceLabel = new JLabel("Price:");
		p.add(productPriceLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 5);
		JLabel productBarCodeLabel = new JLabel("Barcode Number:");
		p.add(productBarCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 6);
		JLabel productReorderThresholdLabel = new JLabel("Reorder Threshold:");
		p.add(productReorderThresholdLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 7);
		JLabel productOrderQuantityJLabel = new JLabel("Order Quantity:");
		p.add(productOrderQuantityJLabel, gc);

		// column 2
		gc.anchor = GridBagConstraints.LAST_LINE_START;

		gc = LayoutHelper.createCellConstraint(1, 0);
		List<Category> categories = shopApplication.getCategories();
		List<String> categoryCodes = new ArrayList<>();
		for (Category category : categories) {
			categoryCodes.add(category.getCode());
		}
		categoryCombo = new JComboBox<>(categoryCodes.toArray(new String[categoryCodes.size()]));

		categoryCombo.setToolTipText("Please choose a category");

		p.add(categoryCombo, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		nameField = new JTextField(20);
		nameField.setToolTipText("Please input the product name.");
		p.add(nameField, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		descriptionField = new JTextArea(5, 20);
		descriptionField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		descriptionField.setToolTipText("Please input product description.");
		JScrollPane productDescriptionFieldScroll = new JScrollPane(descriptionField);
		productDescriptionFieldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		p.add(productDescriptionFieldScroll, gc);

		gc = LayoutHelper.createCellConstraint(1, 3);
		quantityField = new JTextField(20);
		quantityField.setToolTipText("Please input available product quantity.");
		p.add(quantityField, gc);

		gc = LayoutHelper.createCellConstraint(1, 4);
		priceField = new JTextField(20);
		priceField.setToolTipText("Please input product price.");
		p.add(priceField, gc);

		gc = LayoutHelper.createCellConstraint(1, 5);
		barCodeNumberField = new JTextField(20);
		barCodeNumberField.setToolTipText("Please input the barcode number of product.");
		p.add(barCodeNumberField, gc);

		gc = LayoutHelper.createCellConstraint(1, 6);
		reorderThresholdField = new JTextField(20);
		reorderThresholdField.setToolTipText("Please input threshold for reorder.");
		p.add(reorderThresholdField, gc);

		gc = LayoutHelper.createCellConstraint(1, 7);
		reorderQuantityField = new JTextField(20);
		reorderQuantityField.setToolTipText("Please input quanity when reorder this product");
		p.add(reorderQuantityField, gc);

		return p;
	}


	@Override
	protected boolean performOkAction() {
		if ((nameField.getText().trim().length() == 0) || (descriptionField.getText().length() == 0)) {
			messageLabel.setText("Please input all necessary fields.");
			messageLabel.setForeground(Color.RED);
			return false;
		}

		String name = nameField.getText().trim();
		String description = descriptionField.getText().trim();
		Integer availableQuantity = new Integer(quantityField.getText().trim());
		Double price = new Double(priceField.getText().trim());
		String barcodeNumber = barCodeNumberField.getText().trim();
		Integer orderThreshold = new Integer(reorderThresholdField.getText().trim());
		Integer orderQuantity = new Integer(reorderQuantityField.getText().trim());

		// TODO call add product logic
		shopApplication.addProduct(categoryCombo.getSelectedItem().toString(), name, description, availableQuantity,
				price, barcodeNumber, orderThreshold, orderQuantity);

		listPanel.revalidate();
		return true;
	}
}

