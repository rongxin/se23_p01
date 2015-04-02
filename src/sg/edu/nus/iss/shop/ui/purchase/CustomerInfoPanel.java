package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class CustomerInfoPanel extends JPanel {

	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private JLabel memberTypeValuelabel;
	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;

	public CustomerInfoPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
		initMemerInfoPanel();
	}

	private void initMemerInfoPanel() {
		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Customer  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gc;

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel memberTypeLabel = new JLabel("Customer Type: ");
		add(memberTypeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel memberIdLabel = new JLabel("Member ID: ");
		add(memberIdLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		JLabel memberNameLabel = new JLabel("Member Name: ");
		add(memberNameLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		add(loyaltypointsLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 5);
		add(new JLabel(), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		memberTypeValuelabel = new JLabel("");
		add(memberTypeValuelabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		memberIdValuelabel = new JLabel("");
		add(memberIdValuelabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		memberNameValuelabel = new JLabel("");
		add(memberNameValuelabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 3);
		memberLoyaltyPointsValueLabel = new JLabel("0");
		add(memberLoyaltyPointsValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 4);
		add(new JPanel(), gc);

		gc = LayoutHelper.createCellConstraint(1, 5);
		add(new JPanel(), gc);
	}

	public JLabel getMemberTypeValuelabel() {
		return memberTypeValuelabel;
	}

	public JLabel getMemberIdValuelabel() {
		return memberIdValuelabel;
	}

	public JLabel getMemberNameValuelabel() {
		return memberNameValuelabel;
	}

	public JLabel getMemberLoyaltyPointsValueLabel() {
		return memberLoyaltyPointsValueLabel;
	}
}
