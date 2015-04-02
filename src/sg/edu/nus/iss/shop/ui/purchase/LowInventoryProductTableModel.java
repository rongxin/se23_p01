package sg.edu.nus.iss.shop.ui.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Product;

public class LowInventoryProductTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Product ID", "Product Name", "Stock Number" };
	private List<Object[]> tableData = new ArrayList<>();

	public void addItem(Product item) {
		Object[] rowData = new Object[] { item.getProductId(), item.getName(), item.getAvailableQuantity() };
		tableData.add(rowData);

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
