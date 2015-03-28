package sg.edu.nus.iss.shop.ui.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ReportTransaction extends ReportTabPanel {
	private static final long serialVersionUID = 1L;
	private JTextField startDate;
	private JTextField endDate;
	private JLabel errorMessage;

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
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel panelFilters = new JPanel();
		JLabel lblStartDate = new JLabel("Start Date");
		startDate = new JTextField(10);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		startDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		JLabel lblEndDate = new JLabel("End Date");
		endDate = new JTextField(10);

		endDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		panelFilters.add(lblStartDate);
		panelFilters.add(startDate);
		panelFilters.add(lblEndDate);
		panelFilters.add(endDate);
		panelFilters.add(refresh);
		mainPanel.add("Center", panelFilters);
		JPanel errorPanel = new JPanel();
		errorMessage = new JLabel(
				"Please enter date in format dd/MM/yyyy (ie: "
						+ new SimpleDateFormat("dd/MM/yyyy").format(new Date())
						+ ")");
		errorPanel.add(errorMessage);
		mainPanel.add("South", errorPanel);

		return mainPanel;
	}

	@Override
	public Object[] getHeader() {
		return this.shopApplication.getTransactionReportHeader();
	}

	@Override
	public List<String[]> getData() {
		try {
			errorMessage.setForeground(Color.BLACK);
			errorMessage.setText("Please enter date in format dd/MM/yyyy (ie: "
					+ new SimpleDateFormat("dd/MM/yyyy").format(new Date())
					+ ")");
			return this.shopApplication.getTransactionReport(
					startDate.getText(), endDate.getText());
		} catch (ApplicationGUIException | ParseException e) {
			errorMessage.setForeground(Color.RED);
			errorMessage.setText(e.getMessage());
			return null;
		}
	}
}