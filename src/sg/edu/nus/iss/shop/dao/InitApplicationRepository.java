package sg.edu.nus.iss.shop.dao;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;
import sg.edu.nus.iss.shop.util.Logger;
 

public class InitApplicationRepository 
{
	
	private void initRepository()
	{
		PersistentService service= PersistentService.getService();
		
		try {
			StoreKeeper keeper = new StoreKeeper("Stacy","Stacy");
			if(service.retrieveObject(StoreKeeper.class, keeper.getName()) == null)
			{
				service.saveRecord(keeper);
				
				StoreKeeper keeper1 = new StoreKeeper("Johny","Johny");			
				service.saveRecord(keeper1);	
				
				StoreKeeper keeper2 = new StoreKeeper("gd","gd");			
				service.saveRecord(keeper2);
			}
			
			Category category = new Category("CLO","Clothing");
			if(service.retrieveObject(StoreKeeper.class, category.getName()) == null)
			{
				service.saveRecord(category);
				
				Category category1 = new Category("MUG","Mugs");
				service.saveRecord(category1);
				
				Category category2 = new Category("STA","Stationary");
				service.saveRecord(category2);
				
				Category category3 = new Category("DIA","Diary");
				service.saveRecord(category3);
				
				Category category4 = new Category("SNA","Snack");
				service.saveRecord(category4);
				
				Category category5 = new Category("CLA","Calendar");
				service.saveRecord(category5);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLog().log("initRepository:" +e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		
		InitApplicationRepository app = new InitApplicationRepository();
		app.initRepository();
		 
	}
}
