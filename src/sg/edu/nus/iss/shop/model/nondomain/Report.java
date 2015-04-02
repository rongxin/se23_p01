/**
 * 
 */
package sg.edu.nus.iss.shop.model.nondomain;

import java.text.ParseException;
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
	 * @throws ParseException 
	 * */
	abstract public List<String[]> retreiveAndGenerateReportData() throws ApplicationGUIException, ParseException;
	
	/**
	 * Abstract method to get the header to be diplayed in the report
	 * 
	 * */
	abstract public String[] getReportHeader();
}
