package sg.edu.nus.iss.shop.controller;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.StoreInstruction;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;

public class AdminManager {

	private static AdminManager theOnlyAdminManager;
	private static final String INVALID_LOGIN_MESSAGE = "Invalid userid or password.";
	private static final String EMPTY_USERNAME_MESSAGE = "Please enter username.";
	private static final String EMPTY_PASSWORD_MESSAGE = "Please enter password.";
	
	private AdminManager() {
	}
	public static AdminManager getAdminManager() {
		if (AdminManager.theOnlyAdminManager == null) {
			AdminManager.theOnlyAdminManager = new AdminManager();
		}
		return AdminManager.theOnlyAdminManager;
	}
	
	public StoreKeeper login(String username,String password) throws ApplicationGUIException
	{
		if (username == null || username.trim().length() == 0) {
			throw new ApplicationGUIException(AdminManager.EMPTY_USERNAME_MESSAGE);
		}
		if (password == null || password.trim().length()==0) {
			throw new ApplicationGUIException(AdminManager.EMPTY_PASSWORD_MESSAGE);
		}
		List<StoreKeeper> storeKeepers = getAllStoreKeepers();
		Iterator<StoreKeeper> it = storeKeepers.iterator();
		while (it.hasNext()) {
			StoreKeeper storekeeper = it.next();
			if (storekeeper.getName().equals(username) && storekeeper.getPassword().equals(password)) {
				return storekeeper;
			}else
			{
				throw new ApplicationGUIException(AdminManager.INVALID_LOGIN_MESSAGE);				
			}
		}
		return null;
	
	}
	public List<StoreKeeper> getAllStoreKeepers() {
		
		List<StoreKeeper> allUsers = new LinkedList<StoreKeeper>();
		List<Object> objList = null;
		
		try {
			objList = PersistentService.getService().retrieveAll(StoreKeeper.class);
		}catch (Exception e){
			try {
				throw new ApplicationGUIException(e.toString());
			} catch (ApplicationGUIException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Check if the objects are null or empty
		if(objList != null && !objList.isEmpty()) {
			Iterator<Object> it = objList.iterator();
			while (it.hasNext()) {				
				allUsers.add((StoreKeeper)it.next());
			}
		} 
		return  allUsers;
	}
}
