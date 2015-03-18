package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
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

	private JButton scanItemsButton;
	private JButton checkoutButton;
	private JButton proceedPaymentButton;

	private Customer customer;
	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;
	private JLabel memberTypeValuelabel;

	private Map<Product, Integer> scannedItems;

	private ListPurchaseItemPanel listPurchaseItemPanel;

	public CheckoutWindow(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createPurchaseCardPanel());
		this.add("East", createRightPanel());
		this.add("South", createMessagePanel());

		scannedItems = new HashMap<>();
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
		p.add(createActionButtonsPanel());
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

	private JPanel createActionButtonsPanel() {

		JPanel p = new JPanel(new GridLayout(0, 1));
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Actions"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		scanItemsButton = new JButton("Scan items");
		scanItemsButton.setEnabled(false);
		scanItemsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarcodeScannerEmulatorDialog d = new BarcodeScannerEmulatorDialog(p.getParent());
				d.pack();
				d.setVisible(true);
				d.addConfirmListener(new ProductScannedActionListener(d, shopApplication));
			}
		});
		p.add(scanItemsButton);

		proceedPaymentButton = new JButton(" Payment");
		proceedPaymentButton.setEnabled(false);
		proceedPaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (purchaseCardPanel.getLayout());
				cl.show(purchaseCardPanel, CARD_PAYMENT);
				checkoutButton.setEnabled(true);
				proceedPaymentButton.setEnabled(false);
			}
		});
		p.add(proceedPaymentButton);

		checkoutButton = new JButton("Checkout");
		checkoutButton.setEnabled(false);
		checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (purchaseCardPanel.getLayout());
				cl.show(purchaseCardPanel, CARD_SUMMARY);
				checkoutButton.setEnabled(false);
				proceedPaymentButton.setEnabled(false);
			}
		});
		p.add(checkoutButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		p.add(cancelButton);
		return p;
	}

	private JPanel createPurchaseCardPanel() {
		purchaseCardPanel = new JPanel();
		purchaseCardPanel.setLayout(new CardLayout());
		purchaseCardPanel.add(createGetMemberPanel(), CARD_MEMBER);

		listPurchaseItemPanel = new ListPurchaseItemPanel();
		purchaseCardPanel.add(listPurchaseItemPanel, CARD_CART);

		purchaseCardPanel.add(createMakePaymentPanel(), CARD_PAYMENT);
		purchaseCardPanel.add(createSummaryPanel(), CARD_SUMMARY);
		return purchaseCardPanel;
	}

	private void refreshMemberScanStep(Customer member) {
		if (member instanceof Member) {
			memberTypeValuelabel.setText("Member");
			memberIdValuelabel.setText(customer.getId());
			memberIdValuelabel.setToolTipText(customer.getId());
			memberNameValuelabel.setText(((Member) member).getName());
			memberLoyaltyPointsValueLabel.setText("" + ((Member) member).getLoyalPoints());
		} else {
			memberTypeValuelabel.setText("None Member");
			memberIdValuelabel.setText(customer.getId().substring(0, 15));
			memberIdValuelabel.setToolTipText(customer.getId());
			memberNameValuelabel.setText("N.A.");
			memberLoyaltyPointsValueLabel.setText("N.A.");
		}

		CardLayout cl = (CardLayout) (purchaseCardPanel.getLayout());
		cl.show(purchaseCardPanel, CARD_CART);
		memberInfoPanel.setVisible(true);
		purchaseInfoPanel.setVisible(true);

		scanItemsButton.setEnabled(true);
		checkoutButton.setEnabled(false);
		proceedPaymentButton.setEnabled(true);

	}

	private Component createGetMemberPanel() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Member Info "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gc = new GridBagConstraints();
		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 1);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 2);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 3);
		p.add(new JLabel(""), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(1, 1);
		JButton scanMemberCardButton = new JButton("Scan Member Card", IconHelper.createImageIcon("member_card.png"));
		scanMemberCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarcodeScannerEmulatorDialog d = new BarcodeScannerEmulatorDialog(p.getParent());
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
				d.addConfirmListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String scannedCardNumber = d.getScannedBarcodeNumber();

						// TODO get member details by member card
						customer = new Member("F42563743156", "Yan Martel", 100);
						refreshMemberScanStep(customer);
						d.setVisible(false);
						d.dispose();
					}
				});

			}
		});
		p.add(scanMemberCardButton, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		JButton publicMemberButton = new JButton("Not a Member", IconHelper.createImageIcon("non_member.png"));
		publicMemberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customer = new NonMemberCustomer();
				refreshMemberScanStep(customer);
			}
		});
		p.add(publicMemberButton, gc);
		gc = LayoutHelper.createCellConstraint(1, 3);

		// column 4
		gc = LayoutHelper.createCellConstraint(2, 0);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 1);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 2);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 3);
		p.add(new JLabel(""));
		return p;
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

}
