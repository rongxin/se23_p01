package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.ui.JTableButtonMouseListener;
import sg.edu.nus.iss.shop.ui.JTableButtonRenderer;

public class ListPurchaseItemPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ListPurchaseItemPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Items"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		table = new JTable(new ItemTableModel());
		table.getColumn("Edit").setCellRenderer(new JTableButtonRenderer());
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(550, 450));
		this.add(scrollPane);
	}

	public JTable getTable() {
		return table;
	}


}
