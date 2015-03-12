package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheDataWriter extends DataCache
{
	private DataWriter writer = new DataWriter();
	public void writeRecord(String dataSetName,DataRecord record) throws IOException
	{
		if(cahcedData.containsKey(dataSetName))
		{
			cahcedData.get(dataSetName).add(record);
		}
		else
		{
			List<DataRecord> records = new  ArrayList<DataRecord>();
			records.add(record);
			cahcedData.put(dataSetName,records);
		}
		
		writer.write(dataSetName, cahcedData.get(dataSetName));
	}
	
	//private void write
}
