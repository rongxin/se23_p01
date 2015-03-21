package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class PurchaseInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;

	private JLabel totalPayableAmountValueLabel;
	private JLabel discountValueLabel;
	private JLabel totalAmountValueLabel;

	public PurchaseInfoPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
		setLayout(new GridLayout(0, 2));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(" Checkout  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		initPurchaseInfoPanel();
	}

	private void initPurchaseInfoPanel() {

		JLabel totalAmountLabel = new JLabel("Total Amount ($) : ");
		add(totalAmountLabel);

		totalAmountValueLabel = new JLabel("0.00");
		add(totalAmountValueLabel);

		JLabel discountLabel = new JLabel("Discount ($): ");
		add(discountLabel);

		discountValueLabel = new JLabel("0.00");
		add(discountValueLabel);

		JLabel totalPayableAmountLabel = new JLabel("Total Payable ($): ");
		totalPayableAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(totalPayableAmountLabel);

		totalPayableAmountValueLabel = new JLabel("0.00");
		totalPayableAmountValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(totalPayableAmountValueLabel);

	}

	public JLabel getTotalPayableAmountValueLabel() {
		return totalPayableAmountValueLabel;
	}

	public JLabel getDiscountValueLabel() {
		return discountValueLabel;
	}

	public JLabel getTotalAmountValueLabel() {
		return totalAmountValueLabel;
	}

}
