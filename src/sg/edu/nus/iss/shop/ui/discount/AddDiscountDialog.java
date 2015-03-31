package sg.edu.nus.iss.shop.ui.discount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
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
	private JComboBox<String> appliableCombo;
	private JLabel messageLabel;
	private ListDiscountPanel listPanel;

	public AddDiscountDialog(ShopApplication shopApplication,ListDiscountPanel listPanel) {
		super(shopApplication.getMainWindow().getMainPanel().getCategoryWindow(), "Add Discount");
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Discount "),
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
		discountCodeField.setToolTipText("Please input three-letter code for the new category");
		p.add(discountCodeField, gc);

		gc = LayoutHelper.createCellConstraint(1,1);
		discountDescriptionField = new JTextArea(5, 20);
		discountDescriptionField.setWrapStyleWord(true);
		discountDescriptionField.setLineWrap(true);
		discountDescriptionField.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		discountDescriptionField.setToolTipText("Please input discount description.");
		JScrollPane discountDescriptionFieldScroll = new JScrollPane(discountDescriptionField);
		discountDescriptionFieldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		startDateField.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		p.add(startDateField, gc);


		gc = LayoutHelper.createCellConstraint(1, 4);
		periodField = new JTextField(20);
		periodField.setToolTipText("Please input discount period.");
		p.add(periodField, gc);

		ButtonGroup bg = new ButtonGroup();
		gc = LayoutHelper.createCellConstraint(1, 5);		
		JRadioButton rb1=new JRadioButton("Member" ,true);
		bg.add(rb1);
		p.add(rb1, gc);
		 
		gc = LayoutHelper.createCellConstraint(1, 6);
		JRadioButton rb2=new JRadioButton("All");      
	    bg.add(rb2);	 	   
	    p.add(rb2, gc);
	    
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
		String discountPercentage= percentageField.getText().trim();
		String discountPeriod= periodField.getText().trim();

		if ((discountCode.length() == 0) || (discountDesc.length() == 0 || discountPercentage.length()==0)) {
			MessageHelper.showErrorMessage("All fields are compulsory.");
			return false;
		}
		if (!NumberHelper.isValidDouble(discountPercentage))
		{
			MessageHelper.showErrorMessage("Please input valid percentage.");
			return false;
		}
		if (!NumberHelper.isValidNumber(discountPeriod))
		{
			MessageHelper.showErrorMessage("Please input valid period.");
			return false;
		}

		Double discountPercentageDouble = new Double(discountPercentage);
		Integer discountPeriodInteger= new Integer(discountPeriod);

		//shopApplication.addCategory (discountCode, discountDesc);
		// listPanel.getTableModel().addDiscountToTable(discount);
		return true;
	}


}

