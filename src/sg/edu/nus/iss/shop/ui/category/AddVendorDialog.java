package sg.edu.nus.iss.shop.ui.category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

/**
 * @author Xia Rongxin
 */
public class AddVendorDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private Category category;
	private JTextField vendorNameField;
	private JTextField vendorDescriptionField;
	private JLabel messageLabel;
	private ListVendorPanel listPanel;

	public AddVendorDialog(ShopApplication shopApplication, ListVendorPanel listPanel, Category category) {
		super((JFrame) SwingUtilities.getWindowAncestor(listPanel), "Add Vendor");
		this.shopApplication = shopApplication;
		this.listPanel = listPanel;
		this.category = category;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(createInputFormPanel(), BorderLayout.CENTER);
		mainPanel.add(createFormMessagePanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
		return mainPanel;
	}


	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Vendor "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Vendor Name: ");
		p.add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Vendor Description: ");
		p.add(categoryNameLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		vendorNameField = new JTextField(20);
		vendorNameField.setToolTipText("Please input name for the vendor");
		p.add(vendorNameField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		vendorDescriptionField = new JTextField(20);
		vendorDescriptionField.setToolTipText("Please input description for the vendor");
		p.add(vendorDescriptionField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input vendor name and description.");

		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String vendorName = vendorNameField.getText().trim();
		String vendorDescription = vendorDescriptionField.getText().trim();
		if ((vendorName.length() == 0 || vendorDescription.length() == 0)) {
			messageLabel.setText("Vendor name and description are compulsory.");
			messageLabel.setForeground(Color.RED);
			return false;
		}

		Vendor vendor = shopApplication.addVendor(category, vendorName, vendorDescription);

		if (vendor != null) {
			listPanel.getTableModel().addToTable(vendor);
		}
		return true;
	}

	public JTextField getVendorDescriptionField() {
		return vendorDescriptionField;
	}
}

