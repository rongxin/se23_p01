package sg.edu.nus.iss.shop.ui.product;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;

import java.awt.Color; 

public class ListProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ProductTableModel tableModel;

	public ListProductPanel(ShopApplication shopApplication) {
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.shopApplication = shopApplication;

		GridBagConstraints gbc_p = new GridBagConstraints();
		gbc_p.fill = GridBagConstraints.BOTH;
		gbc_p.anchor = GridBagConstraints.NORTHWEST;
		gbc_p.gridx = 0;
		gbc_p.gridy = 0;

		this.add(createMainPanel(), gbc_p);
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 800, 0 };
		gbl_p.rowHeights = new int[] { 450, 0 };
		gbl_p.columnWeights = new double[] { 0.5, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 0.5, Double.MIN_VALUE };
		p.setLayout(gbl_p);

		List<Product> products = shopApplication.getProducts();
		tableModel = new ProductTableModel();

		for (Product product : products) {
			JButton printButton = new JButton(
					IconHelper.createImageIcon("print.png"));
			printButton.addActionListener(new PrintProductLabelListener(
					shopApplication, this, product));
			tableModel.addToTable(product, printButton);
		}

		JTable table = new JTable(tableModel);
		table.setBorder(new LineBorder(Color.ORANGE));
		table.setEnabled(false);
		table.getColumn("Print").setCellRenderer(new JTableButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
		
		table.setName("Items");
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.anchor = GridBagConstraints.NORTHWEST;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		
		GridBagLayout gbl_table = new GridBagLayout();
		gbl_table.columnWidths = new int[] { 800, 0 };
		gbl_table.rowHeights = new int[] { 450, 0 };
		gbl_table.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_table.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_table.setConstraints(table, gbc_table);
		table.setLayout(gbl_table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(800, 450));

		GridBagConstraints gbc_scrollpane = new GridBagConstraints();
		gbc_scrollpane.fill = GridBagConstraints.BOTH;
		gbc_scrollpane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollpane.gridx = 0;
		gbc_scrollpane.gridy = 0;
		p.add(scrollPane, gbc_scrollpane);
		return p;
	}

	public ProductTableModel getTableModel() {
		return tableModel;
	}

}
