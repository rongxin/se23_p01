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
		p.setLayout(new GridLayout(3, 2));
		setMargin(p);

		JButton checkoutButton = createImageButton("Make purchase", "shopping_cart_green.png", "shopping_cart_blue.png");
		p.add(checkoutButton);
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

		p.add(addCategoryButton);
		JButton addMemberButton = createImageButton("Add  new member", "puzzle_blue.png", "puzzle_red.png");
		p.add(addMemberButton);
		JButton addProductButton = createImageButton("Add new product", "cd.png", "cd_blue.png");
		p.add(addProductButton);
		JButton addInventoryButton = createImageButton("Manage inventory", "bulb.png", "bulb_yellow.png");
		p.add(addInventoryButton);
		JButton reportingButton = createImageButton("View reports", "pie_chart_green.png", "pie_chart_yellow.png");
		p.add(reportingButton);

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
