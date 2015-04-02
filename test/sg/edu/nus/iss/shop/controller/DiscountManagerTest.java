package sg.edu.nus.iss.shop.controller;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

import org.junit.Before;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;

public class DiscountManagerTest extends TestCase {
	private DiscountManager discountManager;

	@Before
	public void setup() {

	}

	@Test
	public void testObjectInitialization() {
		discountManager = DiscountManager.getDiscountManager();
		DiscountManager newDiscountManager = discountManager;
		assertSame("Object should be the same", discountManager,
				newDiscountManager);
	}

	@Test
	public void testGetDiscountByCode() throws Exception {
		Discount compareDiscount = DiscountManager.getDiscountManager()
				.getDiscountByCode("CENTENARY");
		assertNotNull(compareDiscount);
		assertEquals("CENTENARY", compareDiscount.getDiscountCode());
	}
	
//	@Test
//	public void testGetAllDiscounts() {
//		assertEquals(3,DiscountManager.getDiscountManager().getAllDiscounts().size());
//	}
	
	@Test
	public void testGetFirstPurchaseDiscountList() {
		String firstPurchaseDiscountCode = "MEMBER_FIRST";
		
		try {
			Discount memberDiscount = DiscountManager.getDiscountManager()
					.getFirstPurchaseDiscountList();
			assertEquals(firstPurchaseDiscountCode, memberDiscount.getDiscountCode());
		} catch (Exception e) {
			Assert.fail("Exception occured when getting first purchase discount.");
		}
	}

	@Test
	public void testGetSubsequentDiscountList() {
		String subsequentDiscountCode = "MEMBER_SUBSEQ";
		
		try {
			Discount memberDiscount = DiscountManager.getDiscountManager()
					.getSubsequentDiscountList();
			assertEquals(subsequentDiscountCode, memberDiscount.getDiscountCode());
		} catch (Exception e) {
			Assert.fail("Exception occured when getting subsequent discount.");
		}
	}

	@Test
	public void testGetMaxValidPublicDiscount() {
		String publicDiscountCode = "CENTENARY";

		try {
			Discount maxValidPublicDiscount = DiscountManager
					.getDiscountManager().getMaxValidPublicDiscount();
			assertEquals(publicDiscountCode, maxValidPublicDiscount.getDiscountCode());
		} catch (Exception e) {
			Assert.fail("Exception occured when getting max public discount.");
		}
	}

	@Test
	public void testEditDiscount() throws Exception {
//		PublicDiscount modifiedDiscount1 = new PublicDiscount("P01",
//				"CHRISTAMS_DAY", 20, "2015-12-23", "3");
//		PublicDiscount modifiedDiscount2 = new PublicDiscount("MEMBER_FIRST",
//				"National Day", 10, "2015-08-07", "3");
//		PublicDiscount modifiedDiscount3 = new PublicDiscount("MEMBER_SUBSEQ",
//				"National Day", 10, "2015-08-07", "3");
//		PublicDiscount modifiedDiscount4 = new PublicDiscount("CENTENARY",
//				"National Day", 10, "ALWAYS", "3");
//		PublicDiscount modifiedDiscount5 = new PublicDiscount("CENTENARY",
//				"National Day", 10, "2015-08-07", "ALWAYS");
//		PublicDiscount modifiedDiscount6 = new PublicDiscount("CENTENARY",
//				"National Day", 10, "2015-08-07", "3");
		
//		DiscountManager.getDiscountManager().editDiscount("NOTEXISTCODE", modifiedDiscount2);
//		DiscountManager.getDiscountManager().editDiscount("CENTENARY", modifiedDiscount2);
//		DiscountManager.getDiscountManager().editDiscount("CENTENARY", modifiedDiscount3);
//		DiscountManager.getDiscountManager().editDiscount("CENTENARY", modifiedDiscount4);
//		DiscountManager.getDiscountManager().editDiscount("CENTENARY", modifiedDiscount5);
//		DiscountManager.getDiscountManager().editDiscount("CENTENARY", modifiedDiscount6);
		
		int newDiscountPercentage = 30;
		DiscountManager.getDiscountManager().editDiscount("MEMBER_FIRST", newDiscountPercentage);
		assertEquals(newDiscountPercentage,DiscountManager.getDiscountManager().getDiscountByCode("MEMBER_FIRST").getDiscountPercentage());
		
		DiscountManager.getDiscountManager().editDiscount("MEMBER_SUBSEQ", newDiscountPercentage);
		assertEquals(newDiscountPercentage,DiscountManager.getDiscountManager().getDiscountByCode("MEMBER_SUBSEQ").getDiscountPercentage());
	
		DiscountManager.getDiscountManager().editDiscount("CENTENARY", newDiscountPercentage);
		assertEquals(newDiscountPercentage,DiscountManager.getDiscountManager().getDiscountByCode("CENTENARY").getDiscountPercentage());
	}
	
	@Test
	public void testAddDiscount(){
		String discountCode = "CHRISTMAS_DAY";
		String description = "Christmas Day";
		int discountPercentage = 5;
		String startDate = "2015-05-01";
		String discountInDays = "30";
		String applicableToMember = "A";
		
		try {
			DiscountManager.getDiscountManager().addDiscount(discountCode, description, discountPercentage, startDate, discountInDays, applicableToMember);
//			assertEquals(description,DiscountManager.getDiscountManager().getDiscountByCode(discountCode).getDescription());
		} catch (Exception e) {
			Assert.fail("Exception occured when adding new discount." + e.toString());
		}
		
	}
}
