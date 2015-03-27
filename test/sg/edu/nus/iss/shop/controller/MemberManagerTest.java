package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
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
	public void testReduceLoyalPoints() {
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
	public void testAdjustLoyalPoints() {
		String testMemberId = "F42563743156";
		int previousPoints = -1;
		int positivePoints = 9;
		int negativePoints = -8;
		
		try {
			Member member = MemberManager.getMemberManager().getMemberById(testMemberId);
			
			previousPoints = member.getLoyalPoints();
			MemberManager.getMemberManager().adjustLoyalPoints(member, positivePoints);
			assertEquals(previousPoints - positivePoints,member.getLoyalPoints());
			
			previousPoints = member.getLoyalPoints();
			MemberManager.getMemberManager().adjustLoyalPoints(member, negativePoints);
			assertEquals(previousPoints - negativePoints,member.getLoyalPoints());
		} catch (ApplicationGUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
