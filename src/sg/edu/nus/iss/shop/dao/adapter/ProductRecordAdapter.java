package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DaoConstant;
import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.model.domain.Product;

public class ProductRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	Product dataObj;
	public ProductRecordAdapter(Product prod)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(prod.getProductId());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getName());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getDescription());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getAvailableQuantity());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getPrice());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getBarcodeNumber());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getOrderThreshold());
		builder.append(DaoConstant.SEPARATOR);
		builder.append(prod.getOrderQuantity());
		dataRecord = new DataRecord(builder.toString());
		dataRecord.setPk(prod.getProductId());
		
		dataObj = prod;
	}
	
	public ProductRecordAdapter(DataRecord record) throws InvalidDataFormat
	{
		String[] dataValues = record.toString().split(DaoConstant.SEPARATOR);
		if(dataValues.length != 8)
		{
			throw new InvalidDataFormat("Data format incorrect for parsing product");
		}
		else
		{
		  int availableQuantity;
		  availableQuantity = Integer.parseInt(dataValues[3]);
		  double price; 
		  price = Double.parseDouble(dataValues[4]);
		  int orderThreshold;
		  orderThreshold = Integer.parseInt(dataValues[6]);
		  int orderQuantity;
		  orderQuantity = Integer.parseInt(dataValues[7]);
			dataObj = new Product(dataValues[0],dataValues[1],dataValues[2],
								availableQuantity, price,dataValues[5],orderThreshold,
								orderQuantity);
		}
		 
		dataRecord = record;
		dataRecord.setPk(dataObj.getProductId());
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