package sg.edu.nus.iss.shop.util;

public interface ILogger {
	
	public void log(String message);
	
	public void log(Exception message);
	
	public void error(String message);
	
	public void warn(String message);
	
	public void info(String message);
	
	public void debug(String message);	

}
