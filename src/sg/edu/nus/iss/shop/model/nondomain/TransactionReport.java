package sg.edu.nus.iss.shop.model.nondomain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import sg.edu.nus.iss.shop.controller.ReportManager;
import sg.edu.nus.iss.shop.controller.TransactionManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;

/**
 * @author lokeshkanna-b
 *
 */

public class TransactionReport extends Report {
	
	private static TransactionReport theOnlyTransactionReport;
	private Date startDate;
	private Date endDate;

	private TransactionReport(){
		this.startDate = new Date();
		this.endDate = new Date();
	}
	
	public static TransactionReport getTransactionReport(Date startDate, Date endDate) throws ApplicationGUIException{
		Date currentDate = new Date();
		if(startDate.after(currentDate)){
			throw new ApplicationGUIException("Start Date cannot be a future Date");
		}else if(endDate.after(currentDate)){
			throw new ApplicationGUIException("End Date cannot be a future Date");
		}else if(startDate.after(endDate)){
			throw new ApplicationGUIException("Start Date cannot be more than End Date");
		}
		if(TransactionReport.theOnlyTransactionReport == null){
			TransactionReport.theOnlyTransactionReport = new TransactionReport();
		}
		//Added by Oscar: The dates are not being updated if the instance is already created.
		TransactionReport.theOnlyTransactionReport.startDate = startDate;
		TransactionReport.theOnlyTransactionReport.endDate = endDate;
		return TransactionReport.theOnlyTransactionReport;
	}
	
	public static TransactionReport getTransactionReport(){
		if(TransactionReport.theOnlyTransactionReport == null){
			TransactionReport.theOnlyTransactionReport = new TransactionReport();
		}
		return TransactionReport.theOnlyTransactionReport;
	}
	
	@Override
	public List<String[]> retreiveAndGenerateReportData() throws ApplicationGUIException, ParseException {
		List<Transaction> transactionsList = null;
		List<String[]> returnTransactionsList = new ArrayList<String[]>();
		String[] transactionArray = null;
		TreeMap<Integer, TreeMap<String, TransactionDetail>> sortedTransactionMap = null;
		TreeMap<String, TransactionDetail> productSortedTDMap = null;
		TransactionDetail transactionDetail = null;
		Transaction transaction = null;
		Date transactionDate = null;
		Customer customerInTransaction = null;
		Product productInTransaction = null;
		String productName = null;
		String productDescription = null;
		int quantity = 0;
		String customerId = null;
		
		TransactionManager transactionManager = TransactionManager.getInstance();
		transactionsList = transactionManager.getAllTransaction(startDate, endDate);
		if(transactionsList != null && transactionsList.size() > 0){
			/*Call method sortTransationsByProductId to get the sorted transaction map*/
			sortedTransactionMap = sortTransationsByProductId(transactionsList);
			if(sortedTransactionMap != null && !sortedTransactionMap.isEmpty()){
				for(Integer transactionId : sortedTransactionMap.keySet()){
					productSortedTDMap = sortedTransactionMap.get(transactionId);
					if(productSortedTDMap != null && !productSortedTDMap.isEmpty()){
						for(String productId : productSortedTDMap.keySet()){
							transactionDetail = productSortedTDMap.get(productId);
							quantity = transactionDetail.getQuantity();
							transaction = transactionDetail.getTransaction();
							productInTransaction = transactionDetail.getProduct();
							/* product Name and Description*/
							productName = productInTransaction.getName();
							productDescription = productInTransaction.getDescription();
							
							transactionDate = transaction.getDate();
							customerInTransaction = transaction.getCustomer();
							
							//customerId = customerInTransaction.getId();
							
							transactionArray = new String[]{
									String.valueOf(transactionId), 
									/*customerId,*/
									productId,
									productName, 
									productDescription, 
									String.valueOf(quantity),
									getDateStringFromObject(transactionDate)
							};
							returnTransactionsList.add(transactionArray);
							
						}
					}
				}
			}else{
				throw new ApplicationGUIException("Unabe to sort transaction!");
			}
		}
		return returnTransactionsList;
	}
	
	private TreeMap<Integer, TreeMap<String, TransactionDetail>> sortTransationsByProductId(List<Transaction> transactionsList) {
		
		TreeMap<Integer, TreeMap<String, TransactionDetail>> returnSortedTransactionMap = null;
		TreeMap<String, TransactionDetail> sortedProductTDMap = null;
		int transactionId = 0;
		List<TransactionDetail> transDetailList = null;
		Product productInTransaction = null;
		String productId = null;
		
		returnSortedTransactionMap = new TreeMap<Integer, TreeMap<String, TransactionDetail>>();
		
		/*Iterating Transaction, to sort by product*/
		for(Transaction transaction : transactionsList){
			transactionId = transaction.getId();
			transDetailList = transaction.getTransactionDetails();
			if(transDetailList != null && transDetailList.size() > 0){
				/*Check to handle duplicate transaction, If not required can be commented*/
				sortedProductTDMap = returnSortedTransactionMap.get(transactionId);
				if(sortedProductTDMap == null){
					sortedProductTDMap = new TreeMap<String, TransactionDetail>();
				}
				/*Iterating Transaction detail to get the product Id to sort*/
				for(TransactionDetail transDetail : transDetailList){
					productInTransaction = transDetail.getProduct();
					productId = productInTransaction.getProductId();
					sortedProductTDMap.put(productId, transDetail);
				}
				returnSortedTransactionMap.put(transactionId, sortedProductTDMap);
			}
		}
		
		return returnSortedTransactionMap;
	}
	
	private String getDateStringFromObject(Date dateObj) throws ParseException{
		
		DateFormat format = new SimpleDateFormat(ReportManager.dateFormat);
		String dateObject = format.format(dateObj);
		return dateObject;
	}

	@Override
	public String[] getReportHeader() {

		String[] transactionReportHeader = new String[]{
				"Transaction Id",
				/*"Category Id",*/
				"Product Id",
				"Product Name",
				"Product Desc",
				"Quantity",
				"Transaction Date"
		};
		return transactionReportHeader;	
	}
}
