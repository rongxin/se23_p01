package sg.edu.nus.iss.shop.ui.product;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
			JButton orderButton = new JButton("Order");
			orderButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(orderButton), "Order for product:"
							+ product.getProductId());

				}
			});
			tableModel.addToTable(product, orderButton);
		}

		JTable table = new JTable(tableModel);
		table.getColumn("Action").setCellRenderer(new JTableButtonRenderer());
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
