package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.util.ILogger;
import sg.edu.nus.iss.shop.util.Logger;
 
public class DataTrackAdapter implements DataRecordAdapter
{

	private ILogger logger = Logger.getLog();
	private DataRecord dataRecord;
	private String dataSetName;
	
	/**
	 * Convert dirty data to tracked data
	 * @param dataSetName
	 * @param record
	 */
	public DataTrackAdapter(String dataSetName, DataRecord dirtyDataRecord)
	{
		this.dataSetName = dataSetName; 
		dataRecord = dirtyDataRecord;		
	}
	
	/**
	 * Recover Tracked data to dirty data  
	 * @param record
	 */
	public DataTrackAdapter(DataRecord trackedDataRecord)
	{
		dataRecord = trackedDataRecord;
		
		String[] dataValues = trackedDataRecord.toString().split(DaoConstant.SEPARATOR);
		
		this.dataSetName = dataValues[0];
	}
	
	public String getDataSetName()
	{
		return this.dataSetName;
	}
	
	public DataRecord getDirtyDataRecord() 
	{
		String recordString =dataRecord.toString();
		int pkIndx = recordString.indexOf("*#PK#*" + DaoConstant.SEPARATOR);
		
		logger.log("recordString:" + recordString);
		
		String dirtyData = recordString.substring(pkIndx+("*#PK#*" + DaoConstant.SEPARATOR).length());
		//System.out.println("dirtyData:" + dirtyData);
		logger.log("dirtyData:" + dirtyData);
		String pkValue = recordString.substring(0, pkIndx-1);
		//System.out.println("pkValue:" + pkValue);
		logger.log("pkValue:" + pkValue);
		
		String[] pkValues = pkValue.split(DaoConstant.SEPARATOR);
		
		StringBuilder sbPK = new StringBuilder();
		for(int i =1;i<pkValues.length;i++)
		{
			if(i >= (pkValues.length -i))
			{
				sbPK.append(pkValues[i]);
			}
			else
			{
				sbPK.append(pkValues[i]+DaoConstant.SEPARATOR);
			}
		}
		 
		DataRecord newRecord = new DataRecord(dirtyData);
		newRecord.setPk(sbPK.toString());
		//System.out.println("sbPK:" + sbPK.toString());
		logger.log("sbPK:" + sbPK.toString());
		return newRecord;
	}
	
	@Override
	public DataRecord getDataRecord() {
		
		StringBuilder newValue = new StringBuilder();
		newValue.append(dataSetName + DaoConstant.SEPARATOR );//append dataset name         0
		newValue.append(dataRecord.getPK()+ DaoConstant.SEPARATOR);//append dirty record PK 1
		newValue.append("*#PK#*"+ DaoConstant.SEPARATOR);//append PK separator              2
		newValue.append(dataRecord.toString());//append original dirty data                 3
		
		DataRecord newRecord = new DataRecord(newValue.toString());
		
		newRecord.setPk(dataSetName + DaoConstant.SEPARATOR + dataRecord.getPK());
		return newRecord;
	}

	@Override
	public Object getDataObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
