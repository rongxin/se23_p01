package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

public class OrderProductWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private Product product;
	private String vendor;
	private Integer orderQuantity;

	public OrderProductWindow(Product product, String vendor, Integer orderQuantity) {
		super("Order Products");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
		setLayout(new BorderLayout());
		this.product = product;
		this.vendor = vendor;
		this.orderQuantity = orderQuantity;
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JTextPane text = new JTextPane();
		text.setContentType("text/html");
		text.setText(getOrderContent());

		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(400, 300));

		p.add(scrollPane);
		return p;
	}

	private String getOrderContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		sb.append("<center><h2> Product Order Form</h2></center>");
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Vendor: </b>    ");
		sb.append(vendor);
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Product ID: </b>    ");
		sb.append(product.getProductId());
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Product Name: </b>    ");
		sb.append(product.getName());
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Product Price ($): </b>    ");
		sb.append(PriceHelper.getPriceDisplay(product.getPrice()));
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Order Quantity: </b>    ");
		sb.append(orderQuantity);
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Total  Price ($): </b>    ");
		sb.append(PriceHelper.getPriceDisplay(orderQuantity * product.getPrice()));
		sb.append("<br>");

		sb.append("</body></html>");
		return sb.toString();
	}



}
