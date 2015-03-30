package sg.edu.nus.iss.shop.ui.util;

import java.util.List;

import sg.edu.nus.iss.shop.model.domain.Product;

public class PriceHelper {

	public static Double getTotalPrice(List<Product> productsInCart) {
		Double totalPrice = new Double(0);
		for (Product productInCart : productsInCart) {
			totalPrice = productInCart.getPrice() + totalPrice;
		}
		return totalPrice;
	}

	public static String getPriceDisplay(Double price) {
		return "" + String.format("%1$,.2f", price);
	}

	public static boolean isValidPrice(String priceInput) {
		if (priceInput == null || priceInput.trim().equals("")) {
			return false;
		}
		try {
			double d = Double.parseDouble(priceInput);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static boolean ParseDoubleValue(String value)
	{		
		try
		{
			Double doubleValue= new Double(value.trim()); 	
			if(doubleValue<=0){return false;}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public static boolean ParseIntValue(String value)
	{		
		try
		{
			Integer intValue= new Integer(value.trim()); 
			if(intValue<=0){return false;}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	

}
