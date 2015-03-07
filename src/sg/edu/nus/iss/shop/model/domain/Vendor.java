package sg.edu.nus.iss.shop.model.domain;

import java.util.LinkedList;
import java.util.List;

public class Vendor {
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/*Lazy loading to be implemented*/
	public List<Category> getCategories(){
		return new LinkedList<Category>();
	}

}
