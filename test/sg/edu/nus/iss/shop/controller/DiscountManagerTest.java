import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import sg.edu.nus.iss.shop.controller.DiscountManager;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Product;

public class DiscountManagerTest extends TestCase {
	private DiscountManager discountManager;
	private Discount testDiscount;
	private Discount modifiedDiscount;

	@Before
	public void setup() {
		testDiscount = Discount("01", "Christmas Day", 10, "2015-12-24", "3",
				"A");
	}

	@Test
	public void testObjectInitialization() {
		discountManager = DiscountManager.getDiscountManager();
		DiscountManager newDiscountManager = discountManager;
		assertSame("Object should be the same", discountManager,
				newDiscountManager);
	}

	@Test
	public void testGetDiscountByCode() {
		Discount compareDiscount = DiscountManager.getDiscountManager()
				.getDiscountByCode("01");
		assertNotNull(compareDiscount);
		assertSame(testDiscount, compareDiscount);
	}

	@Test
	public void testEditDiscount() {
		modifiedDiscount = Disocunt("01", "National Day", 20, "2015-08-08",
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
}
