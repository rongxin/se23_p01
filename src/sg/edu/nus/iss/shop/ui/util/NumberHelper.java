package sg.edu.nus.iss.shop.ui.util;

public class NumberHelper {

	public static boolean isValidNumber(String strInput) {
		if (strInput == null || strInput.trim().equals("")) {
			return false;
		}
		try {
			double d = Integer.parseInt(strInput);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isValidDouble(String strInput) {
		if (strInput == null || strInput.trim().equals("")) {
			return false;
		}
		try {
			double d = Double.parseDouble(strInput);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
