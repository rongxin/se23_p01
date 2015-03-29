package sg.edu.nus.iss.shop.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.util.Logger;

public class DataReader extends DataRespository
{
	private Logger logger = Logger.getLog();
	public List<DataRecord> read(String dataSetName)  
	{
		super.setupRepository();
		
		List<DataRecord> records = new ArrayList<DataRecord>();
		
		Path tmpFilePath = Paths.get(DaoConstant.RELATIVE_FOLDER, dataSetName+DaoConstant.EXT_DATA);
		
		FileReader r =null;
		try {
			r = new FileReader(tmpFilePath.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log("read1:" + e.getMessage());
			return records;
		}
		
		BufferedReader br = new BufferedReader(r); 
		
		String line = null;
		try 
		{
			while ((line = br.readLine()) != null)
			{
				records.add(new DataRecord(line,true));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log("read2:" +e.getMessage());
		}
		finally
		{
			if(br != null)
			{
				try 
				{
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.log("read3:" +e.getMessage());
				}
			}
		}
		
		return records;
	}
}
