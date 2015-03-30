package sg.edu.nus.iss.shop.ui.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.controller.MemberManager;
import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.controller.ReportManager;
import sg.edu.nus.iss.shop.controller.TransactionManager;
import sg.edu.nus.iss.shop.controller.VendorManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Customer;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.ui.util.MessageHelper;
import sg.edu.nus.iss.shop.ui.util.ProductItemsHelper;

public class ShopApplication {
	private ShopMainWindow shopWindow;
	private LoginDialog loginDialog;
	private CategoryManager categoryManager;
	private ProductManager productManager;
	private MemberManager memberManager;
	private TransactionManager transactionManager;
	private DiscountManager discountManager;
	private VendorManager vendorManager;
	private ReportManager reportManager;

	public ShopApplication() {
		loginDialog = new LoginDialog(this);
		loginDialog.pack();
		loginDialog.setLocationByPlatform(true);
		loginDialog.setVisible(true);

		initManagers();
	}

	private void initManagers() {
		categoryManager = CategoryManager.getCategoryManager();
		vendorManager = VendorManager.getVendorManager();
		productManager = ProductManager.getProductManager();
		memberManager = MemberManager.getMemberManager();
		transactionManager = TransactionManager.getInstance();
		discountManager = DiscountManager.getDiscountManager();
		reportManager = ReportManager.getReportManager();
	}

	public void start() {
		loginDialog.setVisible(true);
	}

	public void login(String userName, String password) {
		// TODO login validation here

		// success to show the main application window
		loginDialog.setVisible(false);
		shopWindow = new ShopMainWindow(this);
		shopWindow.pack();
		shopWindow.setLocationRelativeTo(null);
		shopWindow.setVisible(true);
	}

	public void shutdown() {
		System.exit(0);
	}

	public ShopMainWindow getMainWindow() {
		return shopWindow;
	}

	public static void main(String[] args) {
		ShopApplication shop = new ShopApplication();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			MessageHelper.showErrorMessage(shop.getShopWindow(), e.getMessage());
		}
		shop.start();
	}

	public List<Category> getCategories() {
		List<Category> allCategories = new ArrayList<>();
		try {
			allCategories = categoryManager.getAllCategories();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}

		return allCategories;
	}

	public Category addCategory(String categoryCode, String categoryName) {
		try {
			return categoryManager.createCategory(categoryCode, categoryName);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(e.getMessage());
		}
		return null;
	}

	public Vendor addVendor(Category category, String vendorName,
			String vendorDescription) {
		Vendor vendor = null;
		try {
			vendor = vendorManager.addVendor(vendorName, vendorDescription,
					Arrays.asList(category));
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return vendor;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		try {
			products = productManager.getAllProducts();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return products;
	}

	public List<Product> getLowStockProducts() {
		List<Product> products = new ArrayList<>();
		try {
			products = productManager.getProductsWithLowInventory();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}

		return products;
	}

	public List<Product> getLowStockProducts(List<Product> checkProducts) {
		List<Product> products = new ArrayList<>();
		try {
			products = productManager
					.getProductsWithLowInventory(checkProducts);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}

		return products;
	}

	public Product addProduct(String categoryCode, String name,
			String description, Integer availableQuantity, Double price,
			String barcodeNumber, Integer orderThreshold, Integer orderQuantity) {

		try {
			Category category = categoryManager.getCategory(categoryCode);
			return productManager.addProduct(category, name, description,
					availableQuantity, price.doubleValue(), barcodeNumber,
					orderThreshold, orderQuantity);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(e.getMessage());
		}
		return null;
	}

	public Product getProductByBarcode(String scannedId) {
		Product product = null;
		try {
			product = productManager.getProductByBarcode(scannedId);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return product;
	}

	public Member addMember(String memberId, String memberName) {

		try {
			return memberManager.addMember(memberId, memberName);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(e.getMessage());
		}
		return null;
	}

	public Member addDiscount(String discountCode, String description, String percent,String startDate,String period,String appliableTo) {

		/*try {
			//return discountManager.add


		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}*/
		return null;
	}

	public List<Member> getMembers() {
		List<Member> members = new ArrayList<>();
		try {
			members = memberManager.getAllMembers();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return members;
	}

	public Member getMember(String memberId) {
		Member member = null;
		try {
			member = memberManager.getMemberById(memberId);
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return member;
	}

	public List<String[]> getCategoryReport() {
		try {
			return reportManager.getCategoryreport();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public String[] getCategoryReportHeader() {
		try {
			return reportManager.getCategoryReportHeader();
		} catch (Exception e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public String[] getProductReportHeader() {
		try {
			return reportManager.getProductReportHeader();
		} catch (Exception e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public List<String[]> getProductReport() {
		try {
			return reportManager.getProductReport();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public String[] getTransactionReportHeader() {
		try {
			return reportManager.getTransactionReportHeader();
		} catch (Exception e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public List<String[]> getTransactionReport(String startDate, String endDate) throws ParseException, ApplicationGUIException {
		ReportManager rm = ReportManager.getReportManager();
		return rm.getTransactionReport(startDate, endDate);
	}

	public String[] getMemberReportHeader() {
		try {
			return reportManager.getMemberReportHeader();
		} catch (Exception e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public List<String[]> getMemberReport() {
		try {
			return reportManager.getMemberReport();
		} catch (ApplicationGUIException e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
			return null;
		}
	}

	public Double calculateCashToPay(Integer loyalPoints, Double totalPayable) {
		double cashToPay = transactionManager.calculateCashToPay(loyalPoints,
				totalPayable);
		return cashToPay;
	}

	public Transaction checkout(List<Product> products, Customer customer,
			Integer loyalPointsUsed, Double discountedPrice, Double paidAmount) {

		Transaction transactionResult = null;
		Hashtable<Product, Integer> productsWithCount = ProductItemsHelper
				.convertProductListToHashTable(products);

		try {
			transactionResult = transactionManager.endTransaction(customer,
					productsWithCount, discountedPrice, loyalPointsUsed,
					paidAmount);
		} catch (Exception e) {
			MessageHelper.showErrorMessage(getShopWindow(), e.getMessage());
		}
		return transactionResult;
	}

	public List<Discount> getDiscounts() {
		List<Discount> allDiscounts = discountManager.getAllDiscounts();
		return allDiscounts;
	}

	public ShopMainWindow getShopWindow() {
		return shopWindow;
	}

}
