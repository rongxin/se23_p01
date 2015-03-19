package sg.edu.nus.iss.shop.controller;

import org.junit.Test;
import junit.framework.TestCase;

import org.junit.Before;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;

public class DiscountManagerTest extends TestCase {
	private DiscountManager discountManager;
	private FirstPurchaseDiscount firstPurchaseDiscount;
	private SubsequentDiscount subsequentDiscount;
	private PublicDiscount publicDiscount;

	@Before
	public void setup() {
		publicDiscount = new PublicDiscount("CENTENARY",
				"Centenary Celebration in 2014", 10, "2014-01-01", "365");
		firstPurchaseDiscount = new FirstPurchaseDiscount("MEMBER_FIRST",
				"First purchase by member", 20);
		subsequentDiscount = new SubsequentDiscount("MEMBER_SUBSEQ",
				"Subsequent purchase by member", 10);
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

	@Test
	public void testGetFirstPurchaseDiscountList() {
		try {
			Discount memberDiscount = DiscountManager.getDiscountManager().getFirstPurchaseDiscountList();
			assertSame(firstPurchaseDiscount,memberDiscount);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void testGetSubsequentDiscountList() {
		try {
			Discount memberDiscount = DiscountManager.getDiscountManager().getSubsequentDiscountList();
			assertSame(subsequentDiscount,memberDiscount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMaxValidPublicDiscount() {
		try {
			Discount maxValidPublicDiscount = DiscountManager
					.getDiscountManager().getMaxValidPublicDiscount();
			assertSame(publicDiscount, maxValidPublicDiscount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// @Test
	// public void testEditDiscount() throws Exception {
	// PublicDiscount modifiedDiscount = new PublicDiscount("P01",
	// "CHRISTAMS_DAY", 20, "2015-12-23","3");
	// assertSame(
	// modifiedDiscount,
	// DiscountManager.getDiscountManager().editDiscount(
	// modifiedDiscount.getDiscountCode(),
	// modifiedDiscount.getDescription(),
	// modifiedDiscount.getDiscountPercentage(),
	// modifiedDiscount.getStartDate(),
	// modifiedDiscount.getDiscountInDays(),
	// modifiedDiscount.getApplicableToMember()));
	// }
}
