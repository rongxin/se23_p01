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
		reportTabs.addTab("Category Report", createReport1());
		reportTabs.addTab("Products Report", createReport2());
		reportTabs.addTab("Transactions Report", createReport3());
		reportTabs.addTab("Members Report", createReport4());
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
