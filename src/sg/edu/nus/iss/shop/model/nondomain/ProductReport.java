package sg.edu.nus.iss.shop.model.nondomain;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;

/**
 * @author lokeshkanna-b
 *
 */
public class ProductReport extends Report{
	
	private static ProductReport theOnlyProductReport;

	private ProductReport(){
		
	}
	
	public static ProductReport getProductReport(){
		if(ProductReport.theOnlyProductReport == null){
			ProductReport.theOnlyProductReport = new ProductReport();
		}
		return ProductReport.theOnlyProductReport;
	}
	
	@Override
	public List<String[]> retreiveAndGenerateReportData() {
		List<Product> productssList = null;
		List<String[]> returnProductsList = new ArrayList<String[]>();
		String[] productArray = null;
		String productName = null;
		String productId = null;
		String productDesc = null;
		int availableQuantity = 0;
		double price = 0;
		String barcodeNumber = null;
		int orderThreshold = 0;
		int orderQuantity = 0;
		
		ProductManager memberManager = ProductManager.getProductManager();
		productssList = memberManager.getAllProducts();
		if(productssList != null && productssList.size() > 0){
			for(Product product : productssList){
				productName = product.getName();
				productId = product.getProductId();
				productDesc = product.getDescription();
				availableQuantity = product.getAvailableQuantity();
				price = product.getPrice();
				barcodeNumber = product.getBarcodeNumber();
				orderThreshold = product.getOrderThreshold();
				orderQuantity = product.getOrderQuantity();
				productArray = new String[]{
						productName, 
						productId, 
						productDesc, 
						String.valueOf(availableQuantity),
						String.valueOf(price), 
						barcodeNumber, 
						String.valueOf(orderThreshold), 
						String.valueOf(orderQuantity)
				};
				returnProductsList.add(productArray);
			}
		}
		return returnProductsList;
	}

}
