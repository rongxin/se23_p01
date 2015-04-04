package sg.edu.nus.iss.shop.dao;


import java.io.IOException;
import java.util.Date;

import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.util.Logger;
 

public class InitApplicationRepository 
{
	
	private PersistentService service= PersistentService.getService();
	
	private void InitCategoryAndVendor()
	{
		Category category = new Category("CLO","Clothing");
		Category category1 = new Category("MUG","Mugs");
		Category category2 = new Category("STA","Stationary");
		Category category3 = new Category("DIA","Diary");
		Category category4 = new Category("SNA","Snack");
		
		try {
			if(service.retrieveObject(Category.class, category.getName()) == null)
			{
				service.saveRecord(category);
				
				
				service.saveRecord(category1);
				
				
				service.saveRecord(category2);
				
				
				service.saveRecord(category3);
				
				
				service.saveRecord(category4);
				
				Category category5 = new Category("CLA","Calendar");
				service.saveRecord(category5);
				
				Category category6 = new Category("FRU","Fruit");
				service.saveRecord(category6);
				
				Category category7 = new Category("BEV","Beverage");
				service.saveRecord(category7);
			}
		
		Vendor vendor = new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's");
		service.saveVendor(vendor, category);

		Vendor vendor1 = new Vendor("Office Sovenirs", "One and Only Office Sovenirs");
		service.saveVendor(vendor1, category);

		Vendor vendor2 = new Vendor("Pen's and Such", "Sovenirs and gifts for all occasions");
		service.saveVendor(vendor2, category);

		Vendor vendor3 = new Vendor("ArtWorks Stationary Store", "All Kinds of Sttionary and Gifts");
		service.saveVendor(vendor3, category);	
		 
		service.saveVendor(vendor, category1);
		service.saveVendor(vendor1, category1);
		service.saveVendor(vendor2, category1);
		service.saveVendor(vendor3, category1);
		
		service.saveVendor(vendor, category2);
		service.saveVendor(vendor1, category2);
		service.saveVendor(vendor2, category2);
		service.saveVendor(vendor3, category3);
		
		service.saveVendor(vendor, category3);
		service.saveVendor(vendor1, category3);
		service.saveVendor(vendor2, category3);
		service.saveVendor(vendor3, category3);
	
		service.saveVendor(vendor, category4);
		service.saveVendor(vendor1, category4);
		service.saveVendor(vendor2, category4);
		service.saveVendor(vendor3, category4);
		} catch (InvalidDomainObject e) {
			Logger.getLog().log("InitCategoryAndVendor:" +e.getMessage());
		} catch (InvalidDataFormat e) {
			Logger.getLog().log("InitCategoryAndVendor:" +e.getMessage());
		} catch (IOException e) {
			Logger.getLog().log("InitCategoryAndVendor:" +e.getMessage());
		} catch (Exception e) {
			Logger.getLog().log("InitCategoryAndVendor:" +e.getMessage());
		}
	}
	
	private void InitStoreKeeper()
	{
		try {
			StoreKeeper keeper = new StoreKeeper("Stacy","Stacy");
			if(service.retrieveObject(StoreKeeper.class, keeper.getName()) == null)
			{
				service.saveRecord(keeper);
				
				StoreKeeper keeper1 = new StoreKeeper("Johny","Johny");			
				service.saveRecord(keeper1);	
				
				StoreKeeper keeper2 = new StoreKeeper("A0135930J","A0135930J");			
				service.saveRecord(keeper2);
				
				StoreKeeper keeper3 = new StoreKeeper("A0135945X","A0135945X");			
				service.saveRecord(keeper3);
				
				StoreKeeper keeper4 = new StoreKeeper("A0135858R","A0135858R");			
				service.saveRecord(keeper4);
				
				StoreKeeper keeper5 = new StoreKeeper("A0135925B","A0135925B");			
				service.saveRecord(keeper5);
				
				StoreKeeper keeper6 = new StoreKeeper("A0135931H","A0135931H");			
				service.saveRecord(keeper6);
				
				StoreKeeper keeper7 = new StoreKeeper("A0135872Y","A0135872Y");			
				service.saveRecord(keeper7);
				
				StoreKeeper keeper8 = new StoreKeeper("A0135867R","A0135867R");			
				service.saveRecord(keeper8);
				
				StoreKeeper keeper9 = new StoreKeeper("A0006524R","A0006524R");			
				service.saveRecord(keeper9);
			} 
			
		} catch (Exception e) {
			Logger.getLog().log("InitStoreKeeper:" +e.getMessage());
		}
	}
	
