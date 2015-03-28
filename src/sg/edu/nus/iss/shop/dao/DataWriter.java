package sg.edu.nus.iss.shop.dao;
 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

public class DataWriter extends DataRespository
{	
	public void write(String dataSetName,List<DataRecord> records) throws IOException
	{		
		super.setupRepository();
		
		Path tmpFilePath = Paths.get(DaoConstant.RELATIVE_FOLDER, dataSetName+DaoConstant.EXT_TEMP);
		
		PrintWriter pw = new PrintWriter(tmpFilePath.toString());
		Iterator<DataRecord> it = records.iterator();
		while(it.hasNext())
		{
			pw.println(it.next().toString());
		}		
		pw.close();
		
		Path filePath = Paths.get(DaoConstant.RELATIVE_FOLDER,dataSetName+DaoConstant.EXT_DATA);
		Files.copy(tmpFilePath, filePath,StandardCopyOption.REPLACE_EXISTING);
		Files.delete(tmpFilePath);
	}
	
	public void writeInAppend(String dataSetName,List<DataRecord> records) throws IOException
	{		
		super.setupRepository();
		
		Path filePath = Paths.get(DaoConstant.RELATIVE_FOLDER, dataSetName+DaoConstant.EXT_DATA);
		FileWriter fw = new FileWriter(filePath.toString(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Iterator<DataRecord> it = records.iterator();
		while(it.hasNext())
		{
			pw.println(it.next().toString());
		}		
		pw.close();
		fw.close();
	}	
}
