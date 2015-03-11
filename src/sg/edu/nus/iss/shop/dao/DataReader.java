package sg.edu.nus.iss.shop.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader 
{
	public List<DataRecord> read(String dataSetName) throws Exception
	{
		Path tmpFilePath = Paths.get("", dataSetName,DaoConstant.EXT_DATA);
		
		FileReader r = new FileReader(tmpFilePath.toString());
		BufferedReader br = new BufferedReader(r);
		
		List<DataRecord> records = new ArrayList<DataRecord>();
		
		String line = null;
		while ((line = br.readLine()) != null)
		{
			records.add(new DataRecord(line));			
		}
		br.close();
		
		return records;
	}
}
