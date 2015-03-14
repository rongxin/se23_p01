package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;
import sg.edu.nus.iss.shop.ui.LayoutHelper;
import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.ScanMemberCardDialog;

public class CheckoutWindow extends JFrame {

	private static final String CARD_MEMBER = "memberCard";
	private static final String CARD_PAYMENT = "paymentCard";
	private static final String CARD_CART = "cartCard";
	private static final String CARD_SUMMARY = "summaryCard";

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JPanel purchaseCardPanel;

	private JButton checkoutButton;
	private JButton proceedPaymentButton;

	private Customer customer;
	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;
	private JLabel memberTypeValuelabel;

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
		p.add(createChecoutInfoPanel());
		p.add(createActionButtonsPanel());
		return p;
	}

	private JPanel createMemerInfoPanel() {

		JPanel p = new JPanel(new GridLayout(0, 2));

		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Member Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel memberTypeLabel = new JLabel("Member Type: ");
		p.add(memberTypeLabel);

		memberTypeValuelabel = new JLabel("");
		p.add(memberTypeValuelabel);

		JLabel memberIdLabel = new JLabel("Member ID: ");
		p.add(memberIdLabel);

		memberIdValuelabel = new JLabel("");
		p.add(memberIdValuelabel);

		JLabel memberNameLabel = new JLabel("Member Name: ");
		p.add(memberNameLabel);

		memberNameValuelabel = new JLabel("");
		p.add(memberNameValuelabel);

		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		p.add(loyaltypointsLabel);

		memberLoyaltyPointsValueLabel = new JLabel("0");
		p.add(memberLoyaltyPointsValueLabel);

		return p;
	}

	private JPanel createChecoutInfoPanel() {

		JPanel p = new JPanel(new GridLayout(0, 2));

		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Checkout  Information "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel totalAmountLabel = new JLabel("Total Amount: ");
		p.add(totalAmountLabel);

		JLabel totalAmountValueLabel = new JLabel("$ 31.70");
		p.add(totalAmountValueLabel);

		JLabel discountLabel = new JLabel("Discount: ");
		p.add(discountLabel);

		JLabel discountValueLabel = new JLabel("$ 1.00");
		p.add(discountValueLabel);

		JLabel totalPayableAmountLabel = new JLabel("Total Payable: ");
		totalPayableAmountLabel.setFont(new Font("Arial", Font.BOLD, 16));
		p.add(totalPayableAmountLabel);

		JLabel totalPayableAmountValueLabel = new JLabel("$ 30.70");
		totalPayableAmountValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
		p.add(totalPayableAmountValueLabel);

		return p;
	}

	private JPanel createActionButtonsPanel() {

		JPanel p = new JPanel(new GridLayout(0, 1));
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Actions"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton scanItemsButton = new JButton("Scan items");
		p.add(scanItemsButton);

		proceedPaymentButton = new JButton(" Payment");
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
		purchaseCardPanel.add(createShoppingCartPanel(), CARD_CART);
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
		checkoutButton.setEnabled(false);
		proceedPaymentButton.setEnabled(true);

	}

	private Component createGetMemberPanel() {
		JPanel p = new JPanel(new GridLayout(0, 2));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		JButton scanMemberCardButton = new JButton("Scan Member Card");
		scanMemberCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScanMemberCardDialog d = new ScanMemberCardDialog(p.getParent());
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
				d.addConfirmListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String scannedCardNumber = d.getCardNumberField().getText().trim();

						// TODO get member details by member card
						customer = new Member("F42563743156", "Yan Martel", 100);
						refreshMemberScanStep(customer);
						d.setVisible(false);
						d.dispose();
					}
				});

			}
		});
		p.add(scanMemberCardButton);

		JButton publicMemberButton = new JButton("Public Member");
		publicMemberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customer = new NonMemberCustomer();
				refreshMemberScanStep(customer);
			}
		});
		p.add(publicMemberButton);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		return p;
	}

	private JPanel createShoppingCartPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Items"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		Object columnNames[] = { "Product ID", "Product Name", "Price", "Quantity", "SubTotal", "Description" };
		Object rowData[][] = { { "CLO/1", "Centenary Jumper", 21.45, 1, 21.45, "A really nice momento" },
				{ "MUG/1", "Centenary Mug", 10.25, 1, 10.25, "A really nice mug this time" } };
		JTable table = new JTable(rowData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		p.setSize(800, 600);
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
