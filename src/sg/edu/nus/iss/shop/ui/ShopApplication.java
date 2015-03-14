package sg.edu.nus.iss.shop.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.dialog.LoginDialog;

public class ShopApplication {
	private ShopMainWindow shopWindow;
	private LoginDialog loginDialog;

	public ShopApplication() {
		loginDialog = new LoginDialog(this);
		loginDialog.pack();
		loginDialog.setLocationByPlatform(true);
		loginDialog.setVisible(true);
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

	// TODO use category manager to load real categories
	public Category[] getCategories() {
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("CLO", "Clothing"));
		categories.add(new Category("MUG", "Mugs"));
		categories.add(new Category("STA", "Stationary"));
		categories.add(new Category("DTA", "Diary"));
		return categories.toArray(new Category[categories.size()]);
	}

	public void addCategory(String categoryCode, String categoryName) {
		System.out.println("Add Category: " + categoryCode + ",  " + categoryName);
		// TODO call categoryManager to perform add logic
	}

}
