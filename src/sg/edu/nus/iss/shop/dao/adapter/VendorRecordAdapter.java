package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class VendorRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	Vendor dataObj;
	public VendorRecordAdapter(Vendor vendor)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(vendor.getName());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(vendor.getDescription());
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(vendor.getName());
		
		dataObj = vendor;
	}
	
	public VendorRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 2)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing");
		}
		else
		{
			dataObj = new Vendor(dataValues[0],dataValues[1]);
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
