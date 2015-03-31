package sg.edu.nus.iss.shop.ui.discount;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ListDiscountPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private DiscountTableModel tableModel;

	public ListDiscountPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		createMainPanel();

	}

	private void createMainPanel() {
		List<Discount> discounts = shopApplication.getDiscounts();
		tableModel = new DiscountTableModel();

		for (Discount discount : discounts) {
			tableModel.addDiscountToTable(discount);
		}

		JTable table = new JTable(tableModel);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 450));
		this.add(scrollPane);
	}

	public DiscountTableModel getTableModel() {
		return tableModel;
	}

}
