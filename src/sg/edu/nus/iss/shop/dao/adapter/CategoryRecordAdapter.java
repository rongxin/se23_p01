package sg.edu.nus.iss.shop.dao.adapter;

import sg.edu.nus.iss.shop.dao.DataRecord;
import sg.edu.nus.iss.shop.model.domain.Category;

public class CategoryRecordAdapter implements DataRecordAdapter
{
	DataRecord dataRecord;
	public CategoryRecordAdapter(Category category)
	{
		//category.
	}
	
	public DataRecord getDataRecord()
	{
		dataRecord = new DataRecord();
		return dataRecord;
	}
	
}
