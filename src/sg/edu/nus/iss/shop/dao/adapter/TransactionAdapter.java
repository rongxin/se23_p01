package sg.edu.nus.iss.shop.dao.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.FirstPurchaseDiscount;
import sg.edu.nus.iss.shop.model.domain.PublicDiscount;
import sg.edu.nus.iss.shop.model.domain.SubsequentDiscount;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;

public class TransactionAdapter 
{
	private SimpleDateFormat dft = new SimpleDateFormat("yyyyy-mm-dd"); 

	private List<DataRecord> dataRecordList;
	private List<Object> transList;
	public TransactionAdapter(Transaction trans)
	{
		StringBuilder builder = new StringBuilder();
		
		int id = trans.getId();
		String date = dft.format(trans.getDate());
		String customerId = trans.getCustomer().getId();
		
		DataRecord dataRecord = null;
		for(TransactionDetail detail : trans.getTransactionDetails() )
		{
			builder.append(id);
			builder.append(DaoConstant.SEPARATOR);
			builder.append(detail.getProduct().getProductId());
			builder.append(DaoConstant.SEPARATOR);
			builder.append(customerId);
			builder.append(DaoConstant.SEPARATOR);
			builder.append(detail.getQuantity());
			builder.append(DaoConstant.SEPARATOR);
			builder.append(date);
			builder.append("\n");
			
			dataRecord = new DataRecord(builder.toString());
			dataRecordList.add(dataRecord);
		}
		
		transList.add(trans);
	}
	
	public TransactionAdapter(List<DataRecord> record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 6)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing discount");
		}
		else
		{			
			int percentage = Integer.parseInt(dataValues[2]);
			
//		   if(Discount.APPLICABLETOALL.equals(dataValues[5]))
//		   {
//			   dataObj = new PublicDiscount(dataValues[0],dataValues[1],percentage,
//						dataValues[3], dataValues[4]);
//		   }
//		   else  if(Discount.APPLICABLETOMEMBER.equals(dataValues[5]))
//		   {
//			   if(Discount.FIRST_PURCHASE_CODE.equals(dataValues[0]))
//			   {
//				   dataObj = new FirstPurchaseDiscount(dataValues[0],dataValues[1],percentage);
//			   }
//			   else
//			   {
//				   dataObj = new SubsequentDiscount(dataValues[0],dataValues[1],percentage);
//			   }
//		   } 
		} 
		
		//dataRecord = record;
//		if(dataObj != null)
//		{
//			dataRecord.setPk(dataObj.getDiscountCode());
//		}
	}
	
	 
	public List<DataRecord> getDataRecords()
	{		
		return dataRecordList;
	}
	
	 
	public List<Object> getTransactions()
	{
		return transList;
	}	
}