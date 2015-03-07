package sg.edu.nus.iss.shop.ui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import sg.edu.nus.iss.shop.ui.panel.MainPanel;

public class ShopMainWindow extends JFrame {
	private ShopApplication shopApplication;
	private MainPanel memberPanel;

	private WindowListener windowListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			shopApplication.shutdown();
		}
	};

	public ShopMainWindow(ShopApplication shopApplication) {
		super("Souvenir Shop System");
		this.shopApplication = shopApplication;
		memberPanel = new MainPanel(shopApplication);
		Panel p = new Panel();
		p.setLayout(new GridLayout(0, 1));
		p.add(memberPanel);
		add("Center", p);
		addWindowListener(windowListener);
	}
}
