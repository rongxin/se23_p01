package sg.edu.nus.iss.shop.ui.discount;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class ListDiscountPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public ListDiscountPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		List<Discount> discounts = shopApplication.getDiscounts();
		Object discountData[][] = new Object[discounts.size()][2];

		int i = 0;
		for (Discount discount : discounts) {
			discountData[i][0] = discount.getDiscountCode();
			discountData[i][1] = discount.getDescription();
			i++;
		}

		Object columnNames[] = { "Code", "Description" };
		JTable table = new JTable(discountData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}




}
