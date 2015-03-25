package sg.edu.nus.iss.shop.ui.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.iss.shop.controller.CategoryManager;
import sg.edu.nus.iss.shop.controller.MemberManager;
import sg.edu.nus.iss.shop.controller.ProductManager;
import sg.edu.nus.iss.shop.controller.ReportManager;
import sg.edu.nus.iss.shop.controller.TransactionManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ShopApplication {
	private ShopMainWindow shopWindow;
	private LoginDialog loginDialog;
	private CategoryManager categoryManager;
	private ProductManager productManager;
	private MemberManager memberManager;
	private TransactionManager transactionManager;

	public ShopApplication() {
		loginDialog = new LoginDialog(this);
		loginDialog.pack();
		loginDialog.setLocationByPlatform(true);
		loginDialog.setVisible(true);

		categoryManager = CategoryManager.getCategoryManager();
		productManager = ProductManager.getProductManager();
		memberManager = MemberManager.getMemberManager();
		transactionManager = TransactionManager.getInstance();
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		shop.start();
	}

	public List<Category> getCategories() {
		List<Category> allCategories = new ArrayList<>();
		try {
			allCategories = categoryManager.getAllCategories();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}


		System.out.println("Get categories, size:" + allCategories.size());
		return allCategories;
	}

	public void addCategory(String categoryCode, String categoryName) {
		System.out.println("Add Category: " + categoryCode + ",  " + categoryName);
		try {
			categoryManager.createCategory(categoryCode, categoryName);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		try {
			products = productManager.getAllProducts();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
		return products;
	}

	public void addProduct(String categoryCode, String name, String description, Integer availableQuantity,
			Double price, String barcodeNumber, Integer orderThreshold, Integer orderQuantity) {

		System.out.println("Add Product ");
		try {
			Category category = categoryManager.getCategory(categoryCode);
			productManager.addProduct(category, name, description, availableQuantity, price.doubleValue(),
					barcodeNumber, orderThreshold, orderQuantity);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}

	public Product getProductByBarcode(String scannedId) {
		System.out.println("Get product by barcode:" + scannedId);
		Product product = null;
		try {
			product = productManager.getProductByBarcode(scannedId);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void addMember(String memberId, String memberName){
		System.out.println("Add Member, memberId:" + memberId + ", memberName:" + memberName);

		try {
			memberManager.addMember(memberId, memberName);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}

	public List<Member> getMembers() {
		List<Member> members = new ArrayList<>();
		try {
			members = memberManager.getAllMembers();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
		return members;
	}

	public Member getMember(String memberId) {
		Member member = null;
		try {
			member = memberManager.getMemberById(memberId);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
		return member;
	}

	public List<String[]> getCategoryReport(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getCategoryreport();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getCategoryReportHeader(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getCategoryReportHeader();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getProductReportHeader(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getProductReportHeader();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String[]> getProductReport(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getProductReport();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getTransactionReportHeader(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getTransactionReportHeader();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String[]> getTransactionReport(String startDate, String endDate){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getTransactionReport(startDate, endDate);
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getMemberReportHeader(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getMemberReportHeader();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String[]> getMemberReport(){
		ReportManager rm = ReportManager.getReportManager();
		try {
			return rm.getMemberReport();
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double calculateCashToPay(Integer loyalPoints, Double totalPayable) {
		double cashToPay = transactionManager.calculateCashToPay(loyalPoints, totalPayable);
		return cashToPay;
	}

}
