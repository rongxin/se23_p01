package sg.edu.nus.iss.shop.ui.product;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ListLowStockProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private LowStockProductTableModel tableModel;

	public ListLowStockProductPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Product> products = shopApplication.getLowStockProducts();
		tableModel = new LowStockProductTableModel();

		for (Product product : products) {
			tableModel.addToTable(product);
		}

		JTable table = new JTable(tableModel);
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("Action").setCellRenderer(buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table));

		table.setName("Items");
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(750, 450));
		p.add(scrollPane);
		return p;
	}

	public LowStockProductTableModel getTableModel() {
		return tableModel;
	}

}
