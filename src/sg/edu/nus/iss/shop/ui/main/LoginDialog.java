package sg.edu.nus.iss.shop.ui.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.StoreKeeper;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;

public class LoginDialog extends OkCancelDialog {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JLabel messageLabel;
	private JTextField userNameField;
	private JPasswordField passwordField;

	public LoginDialog(ShopApplication shopApplication) {
		super(shopApplication.getMainWindow(), "StoreKepper Login");
		this.shopApplication = shopApplication;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel();
		ImageIcon logoIcon = IconHelper.createImageIcon("bag.png");
		JLabel title = new JLabel("", logoIcon, SwingConstants.CENTER);
		p.add(title);
		return p;
	}


	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Login "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		LayoutHelper.createCellConstraint(0, 0);
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Username: ");
		p.add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Password: ");
		p.add(categoryNameLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;

		userNameField = new JTextField(15);
		// TODO remove later
		userNameField.setText("Stacy");
		userNameField.setToolTipText("Please input your username.");
		p.add(userNameField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		passwordField = new JPasswordField(15);
		// TODO remove later
		passwordField.setText("Stacy");
		passwordField.setToolTipText("Please input name for the category");
		p.add(passwordField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input your username and password.");

		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String userName = userNameField.getText().trim();
		String password = new String(passwordField.getPassword());
		if ("".equals(userName) || "".equals(password)) {
			MessageHelper.showErrorMessage("Please input username or password.");
		}
		StoreKeeper loggedInUser = shopApplication.login(userName, password);
		if (loggedInUser == null) {
			MessageHelper.showErrorMessage("Login failed!");
			return false;
		}

		shopApplication.setLoggedInUser(loggedInUser);
		shopApplication.getMainWindow().getMainPanel().getShopKeeperInfoLabel()
				.setText("Hello " + loggedInUser.getName());
		return true;
	}

}
