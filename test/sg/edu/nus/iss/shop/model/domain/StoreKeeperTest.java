package sg.edu.nus.iss.shop.model.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;
import java.util.Date;

public class StoreKeeperTest {
	private StoreKeeper s;
	

	@Before
	public void setUp() throws Exception {
		s= new StoreKeeper("su", "8888");
		
	}

	@Test
	public void testGettersSetters() {		
	
		assertFalse("username should not be empty",s.getName().isEmpty());
		assertFalse("password should not be empty", s.getPassword().isEmpty());
		assertNotNull("username should not be null", s.getName());
		assertNotNull("password should not be null",s.getPassword());	

	}
}
