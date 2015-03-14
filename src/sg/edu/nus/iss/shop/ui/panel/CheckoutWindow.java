package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
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

import sg.edu.nus.iss.shop.ui.ShopApplication;

public class CheckoutWindow extends JFrame {

	private static final String PAYMENT_CARD = "paymentCard";
	private static final String CART_CARD = "cartCard";
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JPanel purchaseCardPanel;

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

		JLabel membetTypeValuelabel = new JLabel("Student");
		p.add(membetTypeValuelabel);

		JLabel memberIdLabel = new JLabel("Member ID: ");
		p.add(memberIdLabel);

		JLabel membetIdValuelabel = new JLabel("F42563743156");
		p.add(membetIdValuelabel);

		JLabel memberNameLabel = new JLabel("Member Name: ");
		p.add(memberNameLabel);

		JLabel memberNameValuelabel = new JLabel("Yan Martel");
		p.add(memberNameValuelabel);

		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		p.add(loyaltypointsLabel);

		JLabel loyaltypointsValueLabel = new JLabel("0");
		p.add(loyaltypointsValueLabel);

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

		JButton scanMemberInfoButton = new JButton("Scan Member Card");
		p.add(scanMemberInfoButton);

		JButton scanItemsButton = new JButton("Scan items");
		p.add(scanItemsButton);

		JButton makePaymentButton = new JButton("Make Payment");
		makePaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (purchaseCardPanel.getLayout());
				cl.show(purchaseCardPanel, PAYMENT_CARD);
			}
		});
		p.add(makePaymentButton);
		return p;
	}

	private JPanel createPurchaseCardPanel() {
		purchaseCardPanel = new JPanel();
		purchaseCardPanel.setLayout(new CardLayout());
		purchaseCardPanel.add(createShoppingCartPanel(), CART_CARD);
		purchaseCardPanel.add(createMakePaymentPanel(), PAYMENT_CARD);
		return purchaseCardPanel;
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Payments"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		p.add(new JLabel("Make payment"));
		return p;
	}

}
