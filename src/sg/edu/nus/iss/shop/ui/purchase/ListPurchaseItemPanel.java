package sg.edu.nus.iss.shop.ui.purchase;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListPurchaseItemPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ListPurchaseItemPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Items"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		Object columnNames[] = { "Product ID", "Product Name", "Price", "Quantity", "SubTotal" };
		Object rowData[][] = { { "CLO/1", "Centenary Jumper", 21.45, 1, 21.45, },
				{ "MUG/1", "Centenary Mug", 10.25, 1, 10.25, "A really nice mug this time" } };
		JTable table = new JTable(rowData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		this.setSize(800, 600);
	}

}
