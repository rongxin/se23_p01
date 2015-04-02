package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.NumberHelper;

public class GenerateProductOrderDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JTextField orderQuantityValueField;
	private JComboBox<String> vendorCombo;;
	private JLabel messageLabel;
	private Product product;

	public GenerateProductOrderDialog(ShopApplication shopApplication, Product product) {
		super(shopApplication.getMainWindow().getMainPanel().getCategoryWindow(), "Order Products");
		this.shopApplication = shopApplication;
		this.product = product;

		setFormPanel(createNewFormPanel());
	}


	@Override
	protected JPanel createFormPanel() {
		return new JPanel();
	}

	protected JPanel createNewFormPanel() {
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Order Product "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel vendorLabel = new JLabel("Vendor: ");
		p.add(vendorLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel orderQuantityLabel = new JLabel("Order Quantity: ");
		p.add(orderQuantityLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		List<String> vendorNames = new ArrayList<String>();
		Category category = product.getCategory();
		List<Vendor> vendorList = category.getVendorList();
		if (category != null && vendorList != null) {
			for (Vendor vendor : vendorList) {
				vendorNames.add(vendor.getName());
			}
		}
		vendorCombo = new JComboBox<>(vendorNames.toArray(new String[vendorNames.size()]));
		vendorCombo.setToolTipText("Please choose a vendor");
		p.add(vendorCombo, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		orderQuantityValueField = new JTextField(20);
		orderQuantityValueField.setText("" + product.getOrderQuantity());
		orderQuantityValueField.setToolTipText("Please input order quantity.");
		p.add(orderQuantityValueField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please choose vendor to generate products.");
		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String vendor = (String) vendorCombo.getSelectedItem();

		String orderQuantityInput = orderQuantityValueField.getText().trim();

		if (vendor == null || vendor.equals("")) {
			MessageHelper.showErrorMessage("Please set vendor for the product.");
			return false;
		} else if (!NumberHelper.isValidPositiveInteger(orderQuantityInput)) {
			MessageHelper.showErrorMessage("Please input valid quantity.");
			return false;
		}
		Integer orderQuantity = Integer.valueOf(orderQuantityValueField.getText().trim());

		if (orderQuantity == 0) {
			MessageHelper.showErrorMessage("Order quantity should be greater that 0.");
			return false;
		}

		OrderProductWindow window = new OrderProductWindow(product, vendor, orderQuantity);
		window.pack();
		window.setLocationByPlatform(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		return true;
	}
}
