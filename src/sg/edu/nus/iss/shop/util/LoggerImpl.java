package sg.edu.nus.iss.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerImpl implements ILogger{

	private SimpleDateFormat logDateTime = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	
	private String error ="Error:";
	private String warn ="Warn:";
	private String info ="Info:";
	private String debug ="Debug:";
	
	protected LoggerImpl()
	{}
	protected String getLogMessage(String message)
	{
		return logDateTime.format(new Date()) + "    " + message;
	}
	 
	protected String getErrorMessage(String message)
	{
		return getLogLevelMessage(error,message);
	}
	
	protected String getWarnMessage(String message)
	{
		return getLogLevelMessage(warn,message);
	}
	
	protected String getInfoMessage(String message)
	{
		return getLogLevelMessage(info,message);
	}
	
	protected String getDebugMessage(String message)
	{
		return getLogLevelMessage(debug,message);
	}
	
	
	private String getLogLevelMessage(String level, String message)
	{
		return logDateTime.format(new Date()) + " " + level + "    " + message;
	}
	
	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log(Exception message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		
	}

}
