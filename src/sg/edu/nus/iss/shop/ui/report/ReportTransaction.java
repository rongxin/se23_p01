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
	private JTextField startDate;
	private JTextField endDate;
		
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
		startDate = new JTextField(10);
		startDate.setText("01/03/2015");
		JLabel lblEndDate = new JLabel("End Date");
		endDate = new JTextField(10);
		endDate.setText("28/03/2015");
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
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
		//System.out.println("Start Date: " + startDate.getText());
		//System.out.println("End Date: " + endDate.getText());
		return this.shopApplication.getTransactionReport(startDate.getText(), endDate.getText());
	}
}
