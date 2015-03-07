package sg.edu.nus.iss.shop.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ShopApplication {
	private ShopWindow shopWindow;

	public ShopApplication() {
		shopWindow = new ShopWindow(this);
		shopWindow.pack();
		shopWindow.setLocationRelativeTo(null);
	}

	public void start() {
		shopWindow.setVisible(true);
	}

	public void shutdown() {
		System.exit(0);
	}

	public static void main(String[] args) {
		ShopApplication shop = new ShopApplication();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		shop.start();
	}

}
