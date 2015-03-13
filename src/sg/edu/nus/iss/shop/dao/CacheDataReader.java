package sg.edu.nus.iss.shop.dao;

import java.io.IOException;

public class CacheDataReader extends DataCache
{
	public CacheDataReader()
	{
		if(dirtyData == null)
		{
			try 
			{
				dirtyData = reader.read(DIRTYDATASETNAME);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
