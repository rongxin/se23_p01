package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;

public class AdminManagerTest {
	
	String loginuser;
	String loginpass;
	AdminManager adminmanager;
	@Before
	public void setup() {
		try {
			adminmanager=AdminManager.getAdminManager();		
			loginuser="su";
			loginpass="su";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAdminManagerCons() {			
		assertNotNull("Admin Manager must not be null", AdminManager.getAdminManager());
		AdminManager newAdminManager = AdminManager.getAdminManager();
		assertSame("Objects should be the same",adminmanager,newAdminManager);
	}
	@Test
	public void testgetAllStoreKeepers() {			
		List<StoreKeeper> allStoreKeepers = adminmanager.getAllStoreKeepers();
		if (allStoreKeepers == null || allStoreKeepers.size() == 0 || allStoreKeepers.isEmpty()) {
			fail("Cannot find a category");
		}else {
			assertNotNull(allStoreKeepers);
		}	
	}
	
	

	@Test
	public void testValidLogin() throws ApplicationGUIException {				
		
		loginuser="ABB";
		loginpass="123AB1";
		StoreKeeper s= adminmanager.login(loginuser, loginpass);
		assertNotNull("StoreKeeper  object should not be null", AdminManager.getAdminManager());
		if(s==null)
		{
			fail("StoreKeeper object is null.");
		}
		else
		{			
			//assertSame("StoreKeeper.getName() should be same as loginuser",loginuser,s.getName());
			//assertSame("StoreKeeper.getPassword() should be same as loginpass",loginpass,s.getPassword());
			assertEquals(loginuser, s.getName());
			assertEquals(loginpass, s.getPassword());			
		}	
		
	}
	
	@Test
	public void testInvalidLogin() throws ApplicationGUIException {				
	
		StoreKeeper s= adminmanager.login(loginuser, loginpass);
		assertNotNull("StoreKeeper  object should not be null", AdminManager.getAdminManager());
		if(s==null)
		{
			fail("StoreKeeper object is null.");
		}
		else
		{			
			//assertSame("StoreKeeper.getName() should be same as loginuser",loginuser,s.getName());
			//assertSame("StoreKeeper.getPassword() should be same as loginpass",loginpass,s.getPassword());
			assertEquals(loginuser, s.getName());
			assertEquals(loginpass, s.getPassword());
			
		}		
		
	}
	@Test
	public void testChangePassword() throws ApplicationGUIException {				
	
		StoreKeeper olds= adminmanager.login(loginuser, loginpass);
		String newpass="11111";
		assertNotNull("StoreKeeper  object should not be null", AdminManager.getAdminManager());
		assertTrue("New password should not be empty", !newpass.isEmpty());
		assertNotNull("New password should not be null", newpass);
		
		if(olds==null)
		{
			fail("StoreKeeper object is null.");
		}
		else
		{
			assertEquals(loginuser, olds.getName());
			assertEquals(loginpass, olds.getPassword());
			olds=adminmanager.changePassword(olds, newpass);
			if(olds==null)
			{
				fail("StoreKeeper object is null after changepassword.");
			}
			else
			{
				assertEquals(loginuser, olds.getName());
				assertEquals(newpass, olds.getPassword());
			}
			
		}
		
		
	}

}
