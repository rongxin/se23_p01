package sg.edu.nus.iss.shop.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader extends DataRespository
{
	public List<DataRecord> read(String dataSetName) throws IOException  
	{
		super.setupRepository();
		
		Path tmpFilePath = Paths.get(DaoConstant.RELATIVE_FOLDER, dataSetName+DaoConstant.EXT_DATA);
		
		FileReader r = new FileReader(tmpFilePath.toString());
		BufferedReader br = new BufferedReader(r);
		
		List<DataRecord> records = new ArrayList<DataRecord>();
		
		String line = null;
		while ((line = br.readLine()) != null)
		{
			records.add(new DataRecord(line,true));
		}
		br.close();
		
		return records;
	}
}
