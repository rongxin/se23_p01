package sg.edu.nus.iss.shop.ui.report;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.report.ReportWindow;

public class ReportTabPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ReportWindow reportWindow;
	private ShopApplication shopApplication;

	public ReportTabPanel(ReportWindow reportWindow,
			ShopApplication shopApplication) {
		super();
		this.reportWindow = reportWindow;
		this.shopApplication = shopApplication;
		this.add(createTapPanel());
		setVisible(true);
	}

	private JPanel createTapPanel() {
		JPanel p = new JPanel();
		JTabbedPane reportTabs = new JTabbedPane();
		reportTabs.addTab("report 1", createReport1());
		reportTabs.addTab("report 2", createReport2());
		reportTabs.addTab("report 3", createReport3());
		reportTabs.addTab("report 4", createReport4());
		p.add(reportTabs);
		p.setSize(800, 600);
		return p;
	}

	private JPanel createReport1() {
		JPanel report1 = new JPanel();
		report1.add(new JLabel("This goes the report 1"));
		return report1;
	}

	private JPanel createReport2() {
		JPanel report = new JPanel();
		Object columnNames[] = { "Category Code", " Category Name", "Description" };

		List<String[]> categoryReport = shopApplication.getCategoryReport();
		if (categoryReport != null) {
			Object reportData[][] = new Object[categoryReport.size()][categoryReport.get(0).length];

			int i = 0;
			for (String[] data : categoryReport) {
				int j = 0;
				for (String value : data){
					reportData[i][j] = value;
					j++;
				}
				i++;
			}

			JTable table = new JTable(reportData, columnNames);
			table.setName("Items");
			table.setEnabled(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			JScrollPane scrollPane = new JScrollPane(table);
			report.add(scrollPane);
		}else{
			report.add(new JLabel("Could not load report 2"));
		}
		return report;
	}

	private JPanel createReport3() {
		JPanel report = new JPanel();
		report.add(new JLabel("This goes the report 3"));
		return report;
	}

	private JPanel createReport4() {
		JPanel report = new JPanel();
		report.add(new JLabel("This goes the report 4"));
		return report;
	}
}
