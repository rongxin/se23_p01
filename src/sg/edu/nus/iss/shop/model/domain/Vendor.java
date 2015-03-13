package sg.edu.nus.iss.shop.model.domain;

import java.util.LinkedList;
import java.util.List;

public class Vendor {
	private String name;
	private String description;
	private List<Category> categories;

	public Vendor(String name, String description) {
		this.name = name;
		this.description = description;
	}

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
		Vendor vendor = (Vendor)object;
		return this.getName().equals(vendor.getName());
	}

	/* Lazy loading to be implemented */
	public List<Category> getCategories() {
		if (this.categories == null){
			//load categories
		}
		return this.categories;
	}
	
	public void setCategories(List<Category> categories){
		this.categories = categories;
	}

}
