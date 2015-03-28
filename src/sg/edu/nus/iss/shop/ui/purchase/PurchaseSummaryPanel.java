package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class PurchaseSummaryPanel extends JPanel {

	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private LowInventoryProductTableModel tableModel;

	public PurchaseSummaryPanel(CheckoutWindow checkoutWindow, ShopApplication shopApplication) {
		this.checkoutWindow = checkoutWindow;
		this.shopApplication = shopApplication;

		initPurchaseSummaryPanel();
	}

	private void initPurchaseSummaryPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc = LayoutHelper.createCellConstraint(0, 0);
		add(new JLabel("Transaction Completed!"), gc);

		tableModel = new LowInventoryProductTableModel();

		JTable table = new JTable(tableModel);
		table.setName("Low Inventory Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		add(scrollPane);
	}

	public void refreshPurchaseSummaryPanel() {
		List<Product> lowStockProducts = shopApplication.getLowStockProducts(checkoutWindow.getProducts());
		for (Product product : lowStockProducts) {
			tableModel.addItem(product);
		}

	}


}
