package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ActionButtonsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String CARD_MEMBER = "memberCard";
	private static final String CARD_PAYMENT = "paymentCard";
	private static final String CARD_CART = "cartCard";
	private static final String CARD_SUMMARY = "summaryCard";

	private JButton scanItemsButton;
	private JButton checkoutButton;
	private JButton proceedPaymentButton;
	private ShopApplication shopApplication;
	private CheckoutWindow checkoutWindow;



	public ActionButtonsPanel(ShopApplication shopApplication, CheckoutWindow checkoutWindow) {
		this.shopApplication = shopApplication;
		this.checkoutWindow = checkoutWindow;

		setLayout(new GridLayout(0, 1));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Actions"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		initActionButtonsPanel();
	}

	private void initActionButtonsPanel() {

		scanItemsButton = new JButton("Scan items");
		scanItemsButton.setEnabled(false);
		scanItemsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarcodeScannerEmulatorDialog d = new BarcodeScannerEmulatorDialog(getParent());
				d.pack();
				d.setVisible(true);
				d.addConfirmListener(new ProductScannedActionListener(d, shopApplication, checkoutWindow));

			}
		});
		add(scanItemsButton);

		proceedPaymentButton = new JButton(" Payment");
		proceedPaymentButton.setEnabled(false);
		proceedPaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (checkoutWindow.getPurchaseCardPanel().getLayout());
				cl.show(checkoutWindow.getPurchaseCardPanel(), CARD_PAYMENT);
				checkoutButton.setEnabled(true);
				proceedPaymentButton.setEnabled(false);
				scanItemsButton.setEnabled(false);
			}
		});
		add(proceedPaymentButton);

		checkoutButton = new JButton("Checkout");
		checkoutButton.setEnabled(false);
		checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (checkoutWindow.getPurchaseCardPanel().getLayout());
				cl.show(checkoutWindow.getPurchaseCardPanel(), CARD_SUMMARY);
				scanItemsButton.setEnabled(false);
				checkoutButton.setEnabled(false);
				proceedPaymentButton.setEnabled(false);

				Transaction transaction = shopApplication.checkout(checkoutWindow.getProducts(), checkoutWindow.getCustomer(),
 checkoutWindow.getLoyalPointsUsed(), checkoutWindow.getDiscount());
			}
		});
		add(checkoutButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkoutWindow.dispose();
			}
		});
		add(cancelButton);

	}

	public JButton getScanItemsButton() {
		return scanItemsButton;
	}

	public JButton getCheckoutButton() {
		return checkoutButton;
	}

	public JButton getProceedPaymentButton() {
		return proceedPaymentButton;
	}
}
