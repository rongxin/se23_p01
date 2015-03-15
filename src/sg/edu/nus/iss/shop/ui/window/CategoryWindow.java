package sg.edu.nus.iss.shop.ui.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.panel.AddCategoryPanel;
import sg.edu.nus.iss.shop.ui.panel.ListCategoryPanel;

public class CategoryWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public CategoryWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
		this.add("East", createRightPanel());
		this.add("South", createMessagePanel());
	}

	private Component createMainPanel() {
		Panel p = new Panel();


		AddCategoryPanel addPanel = new AddCategoryPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Add Category", addPanel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		ListCategoryPanel listPanel = new ListCategoryPanel();
		tabbedPane.addTab("View Categories", listPanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		p.add(tabbedPane);
		return p;
	}

	private Component createRightPanel() {
		Panel p = new Panel();
		return p;
	}

	private JPanel createMessagePanel() {
		JPanel p = new JPanel();
		JLabel messageLabel = new JLabel();
		messageLabel.setText("Category management.");
		p.add(messageLabel);
		return p;
	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Manage Categories"));
		return p;
	}

}
