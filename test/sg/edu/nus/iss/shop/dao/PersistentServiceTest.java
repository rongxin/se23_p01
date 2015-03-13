package sg.edu.nus.iss.shop.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;

public class PersistentServiceTest 
{
	@Test
	public void TestRetrieveAll() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			Category category = new Category("ABC","123ABC");
			service.saveRecord(category);
			
			Category category1 = new Category("XYZ","123XYZ");
			service.saveRecord(category1);
			
			Category category2 = new Category("DDD","123DDD");
			service.saveRecord(category2);
			
			Category category3 = new Category("EEE","123EEE");
			service.saveRecord(category3);
			
			List<Object> objs = service.retrieveAll((category.getClass()));
			for(Object obj:objs)
			{
				Category cat = (Category)obj;
				System.out.println(cat.getCode()+":" + cat.getName());
			}
			//assertEquals(category,(Category)objs.get(0));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve all ");
		}
	}
	
	@Test
	public void TestRetrieveIndividualObj() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			Category category = new Category("AB1","123AB1");
			service.saveRecord(category);
			Object obj = service.retrieveObject(category.getClass(),category.getCode());  
			assertEquals(category,(Category)obj);
			
			Category category1 = new Category("XY1","123XY1");
			service.saveRecord(category1);
			Object obj1 = service.retrieveObject(category1.getClass(),category1.getCode());  
			assertEquals(category1,(Category)obj1);
			
			Category category2 = new Category("DD1","123DD1");
			service.saveRecord(category2);
			Object obj2 = service.retrieveObject(category2.getClass(),category2.getCode());  
			assertEquals(category2,(Category)obj2);
			
			Category category3 = new Category("EE1","123EE1");
			service.saveRecord(category3);
			Object obj3 = service.retrieveObject(category3.getClass(),category3.getCode());  
			assertEquals(category3,(Category)obj3);
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve individual object. ");
		}
	}

}
