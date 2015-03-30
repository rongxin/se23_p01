package sg.edu.nus.iss.shop.ui.member;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;

public class MemberWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private ListMemberPanel listPanel;
	private AddMemberDialog addDialog;

	public MemberWindow(ShopApplication shopApplication) {
		super("Members");
		setIconImage(IconHelper.createImageIcon("shop.png").getImage());
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createMainPanel());
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Members  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		listPanel = new ListMemberPanel(shopApplication);
		p.add(listPanel);
		return p;
	}


	private JPanel createTitlePanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("  Actions  "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		JButton addButton = new JButton("Register Member");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDialog = new AddMemberDialog(shopApplication, listPanel);
				addDialog.pack();
				addDialog.setLocationByPlatform(true);
				addDialog.setVisible(true);
			}
		});

		p.add(addButton, BorderLayout.WEST);
		return p;
	}

	public ListMemberPanel getListPanel() {
		return listPanel;
	}

	public AddMemberDialog getAddDialog() {
		return addDialog;
	}

}
