package sg.edu.nus.iss.shop.ui.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Product;

public class ItemTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Product ID", "Product Name", "Price", "Quantity", "SubTotal" };
	// private Object[][] data ={ { "CLO/1", "Centenary Jumper", 21.45, 1,
	// 21.45, },
	// { "MUG/1", "Centenary Mug", 10.25, 1, 10.25,
	// "A really nice mug this time" } };

	private List<Object[]> tableData = new ArrayList<>();

	private Map<String, Integer> items = new HashMap<String, Integer>();

	/**
	 * Add a new product to the current items
	 *
	 * @param item
	 */
	public void addItem(Product item) {
		if (items.get(item.getProductId()) == null) {
			// System.out.println("First time item");
			items.put(item.getProductId(), 1);
			Integer itemCount = items.get(item.getProductId());
			Object[] rowData = new Object[] { item.getProductId(), item.getName(), item.getPrice(), itemCount,
					item.getPrice() * itemCount };
			tableData.add(rowData);
		} else {
			// System.out.println("Updating count");
			Integer itemQty = items.get(item.getProductId());
			// System.out.println("itemQty:" + itemQty);
			items.put(item.getProductId(), itemQty + 1);
			Integer itemCount = items.get(item.getProductId());

			for (Object[] row : tableData) {
				if (row[0].equals(item.getProductId())) {
					row[3] = itemCount;
					row[4] = item.getPrice() * itemCount;
				}
			}
		}

		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return tableData.get(row)[col];
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		tableData.get(row)[col] = value;
		fireTableCellUpdated(row, col);
	}
}
