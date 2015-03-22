package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class MakePaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;
	private JTextField loyatyPointsField;
	private JTextField paidAmountField;

	public MakePaymentPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;

		initMakePaymentPanel();
	}

	private void initMakePaymentPanel() {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc = LayoutHelper.createCellConstraint(0, 0);
		this.add(createRedeemLoyaltyPointsPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		this.add(new JPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		this.add(createMakePaymentPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		this.add(new JPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		this.add(new JPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 5);
		this.add(new JPanel(), gc);

	}

	private JPanel createRedeemLoyaltyPointsPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Redeem Loyalty Points  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		p.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel loyaltyPointsLabel = new JLabel("Loyaty  Points: ");
		p.add(loyaltyPointsLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 0);
		p.add(new JPanel(), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		loyatyPointsField = new JTextField(10);
		p.add(loyatyPointsField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		JButton redeemButton = new JButton("Redeem");
		JPanel buttonPanel = new JPanel();

		buttonPanel.add(redeemButton);
		p.add(buttonPanel, gc);

		return p;
	}

	private JPanel createMakePaymentPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Payment  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		p.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel amountToBePaidLabel = new JLabel("Amount to be  paid ($): ");
		p.add(amountToBePaidLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel cashPaidAmount = new JLabel("Paid Amount ($): ");
		p.add(cashPaidAmount, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		JLabel amountToBePaidValue = new JLabel("0.00");
		p.add(amountToBePaidValue, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		paidAmountField = new JTextField(10);
		paidAmountField.setToolTipText("Please input  amount paid");
		p.add(paidAmountField, gc);
		return p;
	}

	public JTextField getLoyatyPointsField() {
		return loyatyPointsField;
	}

	public JTextField getPaidAmountField() {
		return paidAmountField;
	}

}
