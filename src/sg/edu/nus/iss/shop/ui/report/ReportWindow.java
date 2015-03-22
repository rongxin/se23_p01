package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	
	private String reportPanel1;
	private String reportPanel2;
	private String reportPanel3;
	private String reportPanel4;
	
	public ReportWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		//this.add("North", createTitlePanel());
		//this.add("Center", createMainPanel());
	}
}
