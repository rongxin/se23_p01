package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sg.edu.nus.iss.shop.ui.IconHelper;
import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.AddMemberDialog;
import sg.edu.nus.iss.shop.ui.dialog.AddProductDialog;
import sg.edu.nus.iss.shop.ui.window.CategoryWindow;
import sg.edu.nus.iss.shop.ui.window.CheckoutWindow;

public class MainPanel extends JPanel {
	private static final String ICONS_PATH = "/sg/edu/nus/iss/shop/ui/icons/";
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public MainPanel(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		add("North", createTitlePanel());
		add("Center", createOptionsPanel());
		add("South", createInformationPanel());
	}

	private void setMargin(JPanel p) {
		p.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private JPanel createInformationPanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		JLabel shopKeeperInfoLabel = new JLabel();
		shopKeeperInfoLabel.setIcon(IconHelper.createImageIcon("user.png"));
		shopKeeperInfoLabel.setText("Hello Stacy");
		shopKeeperInfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p.add(shopKeeperInfoLabel);
		setMargin(p);
		return p;
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
		checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckoutWindow w = new CheckoutWindow(shopApplication);
				w.pack();
				w.setLocationByPlatform(true);
				w.setLocationRelativeTo(null);
				w.setVisible(true);
			}
		});
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Make Purchase  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		checkoutPanel.add(checkoutButton);
		p.add(checkoutPanel);

		JButton addMemberButton = createImageButton("Register new member", "puzzle_blue.png", "puzzle_red.png");
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
		addMemberPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Register Member  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addMemberPanel.add(addMemberButton);
		p.add(addMemberPanel);

		JButton categoryManagementButton = createImageButton("Categories and Vendors", "poll_green.png", "poll_yellow.png");
		categoryManagementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryWindow w = new CategoryWindow(shopApplication);
				w.pack();
				w.setLocationByPlatform(true);
				w.setLocationRelativeTo(null);
				w.setVisible(true);
			}
		});
		JPanel categoryPanel = new JPanel();
		categoryPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Categories, Vendors "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		categoryPanel.add(categoryManagementButton);
		p.add(categoryPanel);

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

		JButton checkInventoryButton = createImageButton("Manage inventory", "bulb.png", "bulb_yellow.png");
		JPanel addInventorytPanel = new JPanel();
		addInventorytPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Manage Inventory  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addInventorytPanel.add(checkInventoryButton);
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
		JButton button = new JButton(IconHelper.createImageIcon(defaultImageName));
		button.setToolTipText(tooltip);
		button.setRolloverIcon(IconHelper.createImageIcon(rolloverImageName));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}

}
