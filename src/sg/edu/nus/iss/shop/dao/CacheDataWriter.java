package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

import sg.edu.nus.iss.shop.dao.adapter.DataTrackAdapter;
import sg.edu.nus.iss.shop.util.Logger;

public class CacheDataWriter extends DataCache
{
	private static boolean WRITING_DIRTY = false;
	private static List<String> dirtyDataSets = new ArrayList<String>();
	
	private static Timer timer = null;
	
	protected DataWriter writer = new DataWriter();
	
	public CacheDataWriter()
	{
		timer = new Timer(true);//is a daemon task

		WriteDataTimerTask task = new WriteDataTimerTask(new Callback()
		{
			@Override
			public void callback() 
			{
				try 
				{
					write();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					Logger.getLog().log("callback:" + e.getMessage());
				}
			}
		});
		timer.scheduleAtFixedRate(task, 2000, 30000);//2 second delay, 0.5 minute as interval
	}	
	
	public void writeRecord(String dataSetName,DataRecord record)
	{			
		rwl.writeLock().lock();
		if(!dirtyDataSets.contains(dataSetName))
		{
			dirtyDataSets.add(dataSetName);
		}
		
		super.getCachedData(dataSetName); 
		
		boolean update =false;
		//if(record.getIsPersistent())//fix bug 
		{ 
			//remove the old one
//			Iterator<DataRecord> it =super.getCachedData(dataSetName).iterator();
//			while(it.hasNext())
//			{
//				if(record.getPK().equals(it.next().getPK()))
//				{
//					it.remove();
//					break;
//				}
//			}
		
			int i =0;
			for(DataRecord rec :super.getCachedData(dataSetName))			 
			{
				if(record.getPK().equals(rec.getPK()))
				{
					update = true;
					super.getCachedData(dataSetName).set(i, record);
					break;
				}
				i++;
			}
		}
		
		//add the new one
		record.setIsPersistent(true);
		if(!update)
		{
			super.getCachedData(dataSetName).add(record);
		}
				 
		//System.out.println("cahcedData size:" + cahcedData.size() );
		if(dirtyData == null)
		{
			dirtyData = new ArrayList<DataRecord>();
		}
		
		//track dirty data record
		DataRecord dirtyRecord = (new DataTrackAdapter(dataSetName,record)).getDataRecord();
		Iterator<DataRecord> dirtyDataIt =dirtyData.iterator();
		while(dirtyDataIt.hasNext())
		{
			if(dirtyRecord.getPK().equals(dirtyDataIt.next().getPK()))
			{
				dirtyDataIt.remove();
				break;
			}
		}
		dirtyData.add(dirtyRecord);
		
		//persistent dirty record
		writeDirtyData();
		
		rwl.writeLock().unlock();
	}
	
	private void writeDirtyData()
	{
		if(WRITING_DIRTY)
			return;
		WRITING_DIRTY=true;
		try {
			writer.write(DIRTYDATASETNAME, dirtyData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLog().log("writeDirtyData:" + e.getMessage());
		}
		WRITING_DIRTY=false;
	}
	
	private void write() throws IOException
	{		
		rwl.writeLock().lock();
		Iterator<String> it = dirtyDataSets.iterator();
		String dsName = "";
		while(it.hasNext())
		{
			dsName = it.next(); 
			writer.write(dsName, super.getCachedData(dsName));
		}
				
		dirtyDataSets.clear();
		dirtyData.clear();
		writeDirtyData();
		rwl.writeLock().unlock();
	}
	
	@Override	
	public void finalize() throws Throwable
	 {
		//persistent data for all
		if (timer != null)
        {
            //timer.wait(2000);//wait for 2 seconds if task now finish
            timer.cancel();
            timer = null;
        }
		//System.out.println("finally persistent");
        write();
	 }
}
