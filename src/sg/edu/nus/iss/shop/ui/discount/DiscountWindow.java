package sg.edu.nus.iss.shop.ui.discount;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;


public class DiscountWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private AddDiscountDialog addDialog;
	private ListDiscountPanel listPanel;

	public DiscountWindow(ShopApplication shopApplication) {
		super("Discount");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("South", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Discounts  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		listPanel = new ListDiscountPanel(shopApplication);
		p.add("Center", listPanel);
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
				if (addDialog == null) {
					addDialog = new AddDiscountDialog(shopApplication, listPanel);
					addDialog.pack();
					addDialog.setLocationByPlatform(true);
				}
				addDialog.setVisible(true);
			}
		});
		p.add(addButton, BorderLayout.WEST);
		
		return p;
	}

}
