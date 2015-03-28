package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

public class MakePaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;
	private JTextField loyaltyPointsField;
	private JTextField paidAmountField;
	private JLabel amountToBePaidValue;

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
		loyaltyPointsField = new JTextField(10);
		p.add(loyaltyPointsField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		JButton redeemButton = new JButton("Redeem");
		redeemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Integer loyalPoints = ((Member) checkoutWindow.getCustomer()).getLoyalPoints();

				Integer loyalPointsToUse = Integer.valueOf(loyaltyPointsField.getText().trim());

				if (loyalPointsToUse > loyalPoints) {
					JOptionPane.showMessageDialog(null, "Not enough loyalty points.");
				}

				Double cashToBePay = shopApplication.calculateCashToPay(loyalPointsToUse,
						checkoutWindow.getTotalPayable());
				amountToBePaidValue.setText(PriceHelper.getPriceDisplay(cashToBePay));
				checkoutWindow.setLoyalPointsUsed(loyalPointsToUse);
			}
		});
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

		amountToBePaidValue = new JLabel();
		p.add(amountToBePaidValue, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		paidAmountField = new JTextField(10);
		paidAmountField.setToolTipText("Please input  amount paid");
		p.add(paidAmountField, gc);
		return p;
	}

	public JTextField getLoyatyPointsField() {
		return loyaltyPointsField;
	}

	public JTextField getPaidAmountField() {
		return paidAmountField;
	}

	public JLabel getAmountToBePaidValue() {
		return amountToBePaidValue;
	}

}
