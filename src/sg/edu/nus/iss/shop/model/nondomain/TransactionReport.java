package sg.edu.nus.iss.shop.model.nondomain;

import java.util.Date;
import java.util.List;

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
	
	private TransactionReport(Date startDate, Date endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public static TransactionReport getTransactionReport(Date startDate, Date endDate){
		if(TransactionReport.theOnlyTransactionReport == null){
			TransactionReport.theOnlyTransactionReport = new TransactionReport(startDate, endDate);
		}
		return TransactionReport.theOnlyTransactionReport;
	}
	
	
	@Override
	public List<String[]> retreiveAndGenerateReportData() {

		
		return null;
	}
}
