package sg.edu.nus.iss.shop.model.domain;

public class Category {
	private String code;
	private String name;

	/**
	 * Constructor for Category
	 */
	public Category(String code, String name, int runningSequence) {

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
	 *            
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
}
