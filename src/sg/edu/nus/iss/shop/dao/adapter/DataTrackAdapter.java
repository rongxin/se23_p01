package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
 
public class DataTrackAdapter implements DataRecordAdapter
{

	DataRecord dataRecord;
	String dataSetName;
	public DataTrackAdapter(String dataSetName, DataRecord record)
	{
		this.dataSetName = dataSetName; 
		dataRecord = record;		
	}
	
	@Override
	public DataRecord getDataRecord() {
		
		DataRecord newRecord = new DataRecord(dataSetName + DaoConstant.SEPARATOR + dataRecord.toString());
		
		newRecord.setPk(dataSetName + DaoConstant.SEPARATOR + dataRecord.getPK());
		return newRecord;
	}

	@Override
	public Object getDataObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
