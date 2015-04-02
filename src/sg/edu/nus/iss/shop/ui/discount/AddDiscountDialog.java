package sg.edu.nus.iss.shop.ui.discount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.NumberHelper;

public class AddDiscountDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JTextField discountCodeField;
	private JTextArea discountDescriptionField;
	private JTextField percentageField;
	private JTextField startDateField;
	private JTextField periodField;
	private JRadioButton radMember;
	private JRadioButton radAll;
	private JLabel messageLabel;
	private ListDiscountPanel listPanel;

	public AddDiscountDialog(ShopApplication shopApplication,
			ListDiscountPanel listPanel) {
		super(shopApplication.getMainWindow().getMainPanel()
				.getCategoryWindow(), "Add Discount");
		this.shopApplication = shopApplication;
		this.listPanel = listPanel;
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
		p.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(" Add Discount "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel discountCodeLabel = new JLabel("Discount Code:");
		p.add(discountCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel discountDescriptionLabel = new JLabel("Discount Description:");
		p.add(discountDescriptionLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 2);
		JLabel discountPercentageLabel = new JLabel("Discount Percentage:");
		p.add(discountPercentageLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 3);
		JLabel discountStartDateLabel = new JLabel("Start Date:");
		p.add(discountStartDateLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 4);
		JLabel discountPeroidLabel = new JLabel("Period:");
		p.add(discountPeroidLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 5);
		JLabel discountAppliableLabel = new JLabel("Appliable to member:");
		p.add(discountAppliableLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		discountCodeField = new JTextField(20);
		discountCodeField
		.setToolTipText("Please input three-letter code for the new category");
		p.add(discountCodeField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		discountDescriptionField = new JTextArea(5, 20);
		discountDescriptionField.setWrapStyleWord(true);
		discountDescriptionField.setLineWrap(true);
		discountDescriptionField.setBorder(BorderFactory
				.createLineBorder(Color.lightGray));
		discountDescriptionField
		.setToolTipText("Please input discount description.");
		JScrollPane discountDescriptionFieldScroll = new JScrollPane(
				discountDescriptionField);
		discountDescriptionFieldScroll
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		p.add(discountDescriptionFieldScroll, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		percentageField = new JTextField(20);
		percentageField.setToolTipText("Please input percentage.");
		p.add(percentageField, gc);

		gc = LayoutHelper.createCellConstraint(1, 3);
		startDateField = new JTextField(10);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		startDateField.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal
				.getTime()));
		p.add(startDateField, gc);

		gc = LayoutHelper.createCellConstraint(1, 4);
		periodField = new JTextField(20);
		periodField.setToolTipText("Please input discount period.");
		p.add(periodField, gc);

		gc = LayoutHelper.createCellConstraint(1, 5);
		radMember = new JRadioButton("Member", true);
		radMember.setMnemonic(KeyEvent.VK_C);
		radMember.setSelected(true);
		p.add(radMember, gc);

		gc = LayoutHelper.createCellConstraint(1, 6);
		radAll = new JRadioButton("All");
		radAll.setMnemonic(KeyEvent.VK_M);
		ButtonGroup applicableButtonGroup = new ButtonGroup();
		p.add(radAll, gc);
		applicableButtonGroup.add(radMember);
		applicableButtonGroup.add(radAll);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input discount information.");
		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String discountCode = discountCodeField.getText().trim();
		String discountDesc = discountDescriptionField.getText().trim();
		String discountPercentageValue = percentageField.getText().trim();
		String discountPeriodValue = periodField.getText().trim();
		String startDate = startDateField.getText().trim();
		String reformattedStartDate = "";
		
		if (discountCode.length() == 0) {
			MessageHelper.showErrorMessage("Please input discount code.");
			return false;
		} else if (!NumberHelper
				.isValidPositiveInteger(discountPercentageValue)) {
			MessageHelper
			.showErrorMessage("Please input valid discount percentage.");
			return false;
		} else if (!NumberHelper.isValidPositiveInteger(discountPeriodValue)
				&& !discountPeriodValue.equals(Discount.ALWAY_VALID_DAYS)) {
			MessageHelper
			.showErrorMessage("Please input valid discount period.");
			return false;
		}

		Integer discountPercentage = new Integer(discountPercentageValue);
		// Integer discountPeriod= new Integer(discountPeriodValue);

		String discountApplicableTo = "";
		if (radMember.isSelected()) {
			discountApplicableTo = "M";
		} else {
			discountApplicableTo = "A";
		}

		// convert date format
		if (!startDate.equals(Discount.ALWAY_VALID_START_DATE)) {
			SimpleDateFormat fromUI = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat discountStartDate = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				reformattedStartDate = discountStartDate.format(fromUI
						.parse(startDate));
			} catch (ParseException e) {
				MessageHelper.showErrorMessage("Reformat date error.");
				return false;
			}
		}else{
			reformattedStartDate = startDate;
		}
		
		Discount discount = shopApplication.addDiscount(discountCode,
				discountDesc, discountPercentage, reformattedStartDate,
				discountPeriodValue, discountApplicableTo);

		if (discount != null) {
			JButton editButton = new JButton(IconHelper.createImageIcon("edit.png"));
			editButton.addActionListener(new EditDiscountListener(
					shopApplication, listPanel, discount));
			listPanel.getTableModel().addDiscountToTable(discount, editButton);
			return true;
		}

		return false;
	}
}
