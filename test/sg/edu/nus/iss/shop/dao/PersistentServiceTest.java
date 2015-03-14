package sg.edu.nus.iss.shop.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Member;

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
			
			Object obj = service.retrieveObject(category.getClass(),category.getCode());
			
			if(obj== null)
			{
				service.saveRecord(category);
				obj = service.retrieveObject(category.getClass(),category.getCode());
			}
			assertEquals(category,(Category)obj);
			
			Category category1 = new Category("XY1","123XY1");
			
			Object obj1 = service.retrieveObject(category1.getClass(),category1.getCode());
			if(obj1 == null)
			{
				service.saveRecord(category1);
				obj1 = service.retrieveObject(category1.getClass(),category1.getCode());
			}
			assertEquals(category1,(Category)obj1);
			
			Category category2 = new Category("DD1","123DD1");
			
			Object obj2 = service.retrieveObject(category2.getClass(),category2.getCode());
			if(obj2 == null)
			{
				service.saveRecord(category2);
				obj2 = service.retrieveObject(category2.getClass(),category2.getCode());
			}
			assertEquals(category2,(Category)obj2);
			
			Category category3 = new Category("EE1","123EE1");
			
			Object obj3 = service.retrieveObject(category3.getClass(),category3.getCode());
			if(obj3 == null)
			{
				service.saveRecord(category3);
				obj3 = service.retrieveObject(category3.getClass(),category3.getCode());
			}
			assertEquals(category3,(Category)obj3);
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve individual object. ");
		}
	}

	@Test
	public void TestRetrieveMemeberObj() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			Member member = new Member("AB1","123AB1");
			
			Object obj = service.retrieveObject(member.getClass(),member.getId());
			
			if(obj== null)
			{
				service.saveRecord(member);
				obj = service.retrieveObject(member.getClass(),member.getId());
			}
			assertEquals(member,(Member)obj);
			
			Member member1 = new Member("CD1","123CD1");
			
			Object obj1 = service.retrieveObject(member1.getClass(),member1.getId());
			
			if(obj1== null)
			{
				service.saveRecord(member1);
				obj1 = service.retrieveObject(member1.getClass(),member1.getId());
			}
			assertEquals(member,(Member)obj);
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve member object. ");
		}
	}

}
