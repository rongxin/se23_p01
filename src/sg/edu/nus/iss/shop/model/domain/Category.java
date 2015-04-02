/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.model.domain;

import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.util.ILogger;
import sg.edu.nus.iss.shop.util.Logger;

public class Category {
	private String code;
	private String name;
	private List<Vendor> vendorList;
	private List<Product> productList;
	private ILogger log = Logger.getLog();

	/**
	 * Constructor for Category
	 * @param code category code
	 * @param name category name
	 */
	public Category(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/**
	 * Constructor for Category with vendor list 
	 * @param code category code
	 * @param name category name
	 * @param vendorList List of vendors for this category
	 */
	public Category(String code, String name , List<Vendor> vendorList) {
		this.code = code;
		this.name = name;
		this.vendorList = vendorList;
	}
	
	/**
	 * Method to get Category Code
	 * @return category code
	 * */
	public String getCode() {
		return code;
	}

	/**
	 * Setter Method for Category Code
	 * @param code category code to be set   
	 * */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Method to get Category Name
	 * 
	 * @return category name        
	 * */
	public String getName() {
		return name;
	}

	/**
	 * Setter Method for Category Name
	 * 
	 * @param name category name to be set
	 * */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Overriding equals() to compare two Complex objects
	 * @param object object to be compared
	 * @return boolean return true/false based on the comparison by category code
	 * */
	@Override
	public boolean equals(Object object){
		if (object == null){
			return false;
		}
		if (!this.getClass().equals(object.getClass())){
			return false;
		}
		if (this.getName() == null){
			return false;
		}
		Category category = (Category)object;
		return this.getCode().equals(category.getCode());
	}
	
	/**
	 * Method to get Vendor list 
	 * @return vendor list for this category      
	 * */
	public List<Vendor> getVendorList() {
		//Lazy load vendor list
		loadVendorList();
		return this.vendorList;
	}
	
	/**
	 * Method to set Vendor list for this particular category
	 * @param vendor list for this category that has been retrieved     
	 * */
	public void setVendorList(List<Vendor> vendorList) {
 		this.vendorList = vendorList;
 	}
	
	/**
	 * Method to load Vendor list for this category by calling persistent service    
	 * */
	private void loadVendorList() {
		this.vendorList = new LinkedList<Vendor>();
		try {
			this.vendorList = PersistentService.getService().retrieveVendors(this);
		} catch (Exception e) {
			log.log("loadVendorList:"+ e.toString());
		}
		setVendorList(this.vendorList);
	}
	
	/**
	 * Method to get Product list
	 * @return product list for this category      
	 * */
	public List<Product> getProductList() {
		//Lazy load product list
		loadProductList();
		return this.productList;
	}
	
	/**
	 * Method to set product list for this particular category
	 * @param vendor list for this category that has been retrieved     
	 * */
	public void setProductList(List<Product> productList) {
 		this.productList = productList;
 	}
	
	/**
	 * Method to load product list for this category by calling ProductManager and iterate through the records
	 * */
	private void loadProductList() {
		this.productList = new LinkedList<Product>();
		try {
			//Retrieve all product 
			List<Product> allProducts = ProductManager.getProductManager().getAllProducts();
			if(allProducts != null && !allProducts.isEmpty()) {
				for(Product prod : allProducts) {
					//Check if Product belongs to this category
					if(prod.getProductId().substring(0,getCode().length()).equals(getCode())) {
						this.productList.add(prod);
					}
				}
			}
		} catch (Exception e) {
			log.log("loadProductList:"+e.toString());
		}
		setProductList(this.productList );
	}
	

	
}
