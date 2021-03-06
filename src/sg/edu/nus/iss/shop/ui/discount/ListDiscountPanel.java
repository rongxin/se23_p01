package sg.edu.nus.iss.shop.ui.discount;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

/**
 *
 * @author Xia Rongxin
 *
 */
public class ListDiscountPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private DiscountTableModel tableModel;

	public ListDiscountPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		createMainPanel();

	}

	private void createMainPanel() {
		List<Discount> discounts = shopApplication.getDiscounts();
		tableModel = new DiscountTableModel();

		for (Discount discount : discounts) {
			JButton editButton = new JButton(IconHelper.createImageIcon("edit.png"));
			editButton.addActionListener(new EditDiscountListener(shopApplication, this, discount));
			tableModel.addDiscountToTable(discount, editButton);
		}

		JTable table = new JTable(tableModel);
		table.getColumn("Edit").setCellRenderer(new JTableButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(720, 450));
		
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 800, 0 };
		gbl_p.rowHeights = new int[] { 450, 0 };
		gbl_p.columnWeights = new double[] { 0.5, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 0.5, Double.MIN_VALUE };
		this.setLayout(gbl_p);
		
		GridBagConstraints gbc_p = new GridBagConstraints();
		gbc_p.fill = GridBagConstraints.BOTH;
		gbc_p.anchor = GridBagConstraints.NORTHWEST;
		gbc_p.gridx = 0;
		gbc_p.gridy = 0;

		this.add(scrollPane, gbc_p);
		//this.add(scrollPane);
	}

	public DiscountTableModel getTableModel() {
		return tableModel;
	}



}
