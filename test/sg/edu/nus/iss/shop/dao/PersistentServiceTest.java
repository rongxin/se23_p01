package sg.edu.nus.iss.shop.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.ShopKeeper;
import sg.edu.nus.iss.shop.model.domain.Vendor;

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
			
			Category category = new Category("ABB","123AB1");
			
			Object obj = service.retrieveObject(category.getClass(),category.getCode());
			
			if(obj== null)
			{
				service.saveRecord(category);
				obj = service.retrieveObject(category.getClass(),category.getCode());
			}
			assertEquals(category,(Category)obj);
			
			Category category1 = new Category("XYY","123XY1");
			
			Object obj1 = service.retrieveObject(category1.getClass(),category1.getCode());
			if(obj1 == null)
			{
				service.saveRecord(category1);
				obj1 = service.retrieveObject(category1.getClass(),category1.getCode());
			}
			assertEquals(category1,(Category)obj1);
			
			Category category2 = new Category("DDE","123DD1");
			
			Object obj2 = service.retrieveObject(category2.getClass(),category2.getCode());
			if(obj2 == null)
			{
				service.saveRecord(category2);
				obj2 = service.retrieveObject(category2.getClass(),category2.getCode());
			}
			assertEquals(category2,(Category)obj2);
			
			Category category3 = new Category("EED","123EE1");
			
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

	@Test
	public void TestVendor() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			Category category = new Category("ABB","123AB1");			
			//Object obj = service.retrieveObject(category.getClass(),category.getCode());
			
		    Vendor vendor = new Vendor("Name","Description"); 
			service.saveVendor(vendor, category);			
			
			Vendor vendor1 = new Vendor("Name1","Description1"); 
			service.saveVendor(vendor1, category);
			
			
			Vendor vendor2 = new Vendor("Name2","Description2"); 
			service.saveVendor(vendor2, category);
			
			Vendor vendor3 = new Vendor("Name3","Description3"); 
			service.saveVendor(vendor3, category);
			
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve member object. ");
		}
	}
	
	
	@Test
	public void TestStoreKeeper() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			ShopKeeper keeper = new ShopKeeper("ABB","123AB1");				
			service.saveRecord(keeper);	
			
			ShopKeeper keeper1 = new ShopKeeper("ABB1","123AB1");			
			service.saveRecord(keeper1);	
			
			ShopKeeper keeper2 = new ShopKeeper("ABB2","123AB1");			
			service.saveRecord(keeper2);	
			
			ShopKeeper keeper3 = new ShopKeeper("ABB3","123AB1");			
			service.saveRecord(keeper3);	
			
			ShopKeeper keeper4 = new ShopKeeper("ABB4","123AB1");			
			service.saveRecord(keeper4);			
			
			ShopKeeper keeper5 = new ShopKeeper("ABB5","123AB1");			
			service.saveRecord(keeper5);	 
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve member object. ");
		}
	}
	
	
	@Test
	public void TestProduct() {
		
		PersistentService service = PersistentService.getService();
		try {
			
			Product prod = new Product("CLO/1","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod);	
			
			Product prod1 = new Product("CLO/2","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod1);	
			
			Product prod2 = new Product("CLO/3","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod2);	
			
			Product prod3 = new Product("CLU/1","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod3);	
			
			Product prod4 = new Product("CLD/1","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod4);			
			
			Product prod5 = new Product("CLF/1","cENTENARY Jumper","A releally nice momento",315,21.45,"1234",10,100);				
			service.saveRecord(prod5);	
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve member object. ");
		}
	}	
	
	
//	@Test
//	public void TestDiscount() {
//		
//		PersistentService service = PersistentService.getService();
//		try {
//			
//			Discount disc = new Discount("MEMBER_FIRST","First purchase by member", 0 ,"ALWAYS","20","M");				
//			service.saveRecord(disc);	
//			
//			Discount disc1 = new Discount("MEMBER_SUBSEQ","Subsequent purchase by member", 0 ,"ALWAYS","10","M");				
//			service.saveRecord(disc1);	
//			
//			Discount disc2 = new Discount("CENTENARY","Centenary Celebration in 2014", 0 ,"2014-01-01","365","A");				
//			service.saveRecord(disc2);	
//			 
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			Assert.fail("failed to retrieve member object. ");
//		}
//	}	
}
