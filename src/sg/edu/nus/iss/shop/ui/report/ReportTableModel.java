package sg.edu.nus.iss.shop.ui.report;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ReportTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private Object[] columnNames;
	private List<String[]> tableData = new ArrayList<>();
	
	public ReportTableModel(Object[] columnNames, List<String[]> tableData) {
		super();
		this.columnNames = columnNames;
		this.tableData = tableData;
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
	public Object getValueAt(int row, int col) {
		return getTableData().get(row)[col];
	}
	
	@Override
	public String getColumnName(int col) {
		return (String) columnNames[col];
	}
	
	public List<String[]> getTableData() {
		return tableData;
	}

	public void setTableData(List<String[]> tableData) {
		this.tableData = tableData;
		fireTableDataChanged();
	}
}
