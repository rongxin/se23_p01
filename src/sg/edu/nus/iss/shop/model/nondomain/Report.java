/**
 * 
 */
package sg.edu.nus.iss.shop.model.nondomain;

import java.util.List;

/**
 * @author lokeshkanna-b
 *
 */
public abstract class Report {
	/**
	 * Abstract method to generate the report data
	 * to be implemented in the extending class
	 * */
	abstract public List<String[]> retreiveAndGenerateReportData();
	
}
