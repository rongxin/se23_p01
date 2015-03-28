package sg.edu.nus.iss.shop.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.iss.shop.dao.LoggerWriter;

public class Logger {
	
	private static Logger logger = null;
	
	private LoggerWriter writer = null;
	private SimpleDateFormat logDateTime = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	
	private Logger()
	{
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String loggerName = "Log_"+ dft.format(new Date()) ;
		try {
			writer = new LoggerWriter(loggerName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					writer.releaseLoggerWriter();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	//DataWriter writer = new Data
	public static Logger getLog()
	{
		if(logger == null)
			logger = new Logger();
		
		return logger;
	}
	
	public void log(String message)
	{
		//String logRecord = logDateTime.format(new Date()) + ":" + message;		
		writer.writeLog( logDateTime.format(new Date()) + "    " + message);
	}
}
