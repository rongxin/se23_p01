package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Member;

public class MemberRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	Member dataObj;
	public MemberRecordAdapter(Member member)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(member.getName());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(member.getId());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(member.getLoyalPoints());
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(member.getId());
		
		dataObj = member;
	}
	
	public MemberRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 3)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing");
		}
		else
		{
			dataObj = new Member(dataValues[1],dataValues[0]);
			
			try
			{
				int loyaltPoint  = Integer.parseInt(dataValues[2]);
				dataObj.setLoyalPoints(loyaltPoint);
			}catch(NumberFormatException ex){}
		}
		 
		dataRecord = record;
		dataRecord.setPk(dataObj.getId());
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
