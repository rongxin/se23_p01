package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.AddCategoryDialog;

public class MainPanel extends JPanel {
	private ShopApplication shopApplication;

	public MainPanel(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		JLabel mainLebel = new JLabel("Souvenir Shop");
		mainLebel.setHorizontalAlignment(SwingConstants.CENTER);
		add("North", mainLebel);
		add("Center", createOptionsPanel());
	}

	private JPanel createOptionsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));



		panel.add(new JButton("Checkout "));
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

		panel.add(addCategoryButton);
		panel.add(new JButton("Add Member"));
		panel.add(new JButton("Add Product"));
		panel.add(new JButton("Check Inventory"));
		panel.add(new JButton("Reporting"));
		return panel;
	}

}
