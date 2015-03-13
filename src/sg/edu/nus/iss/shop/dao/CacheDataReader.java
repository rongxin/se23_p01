package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.Iterator;

import sg.edu.nus.iss.shop.dao.adapter.DataTrackAdapter;

public class CacheDataReader extends DataCache
{
	public CacheDataReader()
	{
		if(dirtyData == null)
		{
			try 
			{
				dirtyData = reader.read(DIRTYDATASETNAME);
				if(dirtyData != null && dirtyData.size()>0)
				{
					//recover data from last crashed
					DataTrackAdapter adapter = null;
					System.out.println("recover dirtyData: " + dirtyData.size());
					Iterator<DataRecord> it =dirtyData.iterator();
					//for(DataRecord tracked : dirtyData)
					while(it.hasNext())
					{
						adapter =new DataTrackAdapter( it.next());
						super.getCachedData(adapter.getDataSetName()).add(adapter.getDirtyDataRecord());
						System.out.println("recover: " + super.getCachedData(adapter.getDataSetName()).size());
					}					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
