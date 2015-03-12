package sg.edu.nus.iss.shop.dao;

import java.io.File;

public class DataRespository 
{
	protected void setupRepository()
	{
		File dataFolder = new File(DaoConstant.RELATIVE_FOLDER);
		if(!dataFolder.exists())
		{
			dataFolder.mkdir();
		}
	}
}
