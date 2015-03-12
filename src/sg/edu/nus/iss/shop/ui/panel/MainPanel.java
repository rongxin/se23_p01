package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.AddCategoryDialog;
import sg.edu.nus.iss.shop.ui.dialog.AddMemberDialog;
import sg.edu.nus.iss.shop.ui.dialog.AddProductDialog;

public class MainPanel extends JPanel {
	private static final String ICONS_PATH = "/sg/edu/nus/iss/shop/ui/icons/";
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public MainPanel(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		add("North", createTitlePanel());
		add("Center", createOptionsPanel());
	}

	private void setMargin(JPanel p) {
		p.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new GridLayout(1, 1));
		setMargin(p);
		JLabel mainTitle = new JLabel("Souvenir Shop Management");
		mainTitle.setFont(new Font("Arial", 1, 18));
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(mainTitle);
		return p;
	}

	private JPanel createOptionsPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 3));
		setMargin(p);


		JButton checkoutButton = createImageButton("Make purchase", "shopping_cart_green.png", "shopping_cart_blue.png");
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Make Purchase  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		checkoutPanel.add(checkoutButton);
		p.add(checkoutPanel);

		JButton addCategoryButton = createImageButton("Add Category", "poll_green.png", "poll_yellow.png");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCategoryDialog d = new AddCategoryDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});
		JPanel categoryPanel = new JPanel();
		categoryPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Category "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		categoryPanel.add(addCategoryButton);
		p.add(categoryPanel);

		JButton addMemberButton = createImageButton("Add  new member", "puzzle_blue.png", "puzzle_red.png");
		addMemberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMemberDialog d = new AddMemberDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});
		JPanel addMemberPanel = new JPanel();
		addMemberPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Add Member  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addMemberPanel.add(addMemberButton);
		p.add(addMemberPanel);

		JButton addProductButton = createImageButton("Add new product", "cd.png", "cd_blue.png");
		addProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProductDialog d = new AddProductDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});
		JPanel addProductPanel = new JPanel();
		addProductPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Add Product  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addProductPanel.add(addProductButton);
		p.add(addProductPanel);

		JButton addInventoryButton = createImageButton("Manage inventory", "bulb.png", "bulb_yellow.png");
		JPanel addInventorytPanel = new JPanel();
		addInventorytPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Add Inventory  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addInventorytPanel.add(addInventoryButton);
		p.add(addInventorytPanel);

		JButton reportingButton = createImageButton("View reports", "pie_chart_green.png", "pie_chart_yellow.png");
		JPanel reportingPanel = new JPanel();
		reportingPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  View Reports  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		reportingPanel.add(reportingButton);
		p.add(reportingPanel);

		return p;

	}

	private JButton createImageButton(String tooltip, String defaultImageName, String rolloverImageName) {
		JButton button = new JButton(new ImageIcon(getClass()
				.getResource(
						ICONS_PATH + defaultImageName)));
		button.setToolTipText(tooltip);

		button.setRolloverIcon(new ImageIcon(getClass().getResource(ICONS_PATH + rolloverImageName)));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}

}
