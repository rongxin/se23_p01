package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class MakePaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;

	public MakePaymentPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;

		initMakePaymentPanel();
		//
		// JPanel outerPanel = new JPanel(new BorderLayout());
		// outerPanel.add("North", p);
		// outerPanel.add("Center", new JPanel());
		// outerPanel.add("South", new JPanel());
	}

	private void initMakePaymentPanel() {

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Make Payments "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Loyaty  Points: ");
		add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Paid Amount: ");
		add(categoryNameLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 4);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 5);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 6);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 7);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 8);
		add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 9);
		add(new JLabel(), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		JTextField loyatyPointsField = new JTextField(15);
		loyatyPointsField.setToolTipText("Please input points to redeem.");
		add(loyatyPointsField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		JTextField paidAmountField = new JTextField(15);
		paidAmountField.setToolTipText("Please input total amount paid");
		add(paidAmountField, gc);

	}

}
