package sg.edu.nus.iss.shop.ui.category;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class CategoryWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListCategoryPanel listPanel;
	private AddCategoryDialog addDialog;

	public CategoryWindow(ShopApplication shopApplication) {
		super("Category");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Categories  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		listPanel = new ListCategoryPanel(shopApplication);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		JButton addCategoryButton = new JButton("Add Category");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDialog = new AddCategoryDialog(shopApplication, listPanel);
				addDialog.pack();
				addDialog.setLocationByPlatform(true);
				addDialog.setVisible(true);
			}
		});

		p.add(addCategoryButton, BorderLayout.WEST);
		return p;
	}

	public ListCategoryPanel getListPanel() {
		return listPanel;
	}

}
