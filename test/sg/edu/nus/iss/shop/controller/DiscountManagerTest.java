import static org.junit.Assert.*;

import org.junit.Test;

import sg.edu.nus.iss.shop.controller.DiscountManager;


public class DiscountManagerTest extends TestCase{
	private DiscountManager discountManager;
	private Discount testDiscount;
	
	@Before
	public void setup() {

	}
	
	@Test
	public void testObjectInitialization(){
		discountManager = DiscountManager.getDiscountManager();
		DiscountManager newDiscountManager = discountManager;
		assertSame("Object should be the same",discountManager,newDiscountManager);
	}
	
	
}
