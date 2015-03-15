package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ListCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;

	public ListCategoryPanel() {
		super();

		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(createMainPanel(), BorderLayout.CENTER);
		this.add(createActionButtonsPanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
	}

	private JPanel createActionButtonsPanel() {
		JPanel p = new JPanel();

		return p;
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		Object columnNames[] = { "Category Code", "Cateory Name" };
		Object rowData[][] = { { "CLO", "Clothes" }, { "MUG", "Mugs" } };
		JTable table = new JTable(rowData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}

}
