package sg.edu.nus.iss.shop.ui.discount;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class DiscountWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public DiscountWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Discounts  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		ListDiscountPanel listPanel = new ListDiscountPanel(shopApplication);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		JButton addButton = new JButton("Add Discount");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddDiscountDialog d = new AddDiscountDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});

		p.add(addButton, BorderLayout.WEST);
		return p;
	}

}
