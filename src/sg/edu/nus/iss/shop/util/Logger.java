package sg.edu.nus.iss.shop.util;
 

public class Logger implements ILogger {
	
	private static ILogger logger = null;  
	private static ILogger consoleLogger = null;
	private static ILogger fileLogger = null;
	
	private Logger() 
	{ 
		consoleLogger = new ConsoleLogger();
		fileLogger = new FileLogger();
	}
	
	public static ILogger getLog()
	{
		if(fileLogger == null)
			fileLogger = new FileLogger();
		
		return fileLogger;
	}
	
	public static ILogger getLog(LoggerType type)
	{ 	
		if(type == LoggerType.CONSOLE_OUTPUT)
		{			 
			if(consoleLogger == null)
				consoleLogger = new ConsoleLogger();
			return consoleLogger;
		}
		else if(type == LoggerType.FILE_OUTPUT)
		{
			if(fileLogger == null)
				fileLogger = new FileLogger();
			return fileLogger;
		}		 
		else
		{	if(logger == null)
				logger = new Logger();
			return logger;
		}
			
	}
	
	public void log(String message)
	{
		consoleLogger.log(message);
		fileLogger.log(message);
	}
	
	public void error(String message)
	{
		consoleLogger.error(message);
		fileLogger.error(message);
	}
	
	public void warn(String message)
	{
		consoleLogger.warn(message);
		fileLogger.warn(message);	
	}
	
	public void info(String message)
	{
		consoleLogger.info(message);
		fileLogger.info(message);	
	}
	
	public void debug(String message)
	{
		consoleLogger.debug(message);
		fileLogger.debug(message);
	} 
	
	@Override
	public void log(Exception message) {
		// TODO Auto-generated method stub
		consoleLogger.log(message);
	}
}
