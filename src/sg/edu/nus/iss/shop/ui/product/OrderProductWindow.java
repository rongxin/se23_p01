package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class OrderProductWindow extends JFrame {
	private static final long serialVersionUID = 1L;


	public OrderProductWindow() {
		super("Order Products");
		setLayout(new BorderLayout());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Order Product  "),
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
		sb.append("<center><h1> Product Order Form</h1></center>");
		sb.append("<br><hr>");

		sb.append("<b style=\"color:black\">Product ID: </b>    ");
		sb.append("CLO/1");
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Product Name: </b>    ");
		sb.append("Product Name");
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Vendor: </b>    ");
		sb.append("Vendor Name");
		sb.append("<br>");

		sb.append("<b style=\"color:black\">Order Quantity: </b>    ");
		sb.append("100");
		sb.append("<br>");

		sb.append("</body></html>");
		return sb.toString();
	}



}
