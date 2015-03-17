package sg.edu.nus.iss.shop.dao.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.TransactionDetail;
import sg.edu.nus.iss.shop.model.nondomain.TransactionRecord;

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
		 
	}
	
	public TransactionAdapter(List<DataRecord> records) throws InvalidDataFormat, ParseException
	{
		DataRecord record = null;
		Iterator<DataRecord> it = records.iterator();
		
		while(it.hasNext())
		{
			record = it.next();
			transList.add(convert(record)); 
		}	 
	}
	
	
	private TransactionRecord convert(DataRecord record) throws InvalidDataFormat, ParseException
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 5)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing transaction");
		}
		
		TransactionRecord transRecord = new  TransactionRecord();	 
		
		transRecord.setId(Integer.parseInt(dataValues[0]));
		transRecord.setProductId(dataValues[1]);
		transRecord.setCustomerId(dataValues[2]);
		transRecord.setQuantity(Integer.parseInt(dataValues[3]));
		transRecord.setTransactionDate(dft.parse(dataValues[4]));
		
		return transRecord;
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