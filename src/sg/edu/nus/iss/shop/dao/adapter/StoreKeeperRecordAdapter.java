package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;

public class StoreKeeperRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	StoreKeeper dataObj;
	public StoreKeeperRecordAdapter(StoreKeeper keeper)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(keeper.getName());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(keeper.getPassword());
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(keeper.getName());
		
		dataObj = keeper;
	}
	
	public StoreKeeperRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 2)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing storekeeper");
		}
		else
		{
			dataObj = new StoreKeeper(dataValues[0],dataValues[1]);
		}
		 
		dataRecord = record;
		dataRecord.setPk(dataObj.getName());
	}
	
	@Override
	public DataRecord getDataRecord()
	{		
		return dataRecord;
	}
	
	@Override
	public Object getDataObject()
	{
		return dataObj;
	}	
}
