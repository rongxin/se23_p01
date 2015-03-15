package sg.edu.nus.iss.shop.ui.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.ShopApplication;
import sg.edu.nus.iss.shop.ui.dialog.AddProductDialog;
import sg.edu.nus.iss.shop.ui.panel.ListProductPanel;

public class ProductWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListProductPanel listPanel;

	public ProductWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Products  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		listPanel = new ListProductPanel(shopApplication);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton addCategoryButton = new JButton("Add Product");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProductDialog d = new AddProductDialog(shopApplication, listPanel);

				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});

		p.add(addCategoryButton, BorderLayout.WEST);
		return p;
	}

}
