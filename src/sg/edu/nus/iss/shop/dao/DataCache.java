package sg.edu.nus.iss.shop.dao;

import java.util.HashMap;
import java.util.List;

public class DataCache 
{
	protected HashMap<String, List<DataRecord>> cahcedData = new  HashMap<String, List<DataRecord>>();
	protected DataReader reader = new DataReader();
		
	public List<DataRecord> getCachedData(String dataSetName) throws Exception
	{
		if(cahcedData.containsKey(dataSetName))
			return cahcedData.get(dataSetName);
		else
		{
			cacheData(dataSetName);
			return cahcedData.get(dataSetName);
		}
	}
	
	private void cacheData(String dataSetName) throws Exception
	{		
		cahcedData.put(dataSetName,reader.read(dataSetName));
	}
	
	
	
}
