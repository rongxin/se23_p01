package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.NonMemberCustomer;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.LayoutHelper;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;

public class GetMemberInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CheckoutWindow checkoutWindow;
	private ShopApplication shopApplication;
	private BarcodeScannerEmulatorDialog memberScanner;

	public GetMemberInfoPanel(CheckoutWindow checkoutWindow, ShopApplication shopApplication) {
		this.checkoutWindow = checkoutWindow;
		this.shopApplication = shopApplication;
		this.add(createGetMemberPanel());
		setVisible(true);
	}

	private Component createGetMemberPanel() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" Member Info "),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		GridBagConstraints gc = new GridBagConstraints();
		// column 1
		gc = LayoutHelper.createCellConstraint(0, 0);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 1);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 2);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(0, 3);
		p.add(new JLabel(""), gc);

		// column 2
		gc = LayoutHelper.createCellConstraint(1, 0);
		p.add(new JLabel(""), gc);
		gc = LayoutHelper.createCellConstraint(1, 1);
		JButton scanMemberCardButton = new JButton("Scan Member Card", IconHelper.createImageIcon("member_card.png"));
		scanMemberCardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (memberScanner == null) {
					memberScanner = new BarcodeScannerEmulatorDialog(p.getParent());
					memberScanner.pack();
					memberScanner.setLocationByPlatform(true);

					memberScanner.addConfirmListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String memberId = memberScanner.getScannedBarcodeNumber();
							Member member = shopApplication.getMember(memberId);
							if (member != null) {
								checkoutWindow.updateMemberRelatedInfomation(member);
								memberScanner.setVisible(false);
								memberScanner.dispose();
							} else {
								MessageHelper.showErrorMessage(scanMemberCardButton,
										"Unable to find member information for card number:"
												+ memberId);
							}
						}
					});
				}
				memberScanner.setVisible(true);
			}
		});
		p.add(scanMemberCardButton, gc);

		gc = LayoutHelper.createCellConstraint(1, 2);
		JButton publicMemberButton = new JButton("Not a Member", IconHelper.createImageIcon("non_member.png"));
		publicMemberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Customer customer = new NonMemberCustomer();
				checkoutWindow.updateMemberRelatedInfomation(customer);
				if (memberScanner != null) {
					memberScanner.dispose();
				}
			}
		});
		p.add(publicMemberButton, gc);
		gc = LayoutHelper.createCellConstraint(1, 3);

		// column 4
		gc = LayoutHelper.createCellConstraint(2, 0);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 1);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 2);
		p.add(new JLabel(""));
		gc = LayoutHelper.createCellConstraint(2, 3);
		p.add(new JLabel(""));
		return p;
	}

	public BarcodeScannerEmulatorDialog getMemberScanner() {
		return memberScanner;
	}
}
