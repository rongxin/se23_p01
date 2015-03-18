package sg.edu.nus.iss.shop.controller;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;

public class DiscountManagerTest extends TestCase {
	private DiscountManager discountManager;
	private Discount testDiscount;
	private Discount modifiedDiscount;

	@Before
	public void setup() {
		testDiscount = PublicDiscount("01", "Christmas Day", 10, "2015-12-24", "3", "A");
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
				.getDiscountByCode("01");
		assertNotNull(compareDiscount);
		assertSame(testDiscount, compareDiscount);
	}

	@Test
	public void testEditDiscount() throws Exception {
		modifiedDiscount = PublicDiscount("01", "National Day", 20, "2015-08-08",
				"3", "A");
		assertSame(
				modifiedDiscount,
				DiscountManager.getDiscountManager().editDiscount(
						modifiedDiscount.getDiscountCode(),
						modifiedDiscount.getDescription(),
						modifiedDiscount.getDiscountPercentage(),
						modifiedDiscount.getStartDate(),
						modifiedDiscount.getDiscountInDays(),
						modifiedDiscount.getApplicableToMember()));
	}

	private Discount PublicDiscount(String string, String string2, int i,
			String string3, String string4, String string5) {
		// TODO Auto-generated method stub
		return null;
	}
}
