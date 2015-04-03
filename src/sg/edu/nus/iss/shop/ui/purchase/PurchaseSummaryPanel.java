package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;
import sg.edu.nus.iss.shop.ui.util.ProductItemsHelper;

public class PurchaseSummaryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private LowInventoryProductTableModel tableModel;
	private JPanel inventoryAlertPanel;

	private JLabel cashReceivedValueLabel;
	private JLabel changesGivenValueLabel;

	public PurchaseSummaryPanel(CheckoutWindow checkoutWindow, ShopApplication shopApplication) {
		this.checkoutWindow = checkoutWindow;
		this.shopApplication = shopApplication;

		initPurchaseSummaryPanel();
	}

	private void initPurchaseSummaryPanel() {
		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Purchase Summary "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gc = new GridBagConstraints();
		gc = LayoutHelper.createCellConstraint(0, 0);
		add(createTransactionSummaryPanel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		add(createInventoryAlertPanel(), gc);
	}

	private JPanel createTransactionSummaryPanel() {
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel messageLabel = new JLabel("Transaction Completed!");
		p.add(messageLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel cashReceivedLabel = new JLabel("Cash Received ($): ");
		p.add(cashReceivedLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		JLabel changesGivenLabel = new JLabel("Changes Given  ($): ");
		p.add(changesGivenLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 5);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(0, 6);
		p.add(new JLabel(), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		cashReceivedValueLabel = new JLabel("");
		p.add(cashReceivedValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		changesGivenValueLabel = new JLabel("");
		p.add(changesGivenValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 3);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(1, 4);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(1, 5);
		p.add(new JLabel(), gc);

		gc = LayoutHelper.createCellConstraint(1, 6);
		p.add(new JLabel(), gc);

		return p;
	}


	private JPanel createInventoryAlertPanel() {
		tableModel = new LowInventoryProductTableModel();

		JTable table = new JTable(tableModel);
		table.setName("Low Inventory Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(300, 250));


		inventoryAlertPanel = new JPanel(new BorderLayout());
		JLabel alertLabel = new JLabel("Below products are at low inventory level!");
		alertLabel.setForeground(Color.red);
		inventoryAlertPanel.add("North", alertLabel);
		inventoryAlertPanel.add("Center", scrollPane);
		inventoryAlertPanel.setVisible(false);
		return inventoryAlertPanel;
	}

	public void updateAfterCheckout() {
		cashReceivedValueLabel.setText(PriceHelper.getPriceDisplay(checkoutWindow.getCashReceived()));
		changesGivenValueLabel.setText(PriceHelper.getPriceDisplay(checkoutWindow.getChangesGiven()));

		Hashtable<Product, Integer> productsWithCounts = ProductItemsHelper
				.convertProductListToHashTable(checkoutWindow.getProducts());
		ArrayList<Product> uniqueProducts = Collections.list(productsWithCounts.keys());
		List<Product> lowStockProducts = shopApplication
				.getLowStockProducts(uniqueProducts);
		if (lowStockProducts.size() > 0) {
			inventoryAlertPanel.setVisible(true);
		}

		for (Product product : lowStockProducts) {
			tableModel.addItem(product);
		}

	}

	public JPanel getInventoryAlertPanel() {
		return inventoryAlertPanel;
	}

	public JLabel getCashReceivedValueLabel() {
		return cashReceivedValueLabel;
	}

	public JLabel getChangesGivenValueLabel() {
		return changesGivenValueLabel;
	}

}
