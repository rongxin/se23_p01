package sg.edu.nus.iss.shop.ui.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sg.edu.nus.iss.shop.ui.category.CategoryWindow;
import sg.edu.nus.iss.shop.ui.discount.DiscountWindow;
import sg.edu.nus.iss.shop.ui.member.MemberWindow;
import sg.edu.nus.iss.shop.ui.product.ProductWindow;
import sg.edu.nus.iss.shop.ui.purchase.CheckoutWindow;
import sg.edu.nus.iss.shop.ui.report.ReportWindow;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JLabel shopKeeperInfoLabel;
	private CategoryWindow categoryWindow;
	private CheckoutWindow checkoutWindow;
	private MemberWindow memberWindow;
	private ReportWindow reportWindow;
	private ProductWindow productWindow;
	private DiscountWindow discountWindow;

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
		shopKeeperInfoLabel = new JLabel();
		shopKeeperInfoLabel.setIcon(IconHelper.createImageIcon("user.png"));
		shopKeeperInfoLabel.setText("Hello ");
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
				initializeWindow(checkoutWindow);
				checkoutWindow.setSize(850, 600);
			}
		});
		JPanel checkoutPanel = new JPanel();
		setTitledBorder(checkoutPanel, "  Make Purchase  ");
		checkoutPanel.add(checkoutButton);
		p.add(checkoutPanel);

		JButton memberBtn = createImageButton("Manage Members", "puzzle_blue.png", "puzzle_red.png");
		memberBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				memberWindow = new MemberWindow(shopApplication);
				initializeWindow(memberWindow);
			}
		});
		JPanel memberPanel = new JPanel();
		setTitledBorder(memberPanel, "   Members  ");
		memberPanel.add(memberBtn);
		p.add(memberPanel);

		JButton categoryManagementButton = createImageButton("Categories and Vendors", "poll_green.png", "poll_yellow.png");
		categoryManagementButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				categoryWindow = new CategoryWindow(shopApplication);
				initializeWindow(categoryWindow);
			}
		});
		JPanel categoryPanel = new JPanel();
		setTitledBorder(categoryPanel, "Categories, Vendors ");
		categoryPanel.add(categoryManagementButton);
		p.add(categoryPanel);

		JButton addProductButton = createImageButton("Add new product", "cd.png", "cd_blue.png");
		addProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productWindow = new ProductWindow(shopApplication);
				initializeWindow(productWindow);
			}
		});
		JPanel productPanel = new JPanel();
		setTitledBorder(productPanel, "  Products ");
		productPanel.add(addProductButton);
		p.add(productPanel);

		JButton manageDiscountBtn = createImageButton("Manage Discounts", "bulb.png", "bulb_yellow.png");
		manageDiscountBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				discountWindow = new DiscountWindow(shopApplication);
				initializeWindow(discountWindow);
				discountWindow.setSize(750, 600);
			}
		});
		JPanel discountsPanel = new JPanel();
		setTitledBorder(discountsPanel, "  Discounts  ");
		discountsPanel.add(manageDiscountBtn);
		p.add(discountsPanel);

		JButton reportingButton = createImageButton("View reports", "pie_chart_green.png", "pie_chart_yellow.png");
		reportingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reportWindow = new ReportWindow(shopApplication);
				initializeWindow(reportWindow);
			}
		});
		JPanel reportingPanel = new JPanel();
		setTitledBorder(reportingPanel, "  View Reports  ");
		reportingPanel.add(reportingButton);
		p.add(reportingPanel);

		return p;

	}

	private void setTitledBorder(JPanel panel, String title) {
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(title), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
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

	private void initializeWindow(JFrame window) {
		window.pack();
		window.setLocationByPlatform(true);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public JLabel getShopKeeperInfoLabel() {
		return shopKeeperInfoLabel;
	}

}
