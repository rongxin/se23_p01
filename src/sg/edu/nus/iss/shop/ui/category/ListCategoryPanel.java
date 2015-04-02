package sg.edu.nus.iss.shop.ui.category;

/**
 * @author Xia Rongxin
 */
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class ListCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CategoryTableModel tableModel;

	public ListCategoryPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Category> categories = shopApplication.getCategories();

		tableModel = new CategoryTableModel();
		for (Category category : categories) {
			JButton vendorButton = new JButton(IconHelper.createImageIcon("vendor.png"));
			vendorButton.addActionListener(new ViewCategoryVendorListener(shopApplication, category));
			tableModel.addToTable(category, vendorButton);
		}

		JTable table = new JTable(tableModel);
		table.getColumn("Vendor").setCellRenderer(new JTableButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setName("Items");
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 450));
		p.add(scrollPane);
		return p;
	}

	public CategoryTableModel getTableModel() {
		return tableModel;
	}


}
