package sg.edu.nus.iss.shop.util;

public class ConsoleLogger extends LoggerImpl {
	
	
	public void log(String message)
	{
		System.out.println(this.getLogMessage(message));
	}

	@Override
	public void log(Exception message) {
		// TODO Auto-generated method stub
		message.printStackTrace();
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		System.out.println(this.getLogLevelMessage(error,message));
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		System.out.println(this.getLogLevelMessage(warn,message));
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		System.out.println(this.getLogLevelMessage(info,message));
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		System.out.println(this.getLogLevelMessage(debug,message));
	}

}
