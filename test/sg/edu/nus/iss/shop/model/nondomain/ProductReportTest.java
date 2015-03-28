package sg.edu.nus.iss.shop.model.nondomain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductReportTest {
	
	private ProductReport productReport = null;
	private ProductManager productManager = null;
	
	@Before
	public void setup(){
		productReport = ProductReport.getProductReport();
		productManager = ProductManager.getProductManager();
	}
	
	@Test
	public void testProductReportInitialization(){
		assertNotNull("ProductReport should not be null", productReport);
		ProductReport newProductReport = ProductReport.getProductReport();
		assertSame("The ProductReport Objects must be same", productReport, newProductReport);
		
	}
	
	@Test
	public void testRetreiveAndGenerateReportData(){
		
		List<String[]> productReportList = null;
		List<Product> productList = null;
		try{
			productReportList = productReport.retreiveAndGenerateReportData();
			assertNotNull("The product report data should not be null", productReportList);
			if(productReportList.size() > 0){
				for(String[] productArray : productReportList){
					assertNotNull("The product array should not be null!", productArray);
					boolean isInvalidaData = checkArrayHasNullOrEmpty(productArray);
					assertFalse("The value inside product array should not be null or empty", isInvalidaData == false);
				}
			}else{
				productList = productManager.getAllProducts();	
				assertFalse("The product report data is incorrect!", !productList.isEmpty());
			}
		}catch(ApplicationGUIException aguie){
			aguie.printStackTrace();
			Assert.fail(aguie.getDisplayMessage());
		}
	}
	
	@Test
	public void testGetreportHeader(){
		String[] reportHeaderArray = productReport.getReportHeader();
		if(reportHeaderArray != null && reportHeaderArray.length > 0){
			assertFalse("The no of items in heading is incorrect.",reportHeaderArray.length != 8);
		}else{
			Assert.fail("The headers are incorrect");
		}
	}
	
	/**
	 * Method to check whether the array got a invalid data
	 * */
	private boolean checkArrayHasNullOrEmpty(String[] productArray){
		
		for(String productDetails : productArray){
			if(productDetails == null || productDetails == ""){
				return false;
			}
		}
		return true;
	}

}
