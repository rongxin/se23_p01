/**
 * 
 */
package sg.edu.nus.iss.shop.model.nondomain;

import java.util.List;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;

/**
 * @author lokeshkanna-b
 *
 */
public abstract class Report {
	/**
	 * Abstract method to generate the report data
	 * to be implemented in the extending class
	 * @throws ApplicationGUIException 
	 * */
	abstract public List<String[]> retreiveAndGenerateReportData() throws ApplicationGUIException;
	
}
