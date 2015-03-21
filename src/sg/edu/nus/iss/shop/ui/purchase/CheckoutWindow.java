package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class CheckoutWindow extends JFrame {

	private static final String CARD_MEMBER = "memberCard";
	private static final String CARD_PAYMENT = "paymentCard";
	private static final String CARD_CART = "cartCard";
	private static final String CARD_SUMMARY = "summaryCard";

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JPanel purchaseCardPanel;

	private JPanel memberInfoPanel;
	private JPanel purchaseInfoPanel;


	private Customer customer;
	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;
	private JLabel memberTypeValuelabel;



	private ListPurchaseItemPanel listPurchaseItemPanel;
	private ActionButtonsPanel actionButtonsPanel;

	public CheckoutWindow(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createPurchaseCardPanel());
		this.add("East", createRightPanel());
		this.add("South", createMessagePanel());

	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Make Purchase"));
		return p;
	}

	private JPanel createMessagePanel() {
		JPanel p = new JPanel();
		JLabel messageLabel = new JLabel();
		messageLabel.setText("Please scan items");
		p.add(messageLabel);
		return p;
	}

	private JPanel createRightPanel() {
		JPanel p = new JPanel(new GridLayout(3, 1));
		p.add(createMemerInfoPanel());
		p.add(createPurchaseInfoPanel());

		actionButtonsPanel = new ActionButtonsPanel(shopApplication, this);
		p.add(actionButtonsPanel);
		return p;
	}

	private JPanel createMemerInfoPanel() {

		memberInfoPanel = new JPanel(new GridLayout(0, 2));
		memberInfoPanel.setVisible(false);

		memberInfoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Member Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel memberTypeLabel = new JLabel("Member Type: ");
		memberInfoPanel.add(memberTypeLabel);

		memberTypeValuelabel = new JLabel("");
		memberInfoPanel.add(memberTypeValuelabel);

		JLabel memberIdLabel = new JLabel("Member ID: ");
		memberInfoPanel.add(memberIdLabel);

		memberIdValuelabel = new JLabel("");
		memberInfoPanel.add(memberIdValuelabel);

		JLabel memberNameLabel = new JLabel("Member Name: ");
		memberInfoPanel.add(memberNameLabel);

		memberNameValuelabel = new JLabel("");
		memberInfoPanel.add(memberNameValuelabel);

		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		memberInfoPanel.add(loyaltypointsLabel);

		memberLoyaltyPointsValueLabel = new JLabel("0");
		memberInfoPanel.add(memberLoyaltyPointsValueLabel);

		return memberInfoPanel;
	}

	private JPanel createPurchaseInfoPanel() {

		purchaseInfoPanel = new JPanel(new GridLayout(0, 2));
		purchaseInfoPanel.setVisible(false);

		purchaseInfoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Checkout  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel totalAmountLabel = new JLabel("Total Amount: ");
		purchaseInfoPanel.add(totalAmountLabel);

		JLabel totalAmountValueLabel = new JLabel("$ 31.70");
		purchaseInfoPanel.add(totalAmountValueLabel);

		JLabel discountLabel = new JLabel("Discount: ");
		purchaseInfoPanel.add(discountLabel);

		JLabel discountValueLabel = new JLabel("$ 1.00");
		purchaseInfoPanel.add(discountValueLabel);

		JLabel totalPayableAmountLabel = new JLabel("Total Payable: ");
		totalPayableAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
		purchaseInfoPanel.add(totalPayableAmountLabel);

		JLabel totalPayableAmountValueLabel = new JLabel("$ 30.70");
		totalPayableAmountValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
		purchaseInfoPanel.add(totalPayableAmountValueLabel);

		return purchaseInfoPanel;
	}


	private JPanel createPurchaseCardPanel() {
		purchaseCardPanel = new JPanel();
		getPurchaseCardPanel().setLayout(new CardLayout());
		GetMemberInfoPanel getMemberInfoPanel = new GetMemberInfoPanel(this, shopApplication);

		getPurchaseCardPanel().add(getMemberInfoPanel, CARD_MEMBER);

		listPurchaseItemPanel = new ListPurchaseItemPanel();
		getPurchaseCardPanel().add(listPurchaseItemPanel, CARD_CART);

		getPurchaseCardPanel().add(createMakePaymentPanel(), CARD_PAYMENT);
		getPurchaseCardPanel().add(createSummaryPanel(), CARD_SUMMARY);
		return getPurchaseCardPanel();
	}

	public void refreshMemberScanStep(Customer member) {
		if (member instanceof Member) {

			memberTypeValuelabel.setText("Member");
			memberIdValuelabel.setText(member.getId());
			memberIdValuelabel.setToolTipText(member.getId());
			memberNameValuelabel.setText(((Member) member).getName());
			Integer displayLoyalPoints = 0;
			if (((Member) member).getLoyalPoints() > 0) {
				displayLoyalPoints = ((Member) member).getLoyalPoints();
			}
			memberLoyaltyPointsValueLabel.setText("" + displayLoyalPoints);
		} else {
			memberTypeValuelabel.setText("None Member");
			memberIdValuelabel.setText(member.getId());
			memberNameValuelabel.setText("N.A.");
			memberLoyaltyPointsValueLabel.setText("N.A.");
		}

		CardLayout cl = (CardLayout) (getPurchaseCardPanel().getLayout());
		cl.show(getPurchaseCardPanel(), CARD_CART);
		memberInfoPanel.setVisible(true);
		purchaseInfoPanel.setVisible(true);

		actionButtonsPanel.getScanItemsButton().setEnabled(true);
		actionButtonsPanel.getCheckoutButton().setEnabled(false);
		actionButtonsPanel.getProceedPaymentButton().setEnabled(true);

	}

	private JPanel createMakePaymentPanel() {

		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Make Payments "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Loyaty  Points: ");
		p.add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Paid Amount: ");
		p.add(categoryNameLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 4);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 5);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 6);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 7);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 8);
		p.add(new JLabel(), gc);
		gc = LayoutHelper.createCellConstraint(0, 9);
		p.add(new JLabel(), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		JTextField loyatyPointsField = new JTextField(15);
		loyatyPointsField.setToolTipText("Please input points to redeem.");
		p.add(loyatyPointsField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		JTextField paidAmountField = new JTextField(15);
		paidAmountField.setToolTipText("Please input total amount paid");
		p.add(paidAmountField, gc);

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.add("North", p);
		outerPanel.add("Center", new JPanel());
		outerPanel.add("South", new JPanel());

		return p;
	}

	private Component createSummaryPanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Transaction Completed!"));
		return p;
	}

	public JPanel getPurchaseCardPanel() {
		return purchaseCardPanel;
	}



}
