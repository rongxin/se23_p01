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
			Category category = new Category("abc","123");
			service.saveRecord(category);
			List<Object> objs = service.retrieveAll((category.getClass()));
			assertEquals(category,(Category)objs.get(0));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("failed to retrieve all ");
		}
	}
}
