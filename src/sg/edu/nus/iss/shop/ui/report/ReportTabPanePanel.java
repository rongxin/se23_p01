package sg.edu.nus.iss.shop.ui.report;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportTabPanePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ReportWindow reportWindow;
	private ShopApplication shopApplication;

	public ReportTabPanePanel(ReportWindow reportWindow,
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
		return p;
	}

	private JPanel createReport1() {
		ReportCategory rc = new ReportCategory(reportWindow, shopApplication);
		return rc;
	}

	private JPanel createReport2() {
		ReportProduct rp = new ReportProduct(reportWindow, shopApplication);
		return rp;
	}

	private JPanel createReport3() {
		ReportTransaction rt = new ReportTransaction(reportWindow,
				shopApplication);
		return rt;
	}

	private JPanel createReport4() {
		ReportMember rm = new ReportMember(reportWindow,
				shopApplication);
		return rm;
	}
}
