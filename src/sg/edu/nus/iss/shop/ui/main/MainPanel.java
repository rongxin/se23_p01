package sg.edu.nus.iss.shop.ui.main;

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

import sg.edu.nus.iss.shop.ui.category.CategoryWindow;
import sg.edu.nus.iss.shop.ui.member.MemberWindow;
import sg.edu.nus.iss.shop.ui.product.ProductWindow;
import sg.edu.nus.iss.shop.ui.purchase.CheckoutWindow;
import sg.edu.nus.iss.shop.ui.report.ReportWindow;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private CategoryWindow categoryWindow;
	private CheckoutWindow checkoutWindow;
	private MemberWindow memberWindow;
	private ReportWindow reportWindow;
	private ProductWindow productWindow;

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
				checkoutWindow = new CheckoutWindow(shopApplication);
				checkoutWindow.pack();
				checkoutWindow.setLocationByPlatform(true);
				checkoutWindow.setLocationRelativeTo(null);
				checkoutWindow.setVisible(true);
			}
		});
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Make Purchase  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		checkoutPanel.add(checkoutButton);
		p.add(checkoutPanel);

		JButton memberBtn = createImageButton("Manage Members", "puzzle_blue.png", "puzzle_red.png");
		memberBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				memberWindow = new MemberWindow(shopApplication);
				memberWindow.pack();
				memberWindow.setLocationByPlatform(true);
				memberWindow.setLocationRelativeTo(null);
				memberWindow.setVisible(true);
			}
		});
		JPanel memberPanel = new JPanel();
		memberPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("   Members  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		memberPanel.add(memberBtn);
		p.add(memberPanel);

		JButton categoryManagementButton = createImageButton("Categories and Vendors", "poll_green.png", "poll_yellow.png");
		categoryManagementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				categoryWindow = new CategoryWindow(shopApplication);
				categoryWindow.pack();
				categoryWindow.setLocationByPlatform(true);
				categoryWindow.setLocationRelativeTo(null);
				categoryWindow.setVisible(true);
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
				productWindow = new ProductWindow(shopApplication);
				productWindow.pack();
				productWindow.setLocationByPlatform(true);
				productWindow.setLocationRelativeTo(null);
				productWindow.setVisible(true);
			}
		});
		JPanel productPanel = new JPanel();
		productPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Products "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		productPanel.add(addProductButton);
		p.add(productPanel);

		JButton manageDiscountBtn = createImageButton("Manage Discounts", "bulb.png", "bulb_yellow.png");
		JPanel discountsPanel = new JPanel();
		discountsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Discounts  "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		discountsPanel.add(manageDiscountBtn);
		p.add(discountsPanel);

		JButton reportingButton = createImageButton("View reports", "pie_chart_green.png", "pie_chart_yellow.png");
		reportingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reportWindow = new ReportWindow(shopApplication);
				reportWindow.pack();
				reportWindow.setLocationByPlatform(true);
				reportWindow.setLocationRelativeTo(null);
				reportWindow.setVisible(true);
			}
		});
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

	public CategoryWindow getCategoryWindow() {
		return categoryWindow;
	}

	public CheckoutWindow getCheckoutWindow() {
		return checkoutWindow;
	}

	public MemberWindow getMemberWindow() {
		return memberWindow;
	}

	public ReportWindow getReportWindow() {
		return reportWindow;
	}

	public ProductWindow getProductWindow() {
		return productWindow;
	}

}
