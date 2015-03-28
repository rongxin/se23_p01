package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;

public class MemberManagerTest {

	@Before
	public void setup() {
		try {
			MemberManager.getMemberManager().addMember("A0135867R", "Zhu Bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Test normal case
	 * @throws ApplicationGUIException **/
	@Test
	public void testRegisterMember1() throws ApplicationGUIException {
		int oldMemberCount = MemberManager.getMemberManager().getAllMembers().size();
		
		try {
			MemberManager.getMemberManager().addMember("B0135868R" + new Random().nextInt(), "Zhu Bin");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed to add a member");
		}
		Assert.assertEquals(MemberManager.getMemberManager().getAllMembers().size(), oldMemberCount+1);
	}
	
	/**Test invalid id**/
	@Test
	public void testRegisterMember2(){
		try{
			MemberManager.getMemberManager().addMember("123", "Zhu Bin");
			Assert.fail("did not detect invalid id");
		}
		catch (Exception e){
			
		}
	}
	
	@Test
	public void testGetMemberById() throws ApplicationGUIException  {
		String testMemberId = "X4242237431326";
		Member member = MemberManager.getMemberManager().getMemberById(testMemberId);
		assertEquals(testMemberId,member.getId());
	}
	
	@Test
	public void testReduceLoyalPoints1() {
		String testMemberId = "F42563743156";
		int previousPoints = -1;
		int usedPoints = 9;
		try {
			Member member = MemberManager.getMemberManager().getMemberById(testMemberId);
			previousPoints = member.getLoyalPoints();
			
			MemberManager.getMemberManager().reduceLoyalPoints(member, usedPoints);
			assertEquals(previousPoints - usedPoints,member.getLoyalPoints());
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIncreaseLoyalPoints() {
		String testMemberId = "A0135925";
		String testMemberName = "Tao Tong";
		int earnPoints = 100;

		try {
			Discount discount = DiscountManager.getDiscountManager().getSubsequentDiscountList();
			MemberManager.getMemberManager().addMember(testMemberId, testMemberName);
			Member member = MemberManager.getMemberManager().getMemberById(testMemberId);
			MemberManager.getMemberManager().increaseLoyalPoints(member, earnPoints);
			
			member = MemberManager.getMemberManager().getMemberById(testMemberId);
			assertEquals(earnPoints,member.getLoyalPoints());
			assertEquals(discount.getDiscountPercentage(),member.getMaxDiscount().getDiscountPercentage());
		} catch (ApplicationGUIException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
