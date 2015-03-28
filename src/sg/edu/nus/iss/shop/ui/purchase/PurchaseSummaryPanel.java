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
import sg.edu.nus.iss.shop.ui.util.ProductItemsHelper;

public class PurchaseSummaryPanel extends JPanel {

	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private LowInventoryProductTableModel tableModel;
	private JPanel inventoryAlertPanel;

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

		JLabel messageLabel = new JLabel("Transaction Completed!");
		add(messageLabel, gc);
		gc = createInventoryAlertPanel();
		add(inventoryAlertPanel, gc);
	}

	private GridBagConstraints createInventoryAlertPanel() {
		GridBagConstraints gc;
		tableModel = new LowInventoryProductTableModel();

		JTable table = new JTable(tableModel);
		table.setName("Low Inventory Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(300, 300));

		gc = LayoutHelper.createCellConstraint(0, 1);

		inventoryAlertPanel = new JPanel(new BorderLayout());
		JLabel alertLabel = new JLabel("Below products are at low inventory level!");
		alertLabel.setForeground(Color.red);
		inventoryAlertPanel.add("North", alertLabel);
		inventoryAlertPanel.add("Center", scrollPane);
		inventoryAlertPanel.setVisible(false);
		return gc;
	}

	public void refreshPurchaseSummaryPanel() {
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

}
