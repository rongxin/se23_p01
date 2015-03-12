package sg.edu.nus.iss.shop.dao;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DataWriter extends DataRespository
{	
	public void write(String dataSetName,List<DataRecord> records) throws IOException
	{		
		super.setupRepository();
		
		Path tmpFilePath = Paths.get(DaoConstant.RELATIVE_FOLDER, dataSetName+DaoConstant.EXT_TEMP);
		
		PrintWriter pw = new PrintWriter(tmpFilePath.toString());
		for(DataRecord record: records)
		{
			pw.println(record.toString());
		}		
		pw.close();
		
		Path filePath = Paths.get(DaoConstant.RELATIVE_FOLDER,dataSetName+DaoConstant.EXT_DATA);
		Files.copy(tmpFilePath, filePath,StandardCopyOption.REPLACE_EXISTING);
		Files.delete(tmpFilePath);
	}
}
