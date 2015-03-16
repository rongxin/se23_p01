package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;

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
			int percentage = Integer.parseInt(dataValues[2]);
			
		   if(Discount.APPLICABLETOALL.equals(dataValues[5]))
		   {
			   dataObj = new PublicDiscount(dataValues[0],dataValues[1],percentage,
						dataValues[3], dataValues[4]);
		   }
		   else  if(Discount.APPLICABLETOMEMBER.equals(dataValues[5]))
		   {
			   if(Discount.FIRST_PURCHASE_CODE.equals(dataValues[0]))
			   {
				   dataObj = new FirstPurchaseDiscount(dataValues[0],dataValues[1],percentage);
			   }
			   else
			   {
				   dataObj = new SubsequentDiscount(dataValues[0],dataValues[1],percentage);
			   }
		   } 
		} 
		
		dataRecord = record;
		if(dataObj != null)
		{
			dataRecord.setPk(dataObj.getDiscountCode());
		}
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