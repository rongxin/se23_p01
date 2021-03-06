package sg.edu.nus.iss.shop.ui.member;

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

import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.ui.OkCancelDialog;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.TextFieldLimit;

/**
 *
 * @author Xia Rongxin
 *
 */
public class AddMemberDialog extends OkCancelDialog {

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JTextField cardNumberField;
	private JTextField nameField;
	private JLabel messageLabel;

	private ListMemberPanel listPanel;

	public AddMemberDialog(ShopApplication shopApplication, ListMemberPanel listPanel) {
		super(shopApplication.getMainWindow().getMainPanel().getMemberWindow(), "Add Member");
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
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Member "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel memberNameLabel = new JLabel("Member Name: ");
		p.add(memberNameLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel cardNumberLabel = new JLabel("Student/Staff Card Number: ");
		p.add(cardNumberLabel, gc);
		// column 2

		gc = LayoutHelper.createCellConstraint(1, 0);
		nameField = new JTextField(20);
		nameField.setToolTipText("Please input name of the new member.");
		p.add(nameField, gc);


		gc = LayoutHelper.createCellConstraint(1, 1);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		cardNumberField = new JTextField(15);
		cardNumberField.setDocument(new TextFieldLimit(15));
		cardNumberField.setToolTipText("Please input student or staff card number.");
		p.add(cardNumberField, gc);

		return p;
	}

	private JPanel createFormMessagePanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		messageLabel = new JLabel(" ", SwingConstants.CENTER);
		messageLabel.setText("Please input member information.");

		p.add(messageLabel);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		boolean allFiedsOk = validateFields();
		if (!allFiedsOk) {
			return false;
		}

		String cardNumber = cardNumberField.getText().trim();
		String memberName = nameField.getText().trim();

		Member member = shopApplication.addMember(cardNumber, memberName);
		if (member != null) {
			listPanel.getTableModel().addToTable(member);
			return true;
		}
		return false;
	}

	private boolean validateFields() {

		if ("".equals(nameField.getText().trim())) {
			MessageHelper.showErrorMessage("Please input member name.");
			return false;
		} else if ("".equals(cardNumberField.getText().trim())) {
			MessageHelper.showErrorMessage("Please input card number.");
			return false;
		}

		return true;
	}

	public ListMemberPanel getListPanel() {
		return listPanel;
	}
}
