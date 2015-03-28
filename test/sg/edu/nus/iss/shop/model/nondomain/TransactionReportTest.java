package sg.edu.nus.iss.shop.model.nondomain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.ReportManager;
import sg.edu.nus.iss.shop.controller.TransactionManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Transaction;

public class TransactionReportTest {

	private TransactionReport transactionReport = null;
	private TransactionManager transactionManager = null;
	private String startDateStr = "01/03/2015";
	private String endDateStr = "28/03/2015";
	DateFormat dateFormat = null;
	private Date startDate = null;
	private Date endDate = null;
	
	@Before
	public void setup(){
		dateFormat = new SimpleDateFormat(ReportManager.dateFormat);
		transactionManager = TransactionManager.getInstance();
	}
	
	@Test
	public void testTransactionReportInitialization(){
		try{
			startDate = dateFormat.parse(startDateStr);
			endDate = dateFormat.parse(endDateStr);
			transactionReport = TransactionReport.getTransactionReport(startDate, endDate);
			assertNotNull("TransactionReport should not be null", transactionReport);
			TransactionReport newTransacReport = TransactionReport.getTransactionReport(startDate, endDate);
			assertSame("The TransactionReport Objects must be same", transactionReport, newTransacReport);
		}catch(ParseException pe){
			pe.printStackTrace();
			Assert.fail("Exception occured while initializing transaction report.");
		}catch(ApplicationGUIException aguie){
			aguie.printStackTrace();
			Assert.fail(aguie.getDisplayMessage());
		}
	}
	
	@Test
	public void testRetreiveAndGenerateReportData(){
		List<String[]> transactionReportList = null;
		List<Transaction> transactionList = null;
		try{
			startDate = dateFormat.parse(startDateStr);
			endDate = dateFormat.parse(endDateStr);
			transactionReport = TransactionReport.getTransactionReport(startDate, endDate);
			transactionReportList = transactionReport.retreiveAndGenerateReportData();
			assertNotNull("The transaction report data should not be null", transactionReportList);
			if(transactionReportList.size() > 0){
				for(String[] transactionArray : transactionReportList){
					assertNotNull("The transaction array should not be null!", transactionArray);
					boolean isInvalidaData = checkArrayHasNullOrEmpty(transactionArray);
					assertFalse("The value inside category array should not be null or empty", isInvalidaData == false);
				}
			}else{
				transactionList = transactionManager.getAllTransaction(startDate, endDate);	
				if(!transactionList.isEmpty()){
					Assert.fail("Problem generating report, transaction between provided date exist!");
				}else{
					transactionList = transactionManager.getAllTransaction();
					if(transactionList != null && transactionList.size() > 0){
						for(Transaction newTransaction : transactionList){
							Date transationDate = newTransaction.getDate();
							if(transationDate.after(startDate) && transationDate.before(endDate)){
								Assert.fail("Problem generating report, transaction between provided date exist!");
							}
						}
					}
				}
			}
			
		}catch(ApplicationGUIException aguie){
			aguie.printStackTrace();
			Assert.fail(aguie.getDisplayMessage());
		}catch(ParseException pe){
			pe.printStackTrace();
			Assert.fail(pe.getMessage());
		}
	}
	
	@Test
	public void testGetreportHeader(){
		transactionReport = TransactionReport.getTransactionReport();
		String[] reportHeaderArray = transactionReport.getReportHeader();
		if(reportHeaderArray != null && reportHeaderArray.length > 0){
			assertFalse("The no of items in heading is incorrect.",reportHeaderArray.length != 6);
		}else{
			Assert.fail("The headers are incorrect");
		}
	}
	
	/**
	 * Method to check whether the array got a invalid data
	 * */
	private boolean checkArrayHasNullOrEmpty(String[] transactionArray){
		
		for(String transactionDetails : transactionArray){
			if(transactionDetails == null || transactionDetails == ""){
				return false;
			}
		}
		return true;
	}
}
