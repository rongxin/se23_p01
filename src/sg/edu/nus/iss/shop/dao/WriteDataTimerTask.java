package sg.edu.nus.iss.shop.dao;

import java.util.TimerTask;

public class WriteDataTimerTask extends TimerTask{

	private Callback caller = null;
	public WriteDataTimerTask(Callback caller)
	{
		this.caller = caller;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(caller== null)
			return;
		caller.callback();
				
	}

}
