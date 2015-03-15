package sg.edu.nus.iss.shop.controller;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.PersistentService;
import sg.edu.nus.iss.shop.exception.ApplicationGUIException;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;

public class AdminManager {

	private static AdminManager theOnlyAdminManager;
	
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
		if (username.isEmpty() || password.isEmpty()) {	
			System.out.println("username or password empty and return null");
			return null;
		}		
		List<StoreKeeper> storeKeepers = getAllStoreKeepers();
		Iterator<StoreKeeper> it = storeKeepers.iterator();
		while (it.hasNext()) {
			StoreKeeper storekeeper = it.next();
			if (storekeeper.getName().equals(username) && storekeeper.getPassword().equals(password)) {
				System.out.println("Login success username:"+username+" pass:"+password);
				return storekeeper;
			}	
			
		}
		System.out.println("Login fail and return null username:"+username+" pass:"+password);			
		return null;
	
	}
	public StoreKeeper logout()
	{
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
	public StoreKeeper getUsersByName(String userid)throws ApplicationGUIException {
		StoreKeeper oldstorekeeper = null;
		try {
			oldstorekeeper = (StoreKeeper) PersistentService.getService().retrieveObject(StoreKeeper.class, userid);
		}catch (Exception e) {
			throw new ApplicationGUIException(e.toString());
		}
		return oldstorekeeper;
	}
	
	public StoreKeeper changePassword(StoreKeeper storekeeper, String newpassword) throws ApplicationGUIException{	
		StoreKeeper oldStoreKeeper = AdminManager.getAdminManager().getUsersByName(storekeeper.getName());
		if(oldStoreKeeper != null) {
			//change password
			oldStoreKeeper.setPassword(newpassword);	
			System.out.println("Password Changed to "+newpassword);
			try {
				PersistentService.getService().saveRecord(oldStoreKeeper);				
				System.out.println("Password Changed to "+newpassword +"Saved Record");
			}catch(Exception e){
				throw new ApplicationGUIException(e.toString());
			}
		} 
		return oldStoreKeeper;
		
	}
}
