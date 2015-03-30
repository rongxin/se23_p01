package sg.edu.nus.iss.shop.ui.product;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		text.setText("order");
		p.add(text);
		return p;
	}



}
