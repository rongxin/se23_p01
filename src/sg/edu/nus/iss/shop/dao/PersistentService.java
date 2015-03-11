package sg.edu.nus.iss.shop.dao;

import java.util.List;

import sg.edu.nus.iss.shop.dao.adapter.CategoryRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.DataRecordAdapter;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.model.domain.Category;

public class PersistentService 
{
	private CacheDataReader dataReader;
	private CacheDataWriter dataWriter;
	private static PersistentService service;
	private PersistentService()
	{
		dataReader = new CacheDataReader();
		dataWriter = new CacheDataWriter();
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
			dataWriter.writeRecord(adapter.getDataRecord());
		}
		else
			throw new InvalidDomainObject("Not support this object saving");
	}
	
	public Object retrieveObject(Class cls, String objectId) throws Exception
	{
		throw new Exception("Not implement yet");
	}
	
	public List<Object> retrieveAll(Class cls) throws Exception
	{
		throw new Exception("Not implement yet");
	}
	
}
