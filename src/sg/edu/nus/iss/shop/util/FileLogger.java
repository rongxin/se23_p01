package sg.edu.nus.iss.shop.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.iss.shop.dao.LoggerWriter;

public class FileLogger extends LoggerImpl{

	private LoggerWriter writer = null;
	
	FileLogger()
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
	
	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		writer.writeLog(this.getLogMessage(message));
	}

	@Override
	public void log(Exception message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		writer.writeLog(this.getLogLevelMessage(error,message));
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		writer.writeLog(this.getLogLevelMessage(warn,message));
	}

	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		writer.writeLog(this.getLogLevelMessage(info,message));
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		writer.writeLog(this.getLogLevelMessage(debug,message));
	}

}
