package sg.edu.nus.iss.shop.ui.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Product;

public class ItemTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Product ID", "Product Name", "Price", "Qty", "SubTotal", "Edit" };


	private List<Object[]> tableData = new ArrayList<>();

	private Map<Product, Integer> items = new HashMap<Product, Integer>();

	/**
	 * Add a new product to the current items
	 *
	 * @param item
	 */
	public void addItem(Product item, JButton editButton) {
		if (items.get(item) == null) {
			items.put(item, 1);
			Integer itemCount = items.get(item);
			Object[] rowData = new Object[] { item.getProductId(), item.getName(), item.getPrice(), itemCount,
					item.getPrice() * itemCount, editButton };
			tableData.add(rowData);
		} else {
			Integer itemQty = items.get(item);
			items.put(item, itemQty + 1);
			updateTableData(item);
		}

		fireTableDataChanged();
	}

	private void updateTableData(Product item) {
		Integer itemCount = items.get(item);

		for (Object[] row : tableData) {
			if (row[0].equals(item.getProductId())) {
					row[3] = itemCount;
					row[4] = item.getPrice() * itemCount;
			}
		}
	}

	/**
	 * update product qty in the table model
	 *
	 * @param item
	 * @param qty
	 */
	public void editItem(Product item, Integer qty) {
		if (items.get(item) != null) {
			items.put(item, qty);
			updateTableData(item);
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

	public Map<Product, Integer> getItems() {
		return items;
	}

}
