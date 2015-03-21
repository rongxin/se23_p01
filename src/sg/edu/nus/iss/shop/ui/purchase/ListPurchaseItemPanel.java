package sg.edu.nus.iss.shop.ui.purchase;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListPurchaseItemPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ListPurchaseItemPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Items"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		table = new JTable(new ItemTableModel());
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		this.setSize(800, 600);
	}

	public JTable getTable() {
		return table;
	}


}
