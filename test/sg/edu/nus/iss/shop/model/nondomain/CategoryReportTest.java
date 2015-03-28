package sg.edu.nus.iss.shop.model.nondomain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;

public class CategoryReportTest {
	
	private CategoryReport categoryReport = null;
	private CategoryManager categoryManager = null;
	
	@Before
	public void setup(){
		categoryReport = CategoryReport.getCategoryReport();
		categoryManager = CategoryManager.getCategoryManager();
	}
	
	@Test
	public void testCatergoryReportInitialization(){
		assertNotNull("CategoryReport should not be null", categoryReport);
		CategoryReport newCategoryReport = CategoryReport.getCategoryReport();
		assertSame("The CategoryReport Objects must be same", categoryReport, newCategoryReport);
		
	}
	
	@Test
	public void testRetreiveAndGenerateReportData(){
		
		List<String[]> categoryReportList = null;
		List<Category> categoryList = null;
		try{
			categoryReportList = categoryReport.retreiveAndGenerateReportData();
			assertNotNull("The category report data should not be null", categoryReportList);
			if(categoryReportList.size() > 0){
				for(String[] categoryArray : categoryReportList){
					assertNotNull("The category array should not be null!", categoryArray);
					boolean isInvalidaData = checkArrayHasNullOrEmpty(categoryArray);
					assertFalse("The value inside category array should not be null or empty", isInvalidaData == false);
				}
			}else{
				categoryList = categoryManager.getAllCategories();	
				assertFalse("The category report data is incorrect!", !categoryList.isEmpty());
			}
		}catch(ApplicationGUIException aguie){
			aguie.printStackTrace();
			Assert.fail(aguie.getDisplayMessage());
		}
		
	}
	
	@Test
	public void testGetreportHeader(){
		String[] reportHeaderArray = categoryReport.getReportHeader();
		if(reportHeaderArray != null && reportHeaderArray.length > 0){
			for(String reportHeader : reportHeaderArray){
				
			}
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
