package sg.edu.nus.iss.shop.ui.discount;

/**
 * @author Xia Rongxin
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.NumberHelper;

public class EditDiscountDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JLabel discountCodeValueLabel;
	private JTextField percentageField;
	private Discount discount;
	private JLabel messageLabel;
	private ListDiscountPanel listPanel;

	public EditDiscountDialog(ShopApplication shopApplication, ListDiscountPanel listPanel, Discount discount) {
		super(shopApplication.getMainWindow().getMainPanel().getCategoryWindow(), "Edit  Discount");
		this.shopApplication = shopApplication;
		this.listPanel = listPanel;
		this.discount = discount;
		setFormPanel(createNewFormPanel());
	}

	@Override
	protected JPanel createFormPanel() {
		return new JPanel();
	}

	private JPanel createNewFormPanel() {
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Discount "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel discountCodeLabel = new JLabel("Discount Code:");
		p.add(discountCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel discountPercentageLabel = new JLabel("Discount Percentage:");
		p.add(discountPercentageLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		discountCodeValueLabel = new JLabel(discount.getDiscountCode());
		p.add(discountCodeValueLabel, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		percentageField = new JTextField(10);
		percentageField.setText("" + discount.getDiscountPercentage());
		percentageField.setToolTipText("Please input percentage.");
		p.add(percentageField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input discount percentage.");
		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String discountPercentage= percentageField.getText().trim();

		if (!NumberHelper.isValidPositiveInteger(discountPercentage))
		{
			MessageHelper.showErrorMessage("Please input a positive integer value.");
			return false;
		}

		Integer discountPercentageValue = new Integer(discountPercentage);

		Discount editedDiscount = shopApplication.editDiscount(discount.getDiscountCode(), discountPercentageValue);
		if (editedDiscount != null) {
			listPanel.getTableModel().updateEditedData(editedDiscount);
			return true;
		}

		return false;
	}


}

