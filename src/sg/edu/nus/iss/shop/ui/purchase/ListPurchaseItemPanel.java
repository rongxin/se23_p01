package sg.edu.nus.iss.shop.ui.purchase;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ListPurchaseItemPanel extends JPanel implements TableModelListener {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ListPurchaseItemPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Items"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		// Object columnNames[] = { "Product ID", "Product Name", "Price",
		// "Quantity", "SubTotal" };
		// Object rowData[][] = { { "CLO/1", "Centenary Jumper", 21.45, 1,
		// 21.45, },
		// { "MUG/1", "Centenary Mug", 10.25, 1, 10.25,
		// "A really nice mug this time" } };
		// JTable table = new JTable(rowData, columnNames);
		table = new JTable(new ItemTableModel());
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		this.setSize(800, 600);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);

	}

}
