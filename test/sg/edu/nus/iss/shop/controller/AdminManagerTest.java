package sg.edu.nus.iss.shop.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
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
			adminmanager = AdminManager.getAdminManager();
			loginuser = "su";
			loginpass = "su";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdminManagerCons() {
		assertNotNull("Admin Manager must not be null", AdminManager.getAdminManager());
		AdminManager newAdminManager = AdminManager.getAdminManager();
		assertSame("Objects should be the same", adminmanager, newAdminManager);
	}

	@Test
	public void testgetAllStoreKeepers() {
		List<StoreKeeper> allStoreKeepers = adminmanager.getAllStoreKeepers();
		if (allStoreKeepers == null || allStoreKeepers.size() == 0 || allStoreKeepers.isEmpty()) {
			fail("Cannot find a category");
		} else {
			assertNotNull(allStoreKeepers);
		}
	}

	@Test
	public void testValidLogin() throws ApplicationGUIException {


		//StoreKeeper s = adminmanager.login(loginuser, loginpass);
		List<StoreKeeper> allStoreKeepers = AdminManager.getAdminManager().getAllStoreKeepers();
		if (allStoreKeepers == null || allStoreKeepers.isEmpty()) {
			Assert.fail("cannot find any store keepers");
			return;
		}
		StoreKeeper s = allStoreKeepers.get(new Random().nextInt(allStoreKeepers.size()));
		assertNotNull("StoreKeeper  object should not be null", s);
		StoreKeeper newS = AdminManager.getAdminManager().login(s.getName(), s.getPassword());
		if (s != null) {
			// assertSame("StoreKeeper.getName() should be same as loginuser",loginuser,s.getName());
			// assertSame("StoreKeeper.getPassword() should be same as loginpass",loginpass,s.getPassword());
			assertEquals(s.getName(), newS.getName());
			assertEquals(s.getPassword(), newS.getPassword());

		}

	}

	@Test
	public void testInvalidLogin() throws ApplicationGUIException {

		//StoreKeeper s = adminmanager.login(loginuser, loginpass);
		List<StoreKeeper> allStoreKeepers = AdminManager.getAdminManager().getAllStoreKeepers();
		if (allStoreKeepers == null || allStoreKeepers.isEmpty()) {
			Assert.fail("cannot find any store keepers");
			return;
		}
		StoreKeeper s = allStoreKeepers.get(new Random().nextInt(allStoreKeepers.size()));
		assertNotNull("StoreKeeper  object should not be null", s);
		StoreKeeper newS = AdminManager.getAdminManager().login(s.getName(), s.getPassword() + "a");
		Assert.assertNull(newS);

	}

	@Test
	public void testChangePassword() throws ApplicationGUIException {

		List<StoreKeeper> allStoreKeepers = AdminManager.getAdminManager().getAllStoreKeepers();
		if (allStoreKeepers == null || allStoreKeepers.isEmpty()) {
			Assert.fail("cannot find any store keepers");
			return;
		}
		StoreKeeper olds = allStoreKeepers.get(new Random().nextInt(allStoreKeepers.size()));
		String newpass = new Random().nextInt(100000) + "";
		assertNotNull("StoreKeeper  object should not be null", olds);
		assertTrue("New password should not be empty", !newpass.isEmpty());
		assertNotNull("New password should not be null", newpass);

		//assertEquals(loginuser, olds.getName());
		//assertEquals(loginpass, olds.getPassword());
		StoreKeeper newS = adminmanager.changePassword(olds, newpass);
		if (newS == null) {
			fail("StoreKeeper object is null after changepassword.");
		} else {
			assertEquals(olds.getName(), newS.getName());
			assertEquals(newpass, newS.getPassword());
		}

	}

}
