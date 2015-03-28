package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class MemberInfoPanel extends JPanel {

	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private JLabel memberTypeValuelabel;
	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;

	public MemberInfoPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;
		initMemerInfoPanel();
	}

	private void initMemerInfoPanel() {
		setLayout(new GridLayout(0, 2));
		setVisible(false);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Customer  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel memberTypeLabel = new JLabel("Customer Type: ");
		add(memberTypeLabel);

		memberTypeValuelabel = new JLabel("");
		add(memberTypeValuelabel);

		JLabel memberIdLabel = new JLabel("Member ID: ");
		add(memberIdLabel);

		memberIdValuelabel = new JLabel("");
		add(memberIdValuelabel);

		JLabel memberNameLabel = new JLabel("Member Name: ");
		add(memberNameLabel);

		memberNameValuelabel = new JLabel("");
		add(memberNameValuelabel);

		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		add(loyaltypointsLabel);

		memberLoyaltyPointsValueLabel = new JLabel("0");
		add(memberLoyaltyPointsValueLabel);

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
