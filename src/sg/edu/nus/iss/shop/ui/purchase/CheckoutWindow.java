package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

public class CheckoutWindow extends JFrame {

	private static final String CARD_MEMBER = "memberCard";
	private static final String CARD_PAYMENT = "paymentCard";
	private static final String CARD_CART = "cartCard";
	private static final String CARD_SUMMARY = "summaryCard";

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JPanel purchaseCardPanel;

	private JPanel memberInfoPanel;

	private JLabel memberIdValuelabel;
	private JLabel memberNameValuelabel;
	private JLabel memberLoyaltyPointsValueLabel;
	private JLabel memberTypeValuelabel;

	private Customer customer;
	private List<Product> products = new ArrayList<>();
	private Double totalPrice;
	private Double totalDiscountPrice;
	private Double totalPayable;

	private ListPurchaseItemPanel listPurchaseItemPanel;
	private PurchaseInfoPanel purchaseInfoPanel;
	private ActionButtonsPanel actionButtonsPanel;
	private MakePaymentPanel makePaymentPanel;;

	public CheckoutWindow(ShopApplication shopApplication) {
		this.shopApplication = shopApplication;
		setLayout(new BorderLayout());
		this.add("North", createTitlePanel());
		this.add("Center", createPurchaseCardPanel());
		this.add("East", createRightPanel());
		this.add("South", createMessagePanel());

	}

	private JPanel createTitlePanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Make Purchase"));
		return p;
	}

	private JPanel createMessagePanel() {
		JPanel p = new JPanel();
		JLabel messageLabel = new JLabel();
		messageLabel.setText("Please scan items");
		p.add(messageLabel);
		return p;
	}

	private JPanel createRightPanel() {
		JPanel p = new JPanel(new GridLayout(3, 1));
		p.add(createMemerInfoPanel());

		purchaseInfoPanel = new PurchaseInfoPanel(shopApplication, this);
		purchaseInfoPanel.setVisible(false);
		p.add(purchaseInfoPanel);

		actionButtonsPanel = new ActionButtonsPanel(shopApplication, this);
		p.add(actionButtonsPanel);
		return p;
	}

	private JPanel createMemerInfoPanel() {

		memberInfoPanel = new JPanel(new GridLayout(0, 2));
		memberInfoPanel.setVisible(false);

		memberInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(" Member Information "), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JLabel memberTypeLabel = new JLabel("Member Type: ");
		memberInfoPanel.add(memberTypeLabel);

		memberTypeValuelabel = new JLabel("");
		memberInfoPanel.add(memberTypeValuelabel);

		JLabel memberIdLabel = new JLabel("Member ID: ");
		memberInfoPanel.add(memberIdLabel);

		memberIdValuelabel = new JLabel("");
		memberInfoPanel.add(memberIdValuelabel);

		JLabel memberNameLabel = new JLabel("Member Name: ");
		memberInfoPanel.add(memberNameLabel);

		memberNameValuelabel = new JLabel("");
		memberInfoPanel.add(memberNameValuelabel);

		JLabel loyaltypointsLabel = new JLabel("Loyalty Points: ");
		memberInfoPanel.add(loyaltypointsLabel);

		memberLoyaltyPointsValueLabel = new JLabel("0");
		memberInfoPanel.add(memberLoyaltyPointsValueLabel);

		return memberInfoPanel;
	}

	private JPanel createPurchaseCardPanel() {
		purchaseCardPanel = new JPanel();
		getPurchaseCardPanel().setLayout(new CardLayout());
		GetMemberInfoPanel getMemberInfoPanel = new GetMemberInfoPanel(this, shopApplication);

		getPurchaseCardPanel().add(getMemberInfoPanel, CARD_MEMBER);

		listPurchaseItemPanel = new ListPurchaseItemPanel();
		getPurchaseCardPanel().add(listPurchaseItemPanel, CARD_CART);

		makePaymentPanel = new MakePaymentPanel(shopApplication, this);
		getPurchaseCardPanel().add(makePaymentPanel, CARD_PAYMENT);
		getPurchaseCardPanel().add(createSummaryPanel(), CARD_SUMMARY);
		return getPurchaseCardPanel();
	}

	public void updatePurchaseInfo(Double totalPrice, Double discountPrice, Double totalPayable) {
		setTotalPrice(totalPrice);
		setTotalDiscountPrice(discountPrice);
		setTotalPayable(totalPayable);

		getPurchaseInfoPanel().getTotalAmountValueLabel().setText(PriceHelper.getPriceDisplay(totalPrice));
		getPurchaseInfoPanel().getDiscountValueLabel().setText(PriceHelper.getPriceDisplay(discountPrice));
		getPurchaseInfoPanel().getTotalPayableAmountValueLabel().setText(PriceHelper.getPriceDisplay(totalPayable));

		getMakePaymentPanel().getAmountToBePaidValue().setText(PriceHelper.getPriceDisplay(totalPayable));
	}

	public void updateMemberRelatedInfomation(Customer member) {
		customer = member;

		if (member instanceof Member) {

			memberTypeValuelabel.setText("Member");
			memberIdValuelabel.setText(member.getId());
			memberIdValuelabel.setToolTipText(member.getId());
			memberNameValuelabel.setText(((Member) member).getName());
			Integer displayLoyalPoints = 0;
			if (((Member) member).getLoyalPoints() > 0) {
				displayLoyalPoints = ((Member) member).getLoyalPoints();
				makePaymentPanel.getLoyatyPointsField().setText("" + displayLoyalPoints);
			} else {
				makePaymentPanel.getLoyatyPointsField().setEnabled(false);
			}
			memberLoyaltyPointsValueLabel.setText("" + displayLoyalPoints);

		} else {
			memberTypeValuelabel.setText("None Member");
			memberIdValuelabel.setText(member.getId());
			memberNameValuelabel.setText("N.A.");
			memberLoyaltyPointsValueLabel.setText("N.A.");
			makePaymentPanel.getLoyatyPointsField().setEnabled(false);
		}

		CardLayout cl = (CardLayout) (getPurchaseCardPanel().getLayout());
		cl.show(getPurchaseCardPanel(), CARD_CART);
		memberInfoPanel.setVisible(true);
		purchaseInfoPanel.setVisible(true);

		actionButtonsPanel.getScanItemsButton().setEnabled(true);
		actionButtonsPanel.getCheckoutButton().setEnabled(false);
		actionButtonsPanel.getProceedPaymentButton().setEnabled(true);

	}

	private Component createSummaryPanel() {
		JPanel p = new JPanel();
		p.add(new JLabel("Transaction Completed!"));
		return p;
	}

	public JPanel getPurchaseCardPanel() {
		return purchaseCardPanel;
	}

	public ListPurchaseItemPanel getListPurchaseItemPanel() {
		return listPurchaseItemPanel;
	}

	public List<Product> getProducts() {
		return products;
	}

	public PurchaseInfoPanel getPurchaseInfoPanel() {
		return purchaseInfoPanel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(Double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public Double getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(Double totalPayable) {
		this.totalPayable = totalPayable;
	}

	public MakePaymentPanel getMakePaymentPanel() {
		return makePaymentPanel;
	}

}
