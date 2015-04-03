package sg.edu.nus.iss.shop.ui.purchase;

/**
 * @author Xia Rongxin
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.NumberHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;
import sg.edu.nus.iss.shop.ui.util.TextFieldLimit;

public class EditPurchaseItemDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;
	private JLabel productNameValueLabel;
	private JTextField quantityField;
	private Product product;
	private ItemTableModel itemModel;
	private JLabel messageLabel;

	public EditPurchaseItemDialog(ShopApplication shopApplication, CheckoutWindow checkoutWindow, Product product,
			ItemTableModel itemModel) {
		super(shopApplication.getMainWindow().getMainPanel().getCheckoutWindow(), "Edit  Purchase Item");
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
		this.product = product;
		this.itemModel = itemModel;
		setFormPanel(createNewFormPanel());
		setPreferredSize(new Dimension(350, 200));
	}

	@Override
	protected JPanel createFormPanel() {
		return new JPanel();
	}

	private JPanel createNewFormPanel() {
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Edit Qty "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel nameLabel = new JLabel("Product Name:");
		p.add(nameLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel qtyLabel = new JLabel("Qty:");
		p.add(qtyLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		productNameValueLabel = new JLabel(product.getName());
		p.add(productNameValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		quantityField = new JTextField(4);
		quantityField.setDocument(new TextFieldLimit(4));

		Integer currentQty = itemModel.getItems().get(product);
		quantityField.setText("" + currentQty);
		quantityField.setToolTipText("Please input quantity.");
		p.add(quantityField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Edit purchase item qty.");
		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String quantityValue= quantityField.getText().trim();

		if (!NumberHelper.isValidPositiveInteger(quantityValue))
		{
			MessageHelper.showErrorMessage("Please input a positive integer value.");
			return false;
		}

		Integer qty = new Integer(quantityValue);

		itemModel.editItem(product, qty);

		updatePurchaseInfo();

		return true;
	}

	private void updatePurchaseInfo() {
		List<Product> productsInCart = checkoutWindow.getProducts();
		productsInCart.clear();
		for (Product product : itemModel.getItems().keySet()) {
			for (int i = 0; i < itemModel.getItems().get(product); i++) {
				productsInCart.add(product);
			}
		}
		checkoutWindow.setProducts(productsInCart);

		Double totalPrice = PriceHelper.getTotalPrice(productsInCart);

		Discount discount = checkoutWindow.getCustomer().getMaxDiscount();
		Double discountPrice = new Double(0);
		if (discount != null) {
			checkoutWindow.setDiscount(discount.getDiscountPercentage());
			double discountPercentage = discount.getDiscountPercentage() / 100.00;
			discountPrice = totalPrice * discountPercentage;
		}

		Double totalAmountAfterDiscount = totalPrice - discountPrice;
		checkoutWindow.updatePurchaseInfo(totalPrice, discountPrice, totalAmountAfterDiscount);
	}


}

