package sg.edu.nus.iss.shop.ui.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.AddCategoryDialog;
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
		ListCategoryPanel listPanel = new ListCategoryPanel();
		p.add(listPanel);
		// JTabbedPane tabbedPane = new JTabbedPane();
		// AddCategoryPanel addPanel = new AddCategoryPanel();
		// tabbedPane.addTab("Add Category", addPanel);
		// tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		//
		// ListCategoryPanel listPanel = new ListCategoryPanel();
		// tabbedPane.addTab("View Categories", listPanel);
		// tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		//
		// p.add(tabbedPane);
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
		JButton addCategoryButton = new JButton("Add Category");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCategoryDialog d = new AddCategoryDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});
		p.add(addCategoryButton);
		return p;
	}

}
