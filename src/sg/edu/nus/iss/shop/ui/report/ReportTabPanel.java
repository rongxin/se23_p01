package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
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
	
	protected JPanel refreshPanel(){
		JPanel report = new JPanel();
		Object[] columnNames = getHeader();

		List<String[]> unformatedReportData = getData();
		//System.out.println(unformatedReportData.size());
		if (unformatedReportData != null && unformatedReportData.size() > 0) {
			//Object reportData[][] = new Object[unformatedReportData.size()][unformatedReportData
			//		.get(0).length];
			ArrayList<Object[]> reportData = new ArrayList<Object[]>();
			for (String[] data : unformatedReportData) {
				int j = 0;
				Object rowData[] = new Object[unformatedReportData.get(0).length];
				for (String value : data) {
					rowData[j] = value;
					j++;
				}
				reportData.add(rowData);
			}
			reportTableModel = new ReportTableModel(columnNames, unformatedReportData);
			
			JTable table = new JTable(reportTableModel);
			table.setName("Items");
			table.setEnabled(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			JScrollPane scrollPane = new JScrollPane(table);
			report.add(scrollPane);
		} else {
			report.add(new JLabel("Could not load report"));
		}
		return report;
	}
	
	protected void refreshTable(){
		//System.out.println("Start refresh");
		List<String[]> unformatedReportData = getData();
		//	System.out.println("Number of rows: " + unformatedReportData.size());
		reportTableModel.setTableData(unformatedReportData);
		
	}
}
