package sg.edu.nus.iss.shop.model.nondomain;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;
/**
 * @author lokeshkanna-b
 *
 */

public class CategoryReport extends Report {

	private static CategoryReport theOnlyCategoryReport;
	
	private CategoryReport(){
		
	}
	
	public static CategoryReport getCategoryReport(){
		if(CategoryReport.theOnlyCategoryReport == null){
			CategoryReport.theOnlyCategoryReport = new CategoryReport();
		}
		return CategoryReport.theOnlyCategoryReport;
	}

	@Override
	public List<String[]> retreiveAndGenerateReportData() throws ApplicationGUIException {

		List<Category> categoriesList = null;
		List<String[]> returnCategoriesList = new ArrayList<String[]>();
		String[] categoryArray = null;
		String categoryCode = null;
		String categoryName = null;
		List<Vendor> vendorsList = null;
		Integer vendorListSize = 0;
		
		CategoryManager categoryManager = CategoryManager.getCategoryManager();
		categoriesList = categoryManager.getAllCategories();
		if(categoriesList != null && categoriesList.size() > 0){
			for(Category category : categoriesList){
				categoryCode = category.getCode();
				categoryName = category.getName();
				vendorsList = category.getVendorList();
				if(vendorsList != null && vendorsList.size() > 0){
					vendorListSize = vendorsList.size();
				}
				categoryArray = new String[]{
						categoryCode, 
						categoryName, 
						vendorListSize.toString()
				};
				returnCategoriesList.add(categoryArray);
			}
		}
		return returnCategoriesList;
	}

	@Override
	public String[] getReportHeader() {
		
		String[] categoryReportHeader = new String[]{
				"Category Code",
				"Category Name",
				"No.of Providing Vendors"
		};
		return categoryReportHeader;
	}

}
