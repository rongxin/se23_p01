package sg.edu.nus.iss.shop.ui.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.ShopApplication;

public class ListProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public ListProductPanel(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.add(createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		Object columnNames[] = { "Product ID", "Name", "Description", "Quantity", "Price", "Barcode",
				"Reorder Threshold", "Order Quantity" };
		Object rowData[][] = { { "CLO/1", "Centenary Jumper", "A really nice momento", "315", "21.45", "1234", "10",
		"1000" } };

		List<Product> products = shopApplication.getProducts();
		System.out.println("Products: " + products.size());

		Object productData[][] = new Object[products.size()][2];

		int i = 0;
		for (Product product : products) {
			productData[i][0] = product.getProductId();
			productData[i][1] = product.getName();
			i++;
		}

		JTable table = new JTable(rowData, columnNames);
		table.setName("Items");
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		p.add(scrollPane);
		return p;
	}




}
