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

}
