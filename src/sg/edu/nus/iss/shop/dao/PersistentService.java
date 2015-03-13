package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.adapter.CategoryRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.DataRecordAdapter;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Product;

public class PersistentService 
{
	private CacheDataReader dataReader;
	private CacheDataWriter dataWriter;
	private static PersistentService service;
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
			DataRecordAdapter adapter = null;
			System.out.println(objectId);
			for(DataRecord record : dataReader.getCachedData(cls.getSimpleName()))
			{
				System.out.println(record.getPK());
				if(objectId.equals(record.getPK()))
				{
					adapter = new CategoryRecordAdapter(record);
					return adapter.getDataObject();
				}
			}
			return null; 			
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
		else
			throw new InvalidDomainObject("Not support this type of data retrieval");
		
	}

	private boolean isCategoryType(Class cls)
	{
		if (cls.getSimpleName().equals(Category.class.getSimpleName()))
		{
			return true;
		}	 
		return false;
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
	
	private boolean isProductType(Class cls)
	{
		if (cls.getSimpleName().equals(Product.class.getSimpleName()))
		{
			return true;
		}	 
		return false;		 		 
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
	
	public void releaseService()
	{
		try 
		{
			dataWriter.finalize();
			service = null;			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
