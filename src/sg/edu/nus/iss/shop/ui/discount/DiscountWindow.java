package sg.edu.nus.iss.shop.ui.discount;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;


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
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Discounts  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 130, 0 };
		gbl_p.rowHeights = new int[] { 121, 0 };
		gbl_p.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		p.setLayout(gbl_p);
		
		GridBagConstraints gbc_lp = new GridBagConstraints();
		gbc_lp.fill = GridBagConstraints.BOTH;
		gbc_lp.anchor = GridBagConstraints.NORTHWEST;
		gbc_lp.gridx = 0;
		gbc_lp.gridy = 0;
		
		listPanel = new ListDiscountPanel(shopApplication);
		//p.add("Center", listPanel);
		p.add(listPanel,gbc_lp);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JButton addButton = new JButton("Add Discount", IconHelper.createImageIcon("add.png"));
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
