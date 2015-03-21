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
}
