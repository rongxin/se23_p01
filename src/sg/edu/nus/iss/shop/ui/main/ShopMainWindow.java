package sg.edu.nus.iss.shop.ui.main;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class ShopMainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private MainPanel mainPanel;

	private WindowListener windowListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			shopApplication.shutdown();
		}
	};

	public ShopMainWindow(ShopApplication shopApplication) {
		super("Souvenir Shop System");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
		this.shopApplication = shopApplication;
		mainPanel = new MainPanel(shopApplication);
		Panel p = new Panel();
		p.setLayout(new GridLayout(0, 1));
		p.add(mainPanel);
		add("Center", p);
		addWindowListener(windowListener);
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}
}
