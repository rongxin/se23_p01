package sg.edu.nus.iss.shop.ui.product;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Product;

public class LowStockProductTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Product ID", "Name", "Description", "Quantity", "Price", "Barcode",
			"Reorder Threshold", "Order Quantity", "Action" };

	private List<Object[]> tableData = new ArrayList<>();

	public void addToTable(Product product, JButton orderButton) {
		Object[] rowData = new Object[] { product.getProductId(), product.getName(), product.getDescription(),
				product.getAvailableQuantity(), product.getPrice(), product.getBarcodeNumber(),
				product.getOrderThreshold(), product.getOrderQuantity(), orderButton };
		tableData.add(rowData);
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return getTableData().size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return getTableData().get(row)[col];
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		getTableData().get(row)[col] = value;
		fireTableCellUpdated(row, col);
	}

	public List<Object[]> getTableData() {
		return tableData;
	}

	public void setTableData(List<Object[]> tableData) {
		this.tableData = tableData;
	}
}
