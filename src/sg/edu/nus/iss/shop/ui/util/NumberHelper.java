package sg.edu.nus.iss.shop.ui.util;

public class NumberHelper {

	public static boolean isValidNumber(String priceInput) {
		if (priceInput == null || priceInput.trim().equals("")) {
			return false;
		}
		try {
			double d = Integer.parseInt(priceInput);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
