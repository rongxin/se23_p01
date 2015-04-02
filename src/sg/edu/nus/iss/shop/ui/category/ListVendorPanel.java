package sg.edu.nus.iss.shop.ui.category;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

/**
 * @author Xia Rongxin
 */
public class ListVendorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private VendorTableModel tableModel;
	private Category category;

	public ListVendorPanel(ShopApplication shopApplication, Category category) {
		super();
		this.shopApplication = shopApplication;
		this.category = category;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();

		List<Vendor> vendors = category.getVendorList();

		tableModel = new VendorTableModel();
		for (Vendor vendor : vendors) {
			JButton vendorButton = new JButton("Vendors");
			vendorButton.addActionListener(new ViewCategoryVendorListener(shopApplication, category));
			tableModel.addToTable(vendor);
		}

		JTable table = new JTable(tableModel);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}

	public VendorTableModel getTableModel() {
		return tableModel;
	}


}
