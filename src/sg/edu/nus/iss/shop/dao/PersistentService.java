package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.adapter.CategoryRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.DataRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.MemberRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.ProductRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.StoreKeeperRecordAdapter;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.ShopKeeper;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class PersistentService 
{
	private CacheDataReader dataReader;
	private CacheDataWriter dataWriter;
	private static PersistentService service;
	
	private boolean hasBuildPK4Category = false;
	private boolean hasBuildPK4Product = false;
	private boolean hasBuildPK4Member = false;
	private boolean hasBuildPK4StoreKeeper = false;
	
	private PersistentService()
	{
		dataReader = new CacheDataReader();
		dataWriter = new CacheDataWriter();
		
		//can be commented if client app call releaseService method
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	try {
					dataWriter.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	public static PersistentService getService()
	{
		if(service == null)
			service = new PersistentService();
		return service;
	}
	
	public void saveRecord(Object recordObj) throws Exception
	{
		DataRecordAdapter adapter = null;
		if(recordObj instanceof Category)
		{
			adapter = new CategoryRecordAdapter((Category)recordObj);			
		}
		else if(recordObj instanceof ShopKeeper)
		{
			adapter = new StoreKeeperRecordAdapter((ShopKeeper)recordObj);			
		}
		else if(recordObj instanceof Member)
		{
			adapter = new MemberRecordAdapter((Member)recordObj);			
		}
		else if(recordObj instanceof Product)
		{
			adapter = new ProductRecordAdapter((Product)recordObj);			
		}
		if(adapter != null)
		{
			dataWriter.writeRecord(recordObj.getClass().getSimpleName(), adapter.getDataRecord());
		}
		else
			throw new InvalidDomainObject("Not support this object saving");
	}
	
	public Object retrieveObject(Class cls, String objectId) throws InvalidDomainObject, InvalidDataFormat, IOException
	{		
		if(isCategoryType(cls))
		{
			return retrieveCategory(cls, objectId); 			
		}
		else if(isProductType(cls))
		{
			return retrieveProduct(cls, objectId); 			
		}
		else if(isMemberType(cls))
		{
			return retrieveMember(cls, objectId); 			
		}
		else if(isStoreKeeperType(cls))
		{
			return retrieveStoreKeeper(cls, objectId); 			
		}
		else
			throw new InvalidDomainObject("Not support this type of data retrieval");
	}
	
	
	public List<Object> retrieveAll(Class cls) throws IOException, InvalidDataFormat, InvalidDomainObject  
	{		
		//if(cls.newInstance() instanceof Category)
		if(isCategoryType(cls))
		{
			return retrieveAllCategories(cls);			
		}
		else if(isProductType(cls))
		{
			return retrieveAllProducts(cls);
		}
		else if(isMemberType(cls))
		{
			return retrieveAllMembers(cls);
		}
		else if(isStoreKeeperType(cls))
		{
			return retrieveAllStoreKeepers(cls);
		}
		else
			throw new InvalidDomainObject("Not support this type of data retrieval");
		
	}

	public List<Vendor> retrieveVendors(Category category) throws Exception
	{
		throw new Exception("Not implement yet");
	}
	
	private boolean isCategoryType(Class cls)
	{
		if (cls.getSimpleName().equals(Category.class.getSimpleName()))
		{
			return true;
		}	 
		return false;
	}
	
	private boolean isProductType(Class cls)
	{
		if (cls.getSimpleName().equals(Product.class.getSimpleName()))
		{
			return true;
		}	 
		return false;
	}
	
	private boolean isMemberType(Class cls)
	{
		if (cls.getSimpleName().equals(Member.class.getSimpleName()))
		{
			return true;
		}	 
		return false;
	}
	
	private boolean isStoreKeeperType(Class cls)
	{
		if (cls.getSimpleName().equals(ShopKeeper.class.getSimpleName()))
		{
			return true;
		}	 
		return false;
	}
	
	private void buildPK4CachedCategory(Class cls)
	{
		if(hasBuildPK4Category)
			return;
		
		try 
		{
			for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
			{
				 try 
				 {
					new CategoryRecordAdapter(record);
				} 
				 catch (InvalidDataFormat e) 
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hasBuildPK4Category = true;
	}
	
	private void buildPK4CachedProduct(Class cls)
	{
		if(hasBuildPK4Product)
			return;
		
		try 
		{
			for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
			{
				 try 
				 {
					new ProductRecordAdapter(record);
				} 
				 catch (InvalidDataFormat e) 
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hasBuildPK4Product = true;
	}
	
	private void buildPK4CachedMember(Class cls)
	{
		if(hasBuildPK4Member)
			return;
		
		try 
		{
			for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
			{
				 try 
				 {
					new MemberRecordAdapter(record);
				} 
				 catch (InvalidDataFormat e) 
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hasBuildPK4Member = true;
	}
	
	private void buildPK4CachedStoreKeeper(Class cls)
	{
		if(hasBuildPK4StoreKeeper)
			return;
		
		try 
		{
			for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
			{
				 try 
				 {
					new StoreKeeperRecordAdapter(record);
				} 
				 catch (InvalidDataFormat e) 
				{
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hasBuildPK4StoreKeeper = true;
	}

	private List<Object> retrieveAllCategories(Class cls) throws IOException, InvalidDataFormat 
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			adapter = new CategoryRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}	 
	
	private List<Object> retrieveAllProducts(Class cls) throws IOException, InvalidDataFormat   
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			adapter = new CategoryRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}
	
	private List<Object> retrieveAllMembers(Class cls) throws IOException, InvalidDataFormat   
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			adapter = new MemberRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}
	
	private List<Object> retrieveAllStoreKeepers(Class cls) throws IOException, InvalidDataFormat   
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			adapter = new StoreKeeperRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}
	
	private Object retrieveCategory(Class cls, String objectId) throws IOException, InvalidDataFormat 
	{		
		buildPK4CachedCategory(cls);
		
		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new CategoryRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}
	
	private Object retrieveProduct(Class cls, String objectId) throws IOException, InvalidDataFormat 
	{		
		buildPK4CachedProduct(cls);
		
		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new ProductRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}
	
	private Object retrieveMember(Class cls, String objectId) throws IOException, InvalidDataFormat 
	{		
		buildPK4CachedMember(cls);
		
		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new MemberRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}
	
	private Object retrieveStoreKeeper(Class cls, String objectId) throws IOException, InvalidDataFormat 
	{		
		buildPK4CachedStoreKeeper(cls);
		
		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new StoreKeeperRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}
	public void releaseService()
	{
		try 
		{
			dataWriter.finalize();
			service = null;			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	 
}
