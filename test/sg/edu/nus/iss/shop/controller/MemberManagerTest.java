package sg.edu.nus.iss.shop.controller;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Category;

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
	public void TestRegisterMember1() throws ApplicationGUIException {
		int oldMemberCount = MemberManager.getMemberManager().getAllMembers().size();
		try {
			MemberManager.getMemberManager().addMember("B0135868R", "Zhu Bin");
		} catch (Exception e) {
			Assert.fail("failed to add a member");
		}
		Assert.assertEquals(MemberManager.getMemberManager().getAllMembers().size(), oldMemberCount+1);
	}
	
	/**Test invalid id**/
	@Test
	public void TestRegisterMember2(){
		try{
			MemberManager.getMemberManager().addMember("123", "Zhu Bin");
			Assert.fail("did not detect invalid id");
		}
		catch (Exception e){
			
		}
	}
	


}
