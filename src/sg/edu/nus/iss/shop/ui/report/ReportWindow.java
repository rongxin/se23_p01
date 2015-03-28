package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;
import java.awt.Font;

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
		JLabel title = new JLabel("Reports");
		title.setFont(new Font("Arial", 1, 18));
		p.add(title);
		return p;
	}
	
	private JPanel createReportTabPanel(){
		JPanel p = new JPanel();
		ReportTabPanePanel rtp = new ReportTabPanePanel(this, shopApplication);
		p.add(rtp);
		return p;
	}
}
