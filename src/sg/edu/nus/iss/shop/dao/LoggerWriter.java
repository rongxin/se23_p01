package sg.edu.nus.iss.shop.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LoggerWriter extends DataRespository{
	
	
	private PrintWriter pw;
	public LoggerWriter(String loggerName) throws IOException
	{
		super.setupRepository();
		
		Path filePath = Paths.get(DaoConstant.RELATIVE_FOLDER, loggerName+DaoConstant.EXT_LOG);
		FileWriter fw = new FileWriter(filePath.toString(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		pw = new PrintWriter(bw);  
	}
	
	public void writeLog(String message)
	{		 
	    pw.println(message);	  
	}
	
	public void releaseLoggerWriter()
	{
		if(pw != null )
		{
			pw.flush();
			pw.close();
		}
	}

}
