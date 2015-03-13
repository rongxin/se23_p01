package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Category;

public class CategoryRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	Category dataObj;
	public CategoryRecordAdapter(Category category)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(category.getCode());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(category.getName());
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(category.getCode());
		
		dataObj = category;
	}
	
	public CategoryRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 2)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing");
		}
		else
		{
			dataObj = new Category(dataValues[0],dataValues[1]);
		}
		 
		dataRecord = record;
		dataRecord.setPk(dataObj.getCode());
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
