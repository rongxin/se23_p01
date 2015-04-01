package sg.edu.nus.iss.shop.ui.util;

/**
 * @author Xia Rongxin
 */
public class NumberHelper {

	/**
	 * check if the input string is a valid positive integer
	 *
	 * @param strInput
	 * @return
	 */
	public static boolean isValidPositiveInteger(String strInput) {
		if (strInput == null || strInput.trim().equals("")) {
			return false;
		}
		Integer d = null;
		try {
			d = Integer.parseInt(strInput);
		} catch (NumberFormatException nfe) {
			return false;
		}

		if (d < 0) {
			return false;
		}

		return true;
	}

	/**
	 * check if the input string is a valid positive double value
	 *
	 * @param strInput
	 * @return
	 */
	public static boolean isValidPositiveDouble(String strInput) {
		if (strInput == null || strInput.trim().equals("")) {
			return false;
		}

		Double d = null;
		try {
			d = Double.parseDouble(strInput);
		} catch (NumberFormatException nfe) {
			return false;
		}

		if (d < 0) {
			return false;
		}

		return true;
	}
}
