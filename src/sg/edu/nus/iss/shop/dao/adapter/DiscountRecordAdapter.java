package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Discount;

public class DiscountRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	Discount dataObj;
	public DiscountRecordAdapter(Discount discount)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(discount.getDiscountCode());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(discount.getDescription());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(discount.getStartDate());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(discount.getDiscountInDays());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(discount.getDiscountPercentage());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(discount.getApplicableToMember());
		 
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(discount.getDiscountCode());
		
		dataObj = discount;
	}
	
	public DiscountRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 6)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing discount");
		}
		else
		{
		   
		  int percentage;
		  percentage = Integer.parseInt(dataValues[2]);
			dataObj = new Discount(dataValues[0],dataValues[1],percentage,
					dataValues[3], dataValues[4],dataValues[5]);
		}
		 
		dataRecord = record;
		dataRecord.setPk(dataObj.getDiscountCode());
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