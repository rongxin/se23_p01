package sg.edu.nus.iss.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class DataCache 
{
	private static HashMap<String, List<DataRecord>> cahcedData = new  HashMap<String, List<DataRecord>>();
	
	protected static final String DIRTYDATASETNAME = "DataTrack";
	protected static List<DataRecord> dirtyData ; 
	
	protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	protected DataReader reader = new DataReader();
		
	public List<DataRecord> getCachedData(String dataSetName)
	{
		if(!cahcedData.containsKey(dataSetName))
		{
			cacheData(dataSetName);
		}
		
		return cahcedData.get(dataSetName);		 
	}
	
	private void cacheData(String dataSetName)
	{		
		rwl.writeLock().lock();
		cahcedData.put(dataSetName,reader.read(dataSetName));
		rwl.writeLock().unlock();
	}
}
