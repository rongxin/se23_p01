/**
 * 
 */
package sg.edu.nus.iss.shop.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.shop.model.nondomain.CategoryReport;
import sg.edu.nus.iss.shop.model.nondomain.MemberReport;
import sg.edu.nus.iss.shop.model.nondomain.ProductReport;
import sg.edu.nus.iss.shop.model.nondomain.TransactionReport;

/**
 * @author lokeshkanna-b
 *
 */
public class ReportManager {
	private static ReportManager theOnlyReportManager;
	private static final String dateFormat = "dd/MM/yyyy";
	
	private ReportManager(){
		
	}
	
	public static ReportManager getReportManager(){
		if(ReportManager.theOnlyReportManager == null){
			ReportManager.theOnlyReportManager = new ReportManager();
		}
		return ReportManager.theOnlyReportManager;
	}

	/**
	 * Method to get all Categories and generate report data to show in GUI
	 * 
	 * @return categoriesList
	 * @author lokeshkanna-b
	 *
	 * */
	public List<String[]> getCategoryreport(){
		List<String[]> categoriesList = null;
		
		CategoryReport categoryReport = CategoryReport.getCategoryReport();
		categoriesList = categoryReport.retreiveAndGenerateReportData();
		return categoriesList;
	}
	
	/**
	 * Method to get all Member details and generate the data to show in GUI
	 * 
	 * @return membersList
	 * @author lokeshkanna-b
	 * 
	 * */
	public List<String[]> getMemberReport(){
		List<String[]> membersList = null;
		
		MemberReport memberReport = MemberReport.getMemberReport();
		membersList = memberReport.retreiveAndGenerateReportData();
		return membersList;
	}
	
	/**
	 * Method to get all Product details and generate the data to show in GUI
	 * 
	 * @return productsList
	 * @author lokeshkanna-b
	 * 
	 * */
	public List<String[]> getProductReport(){
		List<String[]> productsList = null;
		
		ProductReport productReport = ProductReport.getProductReport();
		productsList = productReport.retreiveAndGenerateReportData();
		return productsList;
	}

	/**
	 * Method to get all Product details and generate the data to show in GUI
	 * 
	 * @param startDateStr, endDateStr
	 * @return transactionsList
	 * @author lokeshkanna-b
	 * @throws ParseException 
	 * 
	 * */
	public List<String[]> getTransactionReport(String startDateStr, String endDateStr) throws ParseException{
		
		Date startDateObj = null;
		Date endDateObj = null;
		List<String[]> transactionsList = null;
		
		if(startDateStr != null && !startDateStr.isEmpty() 
				&& endDateStr != null && !endDateStr.isEmpty()){
			startDateObj = getDateFromString(startDateStr);
			endDateObj = getDateFromString(endDateStr);
			TransactionReport transactionreport = TransactionReport.getTransactionReport(startDateObj, endDateObj);
			transactionsList = transactionreport.retreiveAndGenerateReportData();
		}
		return transactionsList;
	}
	
	/**
	 * private method too convert a date in String format to Date object
	 * 
	 * @param dateString
	 * @return dateObject
	 * @author lokeshkanna-b
	 * @throws ParseException 
	 * 
	 * */
	private Date getDateFromString(String dateString) throws ParseException{
		
		DateFormat format = new SimpleDateFormat(dateFormat);
		Date dateObject = format.parse(dateString);
		return dateObject;
	}
}