package sg.edu.nus.iss.shop.ui.product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

/**
 *
 * @author Xia Rongxin
 *
 */
public class ListLowStockProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private LowStockProductTableModel tableModel;

	public ListLowStockProductPanel(ShopApplication shopApplication) {
		super();
		setBorder(null);
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
		p.setBorder(null);

		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 800, 0 };
		gbl_p.rowHeights = new int[] { 450, 0 };
		gbl_p.columnWeights = new double[] { 0.5, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 0.5, Double.MIN_VALUE };
		p.setLayout(gbl_p);
		
		List<Product> products = shopApplication.getLowStockProducts();
		tableModel = new LowStockProductTableModel();

		for (Product product : products) {
			JButton orderButton = new JButton(IconHelper.createImageIcon("order_product.png"));
			orderButton.setToolTipText("Order product");

			orderButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					GenerateProductOrderDialog generateOrderDialog = new GenerateProductOrderDialog(
							shopApplication, product);
					generateOrderDialog.pack();
					generateOrderDialog.setLocationByPlatform(true);
					generateOrderDialog.setVisible(true);

				}
			});
			tableModel.addToTable(product, orderButton);
		}
		
		JTable table = new JTable(tableModel);
		table.setBorder(new LineBorder(Color.ORANGE));
		table.getColumn("Order").setCellRenderer(new JTableButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));

		table.setName("Items");
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);

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

	public LowStockProductTableModel getTableModel() {
		return tableModel;
	}

}
