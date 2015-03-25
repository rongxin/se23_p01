package sg.edu.nus.iss.shop.ui.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportTransaction extends ReportTabPanel{
	private static final long serialVersionUID = 1L;
		
	public ReportTransaction(ReportWindow reportWindow,
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
		JLabel lblStartDate = new JLabel("Start Date");
		JTextField startDate = new JTextField();
		JLabel lblEndDate = new JLabel("End Date");
		JTextField endDate = new JTextField();
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//How to refresh here???
			}
		});
		panel.add(lblStartDate);
		panel.add(startDate);
		panel.add(lblEndDate);
		panel.add(endDate);
		panel.add(refresh);
		return panel;
	}

	@Override
	public Object[] getHeader() {
		return this.shopApplication.getTransactionReportHeader();
	}

	@Override
	public List<String[]> getData() {
		return this.shopApplication.getTransactionReport("01/01/2015", "31/03/2015");
	}
}
