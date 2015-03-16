package sg.edu.nus.iss.shop.ui.main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ShopApplication {
	private ShopMainWindow shopWindow;
	private LoginDialog loginDialog;
	private CategoryManager categoryManager;
	private ProductManager productManager;

	public ShopApplication() {
		loginDialog = new LoginDialog(this);
		loginDialog.pack();
		loginDialog.setLocationByPlatform(true);
		loginDialog.setVisible(true);

		categoryManager = CategoryManager.getCategoryManager();
		productManager = ProductManager.getProductManager();
	}

	public void start() {
		loginDialog.setVisible(true);
	}

	public void login(String userName, String password) {
		// TODO login validation here

		// success to show the main application window
		loginDialog.setVisible(false);
		shopWindow = new ShopMainWindow(this);
		shopWindow.pack();
		shopWindow.setLocationRelativeTo(null);
		shopWindow.setVisible(true);
	}

	public void shutdown() {
		System.exit(0);
	}

	public ShopMainWindow getMainWindow() {
		return shopWindow;
	}

	public static void main(String[] args) {
		ShopApplication shop = new ShopApplication();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		shop.start();
	}

	public List<Category> getCategories() {
		List<Category> allCategories = new ArrayList<>();
		try {
			allCategories = categoryManager.getAllCategories();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}

		// List<Category> categories = new ArrayList<>();
		// categories.add(new Category("CLO", "Clothing"));
		// categories.add(new Category("MUG", "Mugs"));
		// categories.add(new Category("STA", "Stationary"));
		// categories.add(new Category("DTA", "Diary"));
		return allCategories;
	}

	public void addCategory(String categoryCode, String categoryName) {
		System.out.println("Add Category: " + categoryCode + ",  " + categoryName);
		try {
			categoryManager.createCategory(categoryCode, categoryName);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		try {
			products = productManager.getAllProducts();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
		return products;
	}

	public void addProduct(String categoryCode, String name, String description, Integer availableQuantity, Double price,
			String barcodeNumber, Integer orderThreshold, Integer orderQuantity) {

		System.out.println("Add Product ");
		try {
			Category category = categoryManager.getCategory(categoryCode);
			productManager.addProduct(category, name, description, availableQuantity, price.doubleValue(),
					barcodeNumber, orderThreshold, orderQuantity);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}

}
