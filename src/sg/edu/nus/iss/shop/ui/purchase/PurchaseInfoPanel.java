package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class PurchaseInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;

	private JLabel totalAmountValueLabel;
	private JLabel discountValueLabel;
	private JLabel totalAfterDiscountValueLabel;
	private JLabel cashToPayValueLabel;
	private JLabel loyalPointsUsedValueLabel;
	private JLabel cashReceivedValueLabel;
	private JLabel changesGivenValueLabel;

	public PurchaseInfoPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(" Checkout  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		initPurchaseInfoPanel();
	}

	private void initPurchaseInfoPanel() {
		GridBagConstraints gc;

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel totalAmountLabel = new JLabel("Total Amount ($) : ");
		add(totalAmountLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel discountLabel = new JLabel("Discount ($): ");
		add(discountLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		JLabel totalAfterDiscountLabel = new JLabel("Discounted Amount ($): ");
		add(totalAfterDiscountLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		JLabel loyalPointsUsedLabel = new JLabel("Loyal Points used: ");
		add(loyalPointsUsedLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		JLabel cashToPayLabel = new JLabel("Cash Payable ($): ");
		cashToPayLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(cashToPayLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		totalAmountValueLabel = new JLabel("0.00");
		add(totalAmountValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		discountValueLabel = new JLabel("0.00");
		add(discountValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		totalAfterDiscountValueLabel = new JLabel("0.00");
		add(totalAfterDiscountValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 3);
		loyalPointsUsedValueLabel = new JLabel("0");
		add(loyalPointsUsedValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 4);
		cashToPayValueLabel = new JLabel("0.00");
		cashToPayValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(cashToPayValueLabel, gc);

	}

	public JLabel getTotalAfterDiscountValueLabel() {
		return totalAfterDiscountValueLabel;
	}

	public JLabel getDiscountValueLabel() {
		return discountValueLabel;
	}

	public JLabel getTotalAmountValueLabel() {
		return totalAmountValueLabel;
	}

	public JLabel getLoyalPointsUsedValueLabel() {
		return loyalPointsUsedValueLabel;
	}

	public void setTotalAfterDiscountValueLabel(JLabel totalAfterDiscountValueLabel) {
		this.totalAfterDiscountValueLabel = totalAfterDiscountValueLabel;
	}

	public JLabel getCashToPayValueLabel() {
		return cashToPayValueLabel;
	}

	public JLabel getCashReceivedValueLabel() {
		return cashReceivedValueLabel;
	}

	public JLabel getChangesGivenValueLabel() {
		return changesGivenValueLabel;
	}

}
