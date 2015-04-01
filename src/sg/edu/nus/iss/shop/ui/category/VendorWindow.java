package sg.edu.nus.iss.shop.ui.category;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class VendorWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListVendorPanel listPanel;
	private AddVendorDialog addDialog;
	private Category category;

	public VendorWindow(ShopApplication shopApplication, Category category) {
		super();
		this.shopApplication = shopApplication;
		this.category = category;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Vendors  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		listPanel = new ListVendorPanel(shopApplication, category);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		JButton addCategoryButton = new JButton("Add Vendor");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (addDialog == null) {
					addDialog = new AddVendorDialog(shopApplication, listPanel, category);
					addDialog.pack();
					addDialog.setLocationByPlatform(true);
				}
				addDialog.setVisible(true);
			}
		});

		p.add(addCategoryButton, BorderLayout.WEST);
		return p;
	}

	public ListVendorPanel getListPanel() {
		return listPanel;
	}

}
