package sg.edu.nus.iss.shop.ui.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.ShopApplication;

public class ListCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public ListCategoryPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Category> categories = shopApplication.getCategories();
		Object categoryData[][] = new Object[categories.size()][2];

		int i = 0;
		for (Category category : categories) {
			categoryData[i][0] = category.getCode();
			categoryData[i][1] = category.getName();
			i++;
		}

		Object columnNames[] = { "Category Code", "Cateory Name" };
		// Object rowData[][] = { { "CLO", "Clothes" }, { "MUG", "Mugs" } };
		JTable table = new JTable(categoryData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}




}
