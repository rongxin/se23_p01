package sg.edu.nus.iss.shop.ui.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import sg.edu.nus.iss.shop.ui.util.LayoutHelper;

public class AddCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;


	public AddCategoryPanel() {
		super();

		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(createInputFormPanel(), BorderLayout.CENTER);
		this.add(createActionButtonsPanel(), BorderLayout.SOUTH);
		UIManager.put("title.font", new Font("Arial", Font.BOLD, 14));
	}

	private JPanel createActionButtonsPanel() {
		JPanel p = new JPanel();
		p.add(new JButton("Ok"));
		p.add(new JButton("Cancel"));
		return p;
	}

	private JPanel createInputFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Add Category "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gc = new GridBagConstraints();

		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		JLabel categoryCodeLabel = new JLabel("Category Code: ");
		p.add(categoryCodeLabel, gc);

		gc = LayoutHelper.createCellConstraint(0, 1);
		JLabel categoryNameLabel = new JLabel("Category Name: ");
		p.add(categoryNameLabel, gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.fill = GridBagConstraints.NONE;
		categoryCodeField = new JTextField(3);
		categoryCodeField.setToolTipText("Please input three-letter code for the new category");
		p.add(categoryCodeField, gc);

		gc = LayoutHelper.createCellConstraint(1, 1);
		categoryNameField = new JTextField(20);
		categoryNameField.setToolTipText("Please input name for the category");
		p.add(categoryNameField, gc);

		return p;
	}

}
