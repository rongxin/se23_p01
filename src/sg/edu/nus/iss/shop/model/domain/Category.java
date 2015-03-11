/**
* @author  robincher
*/
package sg.edu.nus.iss.shop.model.domain;

import java.util.List;

import sg.edu.nus.iss.shop.model.domain.Vendor;

public class Category {
	private String code;
	private String name;
	private List<Vendor> vendorList;

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
		return this.code;
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
		return this.name;
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
	 * Method to get Vendor list 
	 * 
	 * @return vendor list for this category      
	 * */
	public List<Vendor> getVendorList() {
		return this.vendorList;
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
	
}