	private void initProductAndTransaction()
	{
		try {
			
			Product prod = new Product("CLO/1", "Centenary Jumper",
					"A releally nice momento", 315, 21.45, "1234", 10, 100);
			
			Product prod02 = new Product("CLO/2", "Men Cotton Knit Polo Tee",
					"Comfy men cotton knit button front with NUS logo embroidered", 500, 17.91, "857473648", 50, 200);
			
			Product prod1 = new Product("MUG/1", "Centenary Mug",
					"A releally nice mug this time", 525, 10.25, "9876", 25,
					150);
			
			Product prod12 = new Product("MUG/2", "Fine Bone Straight Mug With Lion Print (16oz)",
					"Start your day with this bone straight with lion print mug", 200, 17.01, "759384669", 25,
					50);
			
			Product prod2 = new Product("STA/1", "NUS Pen",
					"A releally cute blue pen", 768, 5.75, "123459876", 50, 250);
						
			
			Product prod22 = new Product("STA/3", "A4 Port Folio with 20 pockets",
					"This A4 portfolio comes with 20 pockets - ideal to hold your interesting and inspiring design work", 1000, 9.90, "38647465001", 100, 500);
			
			Product prod3 = new Product("STA/4", "Laser Pointer",
					"Pen size - Comes with an elegant clear casing for safe-keeping", 100, 13.90, "26985746002", 20, 100);
			
			Product prod32 = new Product("STA/2", "NUS Notepad2",
					"Great notepad for those lectures", 1000, 3.15, "6789", 25, 75);
			
			if(service.retrieveObject(Product.class, prod.getName()) == null)
			{
				service.saveRecord(prod);
				
				
				service.saveRecord(prod02);
				
				
				service.saveRecord(prod1);				
				
				service.saveRecord(prod12);				
				
				
				service.saveRecord(prod2);	
				
				service.saveRecord(prod22);
				
				
				
				service.saveRecord(prod3);				
				
				service.saveRecord(prod32);
			
			}
			
			
			
			Transaction trans = new Transaction(1, new Member("F42563743156", "Yan Martel"), new Date());		
			trans.changeProductQuantity(prod, 1); 
			service.saveRecord(trans); 
	
			Transaction trans1 = new Transaction(2, new Member("X4242237431326", "Suraj Sharma"), new Date());
			trans1.changeProductQuantity(prod, 1);
			trans1.changeProductQuantity(prod1, 1);	 
			service.saveRecord(trans1);
			
			Transaction trans2 = new Transaction(3, new Member("F42563743156", "Yan Martel"), new Date());
			trans2.changeProductQuantity(prod, 1);
			trans2.changeProductQuantity(prod1, 1);
			trans2.changeProductQuantity(prod2, 1);
			trans2.changeProductQuantity(prod3, 1);
			service.saveRecord(trans2);
			
			
			Transaction trans3 = new Transaction(4, new Member("X4242237431326", "Suraj Sharma"), new Date());
			trans3.changeProductQuantity(prod2, 1);
			trans3.changeProductQuantity(prod3, 1);
			trans3.changeProductQuantity(prod22, 1);
			service.saveRecord(trans3);
			
			
			Transaction trans4 = new Transaction(5, new Member("X4242237431326", "Suraj Sharma"), new Date());
			trans4.changeProductQuantity(prod, 1);
			trans4.changeProductQuantity(prod1, 1);
			trans4.changeProductQuantity(prod12, 1);
			trans4.changeProductQuantity(prod22, 1);
			service.saveRecord(trans4);
			
			
		} catch (Exception e) {
			Logger.getLog().log("initProductAndTransaction:" +e.getMessage());
		}
		
	}
		
	private void initDiscount()
	{
		
		try {
			Discount disc = new FirstPurchaseDiscount("MEMBER_FIRST",
					"First purchase by member", 20);
			service.saveRecord(disc);
		
		Discount disc1 = new SubsequentDiscount("MEMBER_SUBSEQ",
				"Subsequent purchase by member", 10);
		service.saveRecord(disc1);
	 

		Discount disc2 = new PublicDiscount("CENTENARY",
				"Centenary Celebration in 2015", 15, "2015-01-01", "365");
		service.saveRecord(disc2);
		} catch (Exception e) {
			Logger.getLog().log("initDiscount:" +e.getMessage());
		}
	 
		 
	}
	
	private void initMember()
	{
		try
		{
			Member member = new Member("F42563743156", "Yan Martel", 150);
			service.saveRecord(member);			 

			Member member1 = new Member("X4242237431326", "Suraj Sharma", 250);
			service.saveRecord(member1);			 

			Member member2 = new Member("R424232231326", "Ang Lee", -1);
			service.saveRecord(member2);
			
			Member member3 = new Member("A0135930J", "A0135930J", -1);
			service.saveRecord(member3);
			
			Member member4 = new Member("A0135945X", "A0135945X", -1);
			service.saveRecord(member4);
			
			Member member5 = new Member("A0135858R", "A0135858R", -1);
			service.saveRecord(member5);
			
			Member member6 = new Member("A0135925B", "A0135925B", -1);
			service.saveRecord(member6);
			
			Member member7 = new Member("A0135931H", "A0135931H", -1);
			service.saveRecord(member7);
			
			Member member8 = new Member("A0135872Y", "A0135872Y", -1);
			service.saveRecord(member8);
			
			Member member9 = new Member("A0135867R", "A0135867R", -1);
			service.saveRecord(member9);
			
			Member member10 = new Member("A0006524R", "A0006524R", -1);
			service.saveRecord(member10);
		} catch (Exception e) {
			Logger.getLog().log("initMember:" +e.getMessage());
		}
	
	}
	 
	
	private void initRepository()
	{		
		
		try {
			 InitStoreKeeper();
			 InitCategoryAndVendor();			
			 initDiscount();
			 initMember();
			 initProductAndTransaction();
			 
			
		} catch (Exception e) {
			Logger.getLog().log("initRepository:" +e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		
		InitApplicationRepository app = new InitApplicationRepository();
		app.initRepository();
		 
	}
}
