package sg.edu.nus.iss.shop.ui.category;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Category;

public class CategoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Category Code", "Category Name", "Action" };

	private List<Object[]> tableData = new ArrayList<>();

	public void addToTable(Category category, JButton vendorButton) {
		Object[] rowData = new Object[] { category.getCode(), category.getName(), vendorButton };
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
