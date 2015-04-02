package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.report.ReportWindow;

public abstract class ReportTabPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected ReportWindow reportWindow;
	protected ShopApplication shopApplication;
	protected ReportTableModel reportTableModel;

	public ReportTabPanel(ReportWindow reportWindow,
			ShopApplication shopApplication) {
		super();
		this.reportWindow = reportWindow;
		this.shopApplication = shopApplication;
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createFilterPanel());
		this.add("Center", createReportTablePanel());
	}

	public abstract JPanel createReportTablePanel();

	public abstract JPanel createFilterPanel();

	public abstract Object[] getHeader();

	public abstract List<String[]> getData();

	protected JPanel refreshPanel() {
		JPanel report = new JPanel();
		Object[] columnNames = getHeader();

		List<String[]> unformatedReportData = getData();
		// System.out.println(unformatedReportData.size());
		if (unformatedReportData != null && unformatedReportData.size() > 0) {
			reportTableModel = new ReportTableModel(columnNames,
					unformatedReportData);
		} else {
			reportTableModel = new ReportTableModel(columnNames,
					new ArrayList<String[]>());
		}

		JTable table = new JTable(reportTableModel);
		table.setName("Items");
		table.setEnabled(false);
		if(table.getPreferredSize().width < table.getPreferredScrollableViewportSize().width){
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		}else{
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		report.add(scrollPane);
		return report;
	}

	protected void refreshTable() {
		// System.out.println("Start refresh");
		List<String[]> unformatedReportData = getData();
		if (unformatedReportData != null && unformatedReportData.size() > 0) {
			// System.out.println("Number of rows: " +
			// unformatedReportData.size());
			reportTableModel.setTableData(unformatedReportData);
		} else {
			reportTableModel.setTableData(new ArrayList<String[]>());
		}
	}
}
