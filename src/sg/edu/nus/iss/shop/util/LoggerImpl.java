package sg.edu.nus.iss.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerImpl implements ILogger{

	private SimpleDateFormat logDateTime = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	
	protected String error ="Error:";
	protected String warn ="Warn:";
	protected String info ="Info:";
	protected String debug ="Debug:";
	
	
	protected String getLogMessage(String message)
	{
		return logDateTime.format(new Date()) + "    " + message;
	}
	
	protected String getLogLevelMessage(String level, String message)
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
