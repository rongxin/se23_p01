package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ProductWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListProductPanel listPanel;
	private ListLowStockProductPanel listLowStockPanel;
	private AddProductDialog addDialog;

	public ProductWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Products  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JTabbedPane tabs = new JTabbedPane();
		listPanel = new ListProductPanel(shopApplication);
		listLowStockPanel = new ListLowStockProductPanel(shopApplication);

		tabs.addTab("All Products", listPanel);
		tabs.addTab("Low Stock Products", listLowStockPanel);

		p.add(tabs);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton addButton = new JButton("Add Product");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDialog = new AddProductDialog(shopApplication, listPanel);
				addDialog.pack();
				addDialog.setLocationByPlatform(true);
				addDialog.setVisible(true);
			}
		});

		p.add(addButton, BorderLayout.WEST);
		return p;
	}

	public AddProductDialog getAddDialog() {
		return addDialog;
	}

	public ListProductPanel getListPanel() {
		return listPanel;
	}

}
