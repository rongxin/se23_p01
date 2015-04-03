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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add("North", createTitlePanel());
		getContentPane().add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Products  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 130, 0 };
		gbl_p.rowHeights = new int[] { 121, 0 };
		gbl_p.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		p.setLayout(gbl_p);

		JTabbedPane tabs = new JTabbedPane();
		listPanel = new ListProductPanel(shopApplication);
		listPanel.setBorder(null);
		listLowStockPanel = new ListLowStockProductPanel(shopApplication);

		int allProductsCount = listPanel.getTableModel().getRowCount();
		int lowStockNumber = listLowStockPanel.getTableModel().getRowCount();

		GridBagConstraints gbc_tabs = new GridBagConstraints();
		gbc_tabs.fill = GridBagConstraints.BOTH;
		gbc_tabs.anchor = GridBagConstraints.NORTHWEST;
		gbc_tabs.gridx = 0;
		gbc_tabs.gridy = 0;

		GridBagLayout gbl_a = new GridBagLayout();
		gbl_a.columnWidths = new int[] { 130, 0 };
		gbl_a.rowHeights = new int[] { 121, 0 };
		gbl_a.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_a.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		listPanel.setLayout(gbl_a);

		GridBagLayout gbl_l = new GridBagLayout();
		gbl_l.columnWidths = new int[] { 130, 0 };
		gbl_l.rowHeights = new int[] { 121, 0 };
		gbl_l.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_l.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		listLowStockPanel.setLayout(gbl_l);

		tabs.addTab("All Products (" + allProductsCount + ")", listPanel);
		tabs.addTab("Low Stock Products (" + lowStockNumber + ")",
				listLowStockPanel);

		p.add(tabs, gbc_tabs);
		return p;
	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton addButton = new JButton("Add Product",
				IconHelper.createImageIcon("add.png"));
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
