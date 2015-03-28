package sg.edu.nus.iss.shop.ui.category;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

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
			tableModel.addToTable(category);
		}

		JTable table = new JTable(tableModel);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}

	public CategoryTableModel getTableModel() {
		return tableModel;
	}


}
