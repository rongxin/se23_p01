package sg.edu.nus.iss.shop.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;
import sg.edu.nus.iss.shop.model.domain.Vendor;
import sg.edu.nus.iss.shop.model.nondomain.TransactionRecord;

public class PersistentServiceTest {

	PersistentService service;
	List<Category> categories;
	List<Product> products;
	List<StoreKeeper> storekeepers;
	List<Member> members;
	List<Discount> discounts;
	List<Vendor> vendors;

	@Before
	public void setup() {
		service = PersistentService.getService();
		categories = new ArrayList<Category>();
		products = new ArrayList<Product>();
		storekeepers = new ArrayList<StoreKeeper>();
		members = new ArrayList<Member>();
		discounts = new ArrayList<Discount>();
		vendors = new ArrayList<Vendor>();
	}

	@Test
	public void TestSaveAndRetrieveRecord4Category() {
		try {

			Category category = new Category("CLO", "Clothing");
			service.saveRecord(category);
			Category retrieved = service.retrieveObject(Category.class,
					category.getCode());
			assertNotNull(retrieved);
			assertEquals(category, retrieved);

			Category category1 = new Category("MUG", "Mugs");
			service.saveRecord(category1);
			Category retrieved1 = service.retrieveObject(Category.class,
					category1.getCode());
			assertNotNull(retrieved1);
			assertEquals(category1, retrieved1);

			Category category2 = new Category("STA", "Stationary");
			service.saveRecord(category2);
			Category retrieved2 = service.retrieveObject(Category.class,
					category2.getCode());
			assertNotNull(retrieved2);
			assertEquals(category2, retrieved2);

			Category category3 = new Category("DIA", "Diary");
			service.saveRecord(category3);
			Category retrieved3 = service.retrieveObject(Category.class,
					category3.getCode());
			assertNotNull(retrieved3);
			assertEquals(category3, retrieved3);

			Category retrieved4 = service.retrieveObject(Category.class, "CLA");
			assertNull(retrieved4);
			assertNotEquals(category3, retrieved4);

			categories.add(category);
			categories.add(category1);
			categories.add(category2);
			categories.add(category3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed to save and retrieve category" + ": " + e.toString());

		}

	}

	@Test
	public void TestRetrieveAll4Category() {
		try {

			if (categories.size() <= 0) {
				TestSaveAndRetrieveRecord4Category();
			}

			List<Category> retrievedList = service.retrieveAll(Category.class);
			// assertEquals(retrievedList.size(), categories.size());

			boolean r = false;
			for (Category retrieved : retrievedList) {
				r = false;
				for (Category inited : categories) {
					if (inited.getCode().equals(retrieved.getCode())) {
						assertEquals(retrieved, inited);
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve all categories");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all categories");
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all categories");
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			fail("failed to retrieve all categories");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TestSaveAndRetrieveRecord4Product() {
		try {
			Product prod = new Product("CLO/1", "Centenary Jumper",
					"A releally nice momento", 315, 21.45, "1234", 10, 100);
			service.saveRecord(prod);
			Product retrieved = service.retrieveObject(Product.class,
					prod.getProductId());
			assertNotNull(retrieved);
			assertEquals(prod, retrieved);

			Product prod1 = new Product("MUG/1", "Centenary Mug",
					"A releally nice mug this time", 525, 10.25, "9876", 25,
					150);
			service.saveRecord(prod1);
			Product retrieved1 = service.retrieveObject(Product.class,
					prod1.getProductId());
			assertNotNull(retrieved1);
			assertEquals(prod1, retrieved1);

			Product prod2 = new Product("STA/1", "NUS Pen",
					"A releally cute blue pen", 768, 5.75, "123456789", 50, 250);
			service.saveRecord(prod2);
			Product retrieved2 = service.retrieveObject(Product.class,
					prod2.getProductId());
			assertNotNull(retrieved2);
			assertEquals(prod2, retrieved2);

			Product prod3 = new Product("STA/2", "NUS Notepad",
					"Great notepad for those", 315, 21.45, "1234", 10, 100);
			service.saveRecord(prod3);
			Product retrieved3 = service.retrieveObject(Product.class,
					prod3.getProductId());
			assertNotNull(retrieved3);
			assertEquals(prod3, retrieved3);

			Product retrieved4 = service.retrieveObject(Product.class, "prd1");
			assertNull(retrieved4);
			assertNotEquals(prod3, retrieved4);

			products.add(prod);
			products.add(prod1);
			products.add(prod2);
			products.add(prod3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed to save and retrieve product" + ": " + e.toString());

		}

	}

	@Test
	public void TestRetrieveAll4Product() {
		try {

			if (products.size() <= 0) {
				TestSaveAndRetrieveRecord4Product();
			}

			List<Product> retrievedList = service.retrieveAll(Product.class);

			boolean r = false;
			for (Product retrieved : retrievedList) {
				r = false;
				for (Product inited : products) {
					if (inited.getProductId().equals(retrieved.getProductId())) {
						assertEquals(retrieved, inited);
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve all products");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all products");
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all products");
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			fail("failed to retrieve all products");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TestSaveAndRetrieveRecord4StoreKeeper() {
		try {

			StoreKeeper keeper = new StoreKeeper("Stacy", "Stacy");
			service.saveRecord(keeper);
			StoreKeeper retrieved = service.retrieveObject(StoreKeeper.class,
					keeper.getName());
			assertNotNull(retrieved);
			assertEquals(keeper.getName(), retrieved.getName());

			StoreKeeper keeper1 = new StoreKeeper("Johny", "Johny");
			service.saveRecord(keeper1);
			StoreKeeper retrieved1 = service.retrieveObject(StoreKeeper.class,
					keeper1.getName());
			assertNotNull(retrieved1);
			assertEquals(keeper1.getName(), retrieved1.getName());

			StoreKeeper keeper2 = new StoreKeeper("gd", "gd");
			service.saveRecord(keeper2);
			StoreKeeper retrieved2 = service.retrieveObject(StoreKeeper.class,
					keeper2.getName());
			assertNotNull(retrieved2);
			assertEquals(keeper2.getName(), retrieved2.getName());

			StoreKeeper retrieved4 = service.retrieveObject(StoreKeeper.class,
					"prd1");
			assertNull(retrieved4);
			assertNotEquals(keeper2, retrieved4);

			storekeepers.add(keeper);
			storekeepers.add(keeper1);
			storekeepers.add(keeper2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed to save and retrieve storekeeper" + ": "
					+ e.toString());

		}

	}

	@Test
	public void TestRetrieveAll4StoreKeeper() {
		try {

			if (storekeepers.size() <= 0) {
				TestSaveAndRetrieveRecord4StoreKeeper();
			}

			List<StoreKeeper> retrievedList = service
					.retrieveAll(StoreKeeper.class);

			boolean r = false;
			for (StoreKeeper retrieved : retrievedList) {
				r = false;
				for (StoreKeeper inited : storekeepers) {
					if (inited.getName().equals(retrieved.getName())) {
						assertEquals(retrieved.getName(), inited.getName());
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve all storekeeper");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all storekeeper");
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all storekeeper");
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			fail("failed to retrieve all storekeeper");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TestSaveAndRetrieveRecord4Member() {
		try {
			Member member = new Member("F42563743156", "Yan Martel", 150);
			service.saveRecord(member);
			Member retrieved = service.retrieveObject(Member.class,
					member.getId());
			assertNotNull(retrieved);
			assertEquals(member, retrieved);

			Member member1 = new Member("X4242237431326", "Suraj Sharma");
			service.saveRecord(member1);
			Member retrieved1 = service.retrieveObject(Member.class,
					member1.getId());
			assertNotNull(retrieved);
			assertEquals(member1, retrieved1);

			Member member2 = new Member("R424232231326", "Ang Lee", -1);
			service.saveRecord(member2);
			Member retrieved2 = service.retrieveObject(Member.class,
					member2.getId());
			assertNotNull(retrieved);
			assertEquals(member2, retrieved2);

			Member retrieved4 = service.retrieveObject(Member.class, "prd1");
			assertNull(retrieved4);
			assertNotEquals(member2, retrieved4);

			members.add(member);
			members.add(member1);
			members.add(member2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed to save and retrieve member" + ": " + e.toString());

		}

	}

	@Test
	public void TestRetrieveAll4SMember() {
		try {

			if (members.size() <= 0) {
				TestSaveAndRetrieveRecord4Member();
			}

			List<Member> retrievedList = service.retrieveAll(Member.class);

			boolean r = false;
			for (Member retrieved : retrievedList) {
				r = false;
				for (Member inited : members) {
					if (inited.getId().equals(retrieved.getId())) {
						assertEquals(retrieved, inited);
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve all members");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all members");
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all members");
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			fail("failed to retrieve all members");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TestSaveAndRetrieveRecord4Discount() {
		try {

			Discount disc = new FirstPurchaseDiscount("MEMBER_FIRST",
					"First purchase by member", 20);
			service.saveRecord(disc);
			Discount retrieved = service.retrieveObject(Discount.class,
					disc.getDiscountCode());
			assertNotNull(retrieved);
			assertEquals(disc.getDiscountCode(), retrieved.getDiscountCode());

			Discount disc1 = new SubsequentDiscount("MEMBER_SUBSEQ",
					"Subsequent purchase by member", 10);
			service.saveRecord(disc1);
			Discount retrieved1 = service.retrieveObject(Discount.class,
					disc1.getDiscountCode());
			assertNotNull(retrieved1);
			assertEquals(disc1.getDiscountCode(), retrieved1.getDiscountCode());

			Discount disc2 = new PublicDiscount("CENTENARY",
					"Centenary Celebration in 2014", 0, "2014-01-01", "365");
			service.saveRecord(disc2);
			Discount retrieved2 = service.retrieveObject(Discount.class,
					disc2.getDiscountCode());
			assertNotNull(retrieved2);
			assertEquals(disc2.getDiscountCode(), retrieved2.getDiscountCode());

			Discount retrieved4 = service
					.retrieveObject(Discount.class, "prd1");
			assertNull(retrieved4);
			assertNotEquals(disc2, retrieved4);

			discounts.add(disc);
			discounts.add(disc1);
			discounts.add(disc2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed to save and retrieve discount" + ": " + e.toString());

		}

	}

	@Test
	public void TestRetrieveAll4Discount() {
		try {

			if (discounts.size() <= 0) {
				TestSaveAndRetrieveRecord4Discount();
			}

			List<Discount> retrievedList = service.retrieveAll(Discount.class);

			boolean r = false;
			for (Discount retrieved : retrievedList) {
				r = false;
				for (Discount inited : discounts) {
					if (inited.getDiscountCode().equals(retrieved.getDiscountCode())) {
						assertEquals(retrieved.getDiscountCode(), inited.getDiscountCode());
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve all discounts");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all discounts");
			e.printStackTrace();
		} catch (InvalidDataFormat e) {
			// TODO Auto-generated catch block
			fail("failed to retrieve all discounts");
			e.printStackTrace();
		} catch (InvalidDomainObject e) {
			fail("failed to retrieve all discounts");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
 
	@Test
	public void TestSaveAndRetrieveRecord4Vendor() {

		try {

			Category category = new Category("MUG", "Mugs");
		 
			Vendor vendor = new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's");
			service.saveVendor(vendor, category);

			Vendor vendor1 = new Vendor("Office Sovenirs", "One and Only Office Sovenirs");
			service.saveVendor(vendor1, category);

			Vendor vendor2 = new Vendor("Pen's and Such", "Sovenirs and gifts for all occasions");
			service.saveVendor(vendor2, category);

			Vendor vendor3 = new Vendor("ArtWorks Stationary Store", "All Kinds of Sttionary and Gifts");
			service.saveVendor(vendor3, category);
			
			vendors.add(vendor);
			vendors.add(vendor1);
			vendors.add(vendor2);
			vendors.add(vendor3);
			
			List<Vendor> retrieveds = service.retrieveVendors(category);
			boolean r = false;
			for (Vendor retrieved : retrieveds) {
				r = false;
				for (Vendor inited : vendors) {
					if (inited.getName().equals(retrieved.getName())) {
						assertEquals(retrieved, inited);
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve category's vendors");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to save and retrieve vendor object. ");
		}
	} 
	
	
	@Test
	public void TestSaveAndRetrieveRecord4Transaction() {

		try {
			
			Product product = new Product("CLO/1", "Centenary Jumper", "A releally nice momento", 1, 1, "1", 1, 1);
			Transaction trans = new Transaction(1, new Member("1", "Stacy"), new Date());
			trans.changeProductQuantity(product, 1);
			//TransactionDetail transDetail = new TransactionDetail(trans, product, 10); 
			
			assertTrue(trans.getTransactionDetails().size() >0);
			service.saveRecord(trans);
			 

			Product product1 = new Product("CLO/2", "Centenary Jumper", "A releally nice momento", 1, 1, "1", 1, 1);
			Transaction trans1 = new Transaction(2, new Member("1", "Stacy"), new Date());
			trans1.changeProductQuantity(product, 1);
			trans1.changeProductQuantity(product1, 1);
//			TransactionDetail transDetail2 = new TransactionDetail(trans1, product, 10);
//			TransactionDetail transDetail3 = new TransactionDetail(trans1, product1, 1);
			assertTrue(trans1.getTransactionDetails().size() >0);
			service.saveRecord(trans1);
 
			
			List<Transaction> transes = new ArrayList<Transaction>();
			transes.add(trans);
			transes.add(trans1);			 
			
			List<TransactionRecord> retrieveds = service.retrieveAll(Transaction.class);
			boolean r = false;
			for (TransactionRecord retrieved : retrieveds) {
				r = false;
				for (Transaction inited : transes) {
					if (inited.getId() == retrieved.getId()) {
						assertEquals(retrieved.getId(), inited.getId());
						r = true;
					}
				}
			}

			if (!r) {
				fail("failed to retrieve transaction");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to save and retrieve transaction object. ");
		}
	} 
	 
}
