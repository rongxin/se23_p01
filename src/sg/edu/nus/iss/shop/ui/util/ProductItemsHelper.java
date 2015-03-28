package sg.edu.nus.iss.shop.ui.util;

import java.util.Hashtable;
import java.util.List;

import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductItemsHelper {

	public static Hashtable<Product, Integer> convertProductListToHashTable(List<Product> products) {
		Hashtable<Product, Integer> productsWithCount = new Hashtable<>();

		for (Product product : products) {
			if (productsWithCount.get(product) == null) {
				productsWithCount.put(product, 1);
			} else {
				Integer itemQty = productsWithCount.get(product);
				productsWithCount.put(product, itemQty + 1);
			}
		}
		return productsWithCount;
	}

}
