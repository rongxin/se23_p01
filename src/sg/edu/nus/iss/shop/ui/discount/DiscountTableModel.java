package sg.edu.nus.iss.shop.ui.discount;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import sg.edu.nus.iss.shop.model.domain.Discount;

public class DiscountTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Discount Code", "Description", "Discount Percentage", "Start Date", "Period",
			"Applicable to Member", "Edit" };

	private List<Object[]> tableData = new ArrayList<>();

	public void addDiscountToTable(Discount discount, JButton editButton) {
		Object[] rowData = new Object[] { discount.getDiscountCode(), discount.getDescription(),
				"" + discount.getDiscountPercentage(), discount.getStartDate(), discount.getDiscountInDays(),
				discount.getApplicableToMember(), editButton };
		tableData.add(rowData);
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
