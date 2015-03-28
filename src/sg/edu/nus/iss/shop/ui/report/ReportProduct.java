package sg.edu.nus.iss.shop.ui.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportProduct extends ReportTabPanel{
	private static final long serialVersionUID = 1L;
		
	public ReportProduct(ReportWindow reportWindow,
			ShopApplication shopApplication) {
		super(reportWindow, shopApplication);
	}

	@Override
	public JPanel createReportTablePanel() {
		return refreshPanel();
	}

	@Override
	public JPanel createFilterPanel() {
		JPanel panel = new JPanel();
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		panel.add(refresh);
		return panel;
	}

	@Override
	public Object[] getHeader() {
		return this.shopApplication.getProductReportHeader();
	}

	@Override
	public List<String[]> getData() {
		return this.shopApplication.getProductReport();
	}
}
