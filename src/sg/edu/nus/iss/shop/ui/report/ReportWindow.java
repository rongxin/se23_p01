package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	
	public ReportWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createReportTabPanel());
	}
	
	private JPanel createTitlePanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Reports"));
		return p;
	}
	
	private JPanel createReportTabPanel(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Reports  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		ReportTabPanePanel rtp = new ReportTabPanePanel(this, shopApplication);
		p.add(rtp);
		return p;
	}
}
