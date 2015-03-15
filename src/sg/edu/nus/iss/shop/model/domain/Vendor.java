package sg.edu.nus.iss.shop.model.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.VendorManager;

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

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		if (this.getName() == null) {
			return false;
		}
		Vendor vendor = (Vendor) object;
		return this.getName().equals(vendor.getName());
	}

	/* Lazy loading to be implemented */
	public List<Category> getCategories() {
		if (this.categories == null) { // categories not loaded yet
			loadCategories();
		}
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	private void loadCategories() {
		List<Category> resultCategories = new LinkedList<Category>();
		List<Category> allCategories;
		try {
			allCategories = CategoryManager.getCategoryManager().getAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			setCategories(resultCategories);
			return;
		}
		Iterator<Category> it = allCategories.iterator();
		while (it.hasNext()) {
			Category category = it.next();
			List<Vendor> allVendorsForCategory = category.getVendorList();
			if (allVendorsForCategory.contains(this)) {
				resultCategories.add(category);
			}
		}

	}

}
