package sg.edu.nus.iss.shop.ui.purchase;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.ui.main.ShopApplication;
import sg.edu.nus.iss.shop.ui.util.IconHelper;
import sg.edu.nus.iss.shop.ui.util.PriceHelper;

public class CheckoutWindow extends JFrame {

	private static final String CARD_MEMBER = "memberCard";
	private static final String CARD_PAYMENT = "paymentCard";
	private static final String CARD_CART = "cartCard";
	private static final String CARD_SUMMARY = "summaryCard";

	private static final long serialVersionUID = 1L;
	private ShopApplication shopApplication;
	private JPanel purchaseCardPanel;

	private Customer customer;
	private List<Product> products = new ArrayList<>();
	private Double totalPrice;
	private Double totalDiscountedPrice;
	private Double totalPayable;
	private Double cashReceived;
	private Double changesGiven;
	private Integer loyalPointsUsed = 0;
	private Integer discount = 0;


	private ListPurchaseItemPanel listPurchaseItemPanel;
	private CustomerInfoPanel memberInfoPanel;
	private PurchaseInfoPanel purchaseInfoPanel;
	private ActionButtonsPanel actionButtonsPanel;
	private MakePaymentPanel makePaymentPanel;
	private PurchaseSummaryPanel purchaseSummaryPanel;

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
		messageLabel.setText("");
		p.add(messageLabel);
		return p;
	}

	private JPanel createRightPanel() {
		JPanel p = new JPanel(new GridLayout(3, 1));
		memberInfoPanel = new CustomerInfoPanel(shopApplication, this);
		memberInfoPanel.setVisible(false);
		p.add(memberInfoPanel);

		purchaseInfoPanel = new PurchaseInfoPanel(shopApplication, this);
		purchaseInfoPanel.setVisible(false);
		p.add(purchaseInfoPanel);

		actionButtonsPanel = new ActionButtonsPanel(shopApplication, this);
		p.add(actionButtonsPanel);
		return p;
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

		purchaseSummaryPanel = new PurchaseSummaryPanel(this, shopApplication);

		getPurchaseCardPanel().add(purchaseSummaryPanel, CARD_SUMMARY);
		return getPurchaseCardPanel();
	}

	public void updatePurchaseInfo(Double totalPrice, Double discountPrice, Double totalPayable) {
		setTotalPrice(totalPrice);
		setTotalDiscountedPrice(discountPrice);
		setTotalPayable(totalPayable);

		getPurchaseInfoPanel().getTotalAmountValueLabel().setText(PriceHelper.getPriceDisplay(totalPrice));
		getPurchaseInfoPanel().getDiscountValueLabel().setText(PriceHelper.getPriceDisplay(discountPrice));
		getPurchaseInfoPanel().getTotalAfterDiscountValueLabel().setText(PriceHelper.getPriceDisplay(totalPayable));
		getPurchaseInfoPanel().getCashToPayValueLabel().setText(PriceHelper.getPriceDisplay(totalPayable));

		getMakePaymentPanel().getAmountToBePaidValue().setText(PriceHelper.getPriceDisplay(totalPayable));

	}

	public void updateMemberRelatedInfomation(Customer member) {
		customer = member;

		if (member instanceof Member) {

			memberInfoPanel.getMemberTypeValuelabel().setIcon(IconHelper.createImageIcon("customer_member.png"));
			memberInfoPanel.getMemberTypeValuelabel().setToolTipText("Member");
			memberInfoPanel.getMemberIdValuelabel().setText(member.getId());
			memberInfoPanel.getMemberNameValuelabel().setText(((Member) member).getName());
			Integer displayLoyalPoints = 0;
			if (((Member) member).getLoyalPoints() > 0) {
				displayLoyalPoints = ((Member) member).getLoyalPoints();
				makePaymentPanel.getLoyatyPointsField().setText("" + displayLoyalPoints);
			} else {
				makePaymentPanel.getLoyatyPointsField().setEnabled(false);
			}
			memberInfoPanel.getMemberLoyaltyPointsValueLabel().setText("" + displayLoyalPoints);

		} else {
			memberInfoPanel.getMemberTypeValuelabel().setIcon(IconHelper.createImageIcon("customer_public.png"));
			memberInfoPanel.getMemberTypeValuelabel().setToolTipText("Non Member");
			memberInfoPanel.getMemberIdValuelabel().setText(member.getId());
			memberInfoPanel.getMemberNameValuelabel().setText("N.A.");
			memberInfoPanel.getMemberLoyaltyPointsValueLabel().setText("N.A.");
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

	public Double getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}

	public void setTotalDiscountedPrice(Double totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
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

	public Integer getLoyalPointsUsed() {
		return loyalPointsUsed;
	}

	public void setLoyalPointsUsed(Integer loyalPointsUsed) {
		this.loyalPointsUsed = loyalPointsUsed;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public PurchaseSummaryPanel getPurchaseSummaryPanel() {
		return purchaseSummaryPanel;
	}

	public Double getCashReceived() {
		return cashReceived;
	}

	public void setCashReceived(Double cashReceived) {
		this.cashReceived = cashReceived;
	}

	public CustomerInfoPanel getMemberInfoPanel() {
		return memberInfoPanel;
	}

	public Double getChangesGiven() {
		return changesGiven;
	}

	public void setChangesGiven(Double changesGiven) {
		this.changesGiven = changesGiven;
	}

}
