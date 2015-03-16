package sg.edu.nus.iss.ui.member;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.category.ListCategoryPanel;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;

public class MemberWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;

	public MemberWindow(ShopApplication shopApplication) {
		super();
		this.shopApplication = shopApplication;
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Categories  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		ListCategoryPanel listPanel = new ListCategoryPanel(shopApplication);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		JButton addCategoryButton = new JButton("Register Member");
		addCategoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMemberDialog d = new AddMemberDialog(shopApplication);
				d.pack();
				d.setLocationByPlatform(true);
				d.setVisible(true);
			}
		});

		p.add(addCategoryButton, BorderLayout.WEST);
		return p;
	}

}
