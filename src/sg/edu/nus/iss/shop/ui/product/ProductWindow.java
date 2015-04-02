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
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class ProductWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListProductPanel listPanel;
	private ListLowStockProductPanel listLowStockPanel;
	private AddProductDialog addDialog;

	public ProductWindow(ShopApplication shopApplication) {
		super("Products");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
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

		int allProductsCount = listPanel.getTableModel().getRowCount();
		int lowStockNumber = listLowStockPanel.getTableModel().getRowCount();

		tabs.addTab("All Products (" + allProductsCount + ")", listPanel);
		tabs.addTab("Low Stock Products (" + lowStockNumber + ")", listLowStockPanel);

		p.add(tabs);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton addButton = new JButton("Add Product", IconHelper.createImageIcon("add.png"));
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
