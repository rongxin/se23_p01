package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataCache 
{
	protected static HashMap<String, List<DataRecord>> cahcedData = new  HashMap<String, List<DataRecord>>();
	
	protected static final String DIRTYDATASETNAME = "DataTrack";
	protected static List<DataRecord> dirtyData ; 
	protected DataReader reader = new DataReader();
		
	public List<DataRecord> getCachedData(String dataSetName) throws IOException
	{
		if(!cahcedData.containsKey(dataSetName))
		{
			cacheData(dataSetName);
		}
		
		return cahcedData.get(dataSetName);		 
	}
	
	private void cacheData(String dataSetName) throws IOException  
	{		
		cahcedData.put(dataSetName,reader.read(dataSetName));
	}
}
