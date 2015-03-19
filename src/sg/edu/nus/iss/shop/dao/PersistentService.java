package sg.edu.nus.iss.shop.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.shop.dao.adapter.CategoryRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.DataRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.DiscountRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.MemberRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.ProductRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.StoreKeeperRecordAdapter;
import sg.edu.nus.iss.shop.dao.adapter.TransactionAdapter;
import sg.edu.nus.iss.shop.dao.adapter.VendorRecordAdapter;
import sg.edu.nus.iss.shop.dao.exception.InvalidDataFormat;
import sg.edu.nus.iss.shop.dao.exception.InvalidDomainObject;
import sg.edu.nus.iss.shop.model.domain.Category;
import sg.edu.nus.iss.shop.model.domain.Discount;
import sg.edu.nus.iss.shop.model.domain.Member;
import sg.edu.nus.iss.shop.model.domain.Product;
import sg.edu.nus.iss.shop.model.domain.StoreKeeper;
import sg.edu.nus.iss.shop.model.domain.Transaction;
import sg.edu.nus.iss.shop.model.domain.Vendor;

public class PersistentService
{
	private CacheDataReader dataReader;
	private CacheDataWriter dataWriter;
	private static PersistentService service;

	private boolean hasBuildPK4Category = false;
	private boolean hasBuildPK4Product = false;
	private boolean hasBuildPK4Member = false;
	private boolean hasBuildPK4StoreKeeper = false;
	private boolean hasBuildPK4Discount = false;

	private PersistentService()
	{
		dataReader = new CacheDataReader();
		dataWriter = new CacheDataWriter();

		//can be commented if client app call releaseService method
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					dataWriter.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public static PersistentService getService()
	{
		if(service == null)
			service = new PersistentService();
		return service;
	}

	public void saveRecord(Object recordObj) throws Exception
	{
		String dsName = recordObj.getClass().getSimpleName() + DaoConstant.DS_SUFFIX;
		DataRecordAdapter adapter = null;
		if(recordObj instanceof Category)
		{
			adapter = new CategoryRecordAdapter((Category)recordObj);
		}
		else if(recordObj instanceof StoreKeeper)
		{
			adapter = new StoreKeeperRecordAdapter((StoreKeeper)recordObj);
		}
		else if(recordObj instanceof Member)
		{
			adapter = new MemberRecordAdapter((Member)recordObj);
		}
		else if(recordObj instanceof Product)
		{
			adapter = new ProductRecordAdapter((Product)recordObj);
		}
		else if(recordObj instanceof Discount)
		{
			dsName = recordObj.getClass().getSuperclass().getSimpleName() + DaoConstant.DS_SUFFIX;
			adapter = new DiscountRecordAdapter((Discount)recordObj);
		}
		else if(recordObj instanceof Transaction)
		{			
			TransactionAdapter transAdapter = new TransactionAdapter((Transaction)recordObj);
			dataWriter.writer.writeInAppend(dsName, transAdapter.getDataRecords());
			return;
		}
		
		if(adapter != null)
		{
			dataWriter.writeRecord(dsName, adapter.getDataRecord());
		}
		else
			throw new InvalidDomainObject("Not support this object saving");
	}

	public <T> T retrieveObject(Class cls, String objectId) throws InvalidDomainObject, InvalidDataFormat, IOException
	{
		String dsName = cls.getSimpleName() + DaoConstant.DS_SUFFIX;
		if(isCategoryType(cls))
		{
			return (T) retrieveCategory(dsName, objectId);
		}
		else if(isProductType(cls))
		{
			return (T) retrieveProduct(dsName, objectId);
		}
		else if(isMemberType(cls))
		{
			return (T) retrieveMember(dsName, objectId);
		}
		else if(isStoreKeeperType(cls))
		{
			return (T) retrieveStoreKeeper(dsName, objectId);
		}
		else if(isDiscountType(cls))
		{
			return (T) retrieveDiscount(dsName, objectId);
		}
		else
			throw new InvalidDomainObject("Not support this type of data retrieval");
	} 
	 
	public <T> List<T> retrieveAll(Class cls) throws IOException, InvalidDataFormat, InvalidDomainObject
	{
		//if(cls.newInstance() instanceof Category)
		String dsName = cls.getSimpleName() + DaoConstant.DS_SUFFIX;
		if(isCategoryType(cls))
		{
			return (List<T>) retrieveAllCategories(dsName);
		}
		else if(isProductType(cls))
		{
			return (List<T>) retrieveAllProducts(dsName);
		}
		else if(isMemberType(cls))
		{
			return (List<T>) retrieveAllMembers(dsName);
		}
		else if(isStoreKeeperType(cls))
		{
			return (List<T>) retrieveAllStoreKeepers(dsName);
		}
		else if(isDiscountType(cls))
		{
			return (List<T>) retrieveAllDiscounts(dsName);
		}
		else if(isTransactionType(cls))
		{
			return (List<T>) retrieveAllTransactions(dsName);
		}		
		else
			throw new InvalidDomainObject("Not support this type of data retrieval");

	}

	public List<Vendor> retrieveVendors(Category category) throws Exception
	{
		String dsName = Vendor.class.getSimpleName() + DaoConstant.DS_SUFFIX + category.getCode();
		List<Vendor> objects = new ArrayList<Vendor>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dsName))
		{
			adapter = new VendorRecordAdapter(record);
			objects.add((Vendor)adapter.getDataObject());
		}
		return objects;
	}

	//	public void saveVendors(Category category) throws Exception
	//	{
	//		for(Vendor vendor:category.getVendorList())
	//		{
	//			saveVendor(vendor, category);
	//		}
	//	}

	public void saveVendor(Vendor vendor, Category category) throws IOException
	{
		if(vendor != null && category != null)
		{
			String dsName = Vendor.class.getSimpleName() + DaoConstant.DS_SUFFIX + category.getCode();
			DataRecordAdapter adapter = new VendorRecordAdapter(vendor);
			dataWriter.writeRecord(dsName, adapter.getDataRecord());
		}
	}

	private boolean isCategoryType(Class cls)
	{
		if (cls.getSimpleName().equals(Category.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}

	private boolean isProductType(Class cls)
	{
		if (cls.getSimpleName().equals(Product.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}

	private boolean isMemberType(Class cls)
	{
		if (cls.getSimpleName().equals(Member.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}

	private boolean isStoreKeeperType(Class cls)
	{
		if (cls.getSimpleName().equals(StoreKeeper.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}
	
	private boolean isDiscountType(Class cls)
	{
		if (cls.getSimpleName().equals(Discount.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}
	
	private boolean isTransactionType(Class cls)
	{
		if (cls.getSimpleName().equals(Transaction.class.getSimpleName()))
		{
			return true;
		}
		return false;
	}
	
	private void buildPK4CachedCategory(String dataSetName)
	{
		if(hasBuildPK4Category)
			return;

		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			try
			{
				new CategoryRecordAdapter(record);
			}
			catch (InvalidDataFormat e)
			{
				e.printStackTrace();
			}
		}
		hasBuildPK4Category = true;
	}

	private void buildPK4CachedProduct(String dataSetName)
	{
		if(hasBuildPK4Product)
			return;

		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			try
			{
				new ProductRecordAdapter(record);
			}
			catch (InvalidDataFormat e)
			{
				e.printStackTrace();
			}
		}
		hasBuildPK4Product = true;
	}

	private void buildPK4CachedMember(String dataSetName)
	{
		if(hasBuildPK4Member)
			return;

		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			try
			{
				new MemberRecordAdapter(record);
			}
			catch (InvalidDataFormat e)
			{
				e.printStackTrace();
			}
		}
		hasBuildPK4Member = true;
	}

	private void buildPK4CachedStoreKeeper(String dataSetName)
	{
		if(hasBuildPK4StoreKeeper)
			return;

		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			try
			{
				new StoreKeeperRecordAdapter(record);
			}
			catch (InvalidDataFormat e)
			{
				e.printStackTrace();
			}
		}
		hasBuildPK4StoreKeeper = true;
	}
	
	private void buildPK4CachedDiscount(String dataSetName)
	{
		if(hasBuildPK4Discount)
			return;

		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			try
			{
				new DiscountRecordAdapter(record);
			}
			catch (InvalidDataFormat e)
			{
				e.printStackTrace();
			}
		}
		hasBuildPK4Discount = true;
	}

	private List<Object> retrieveAllCategories(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			adapter = new CategoryRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}

	private List<Object> retrieveAllProducts(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			adapter = new ProductRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}

	private List<Object> retrieveAllMembers(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			adapter = new MemberRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}

	private List<Object> retrieveAllStoreKeepers(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			adapter = new StoreKeeperRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}
	
	private List<Object> retrieveAllDiscounts(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<Object> objects = new ArrayList<Object>();
		DataRecordAdapter adapter = null;
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			adapter = new DiscountRecordAdapter(record);
			objects.add(adapter.getDataObject());
		}
		return objects;
	}
	
	private List<Object> retrieveAllTransactions(String dataSetName) throws IOException, InvalidDataFormat
	{
		List<DataRecord> records = dataReader.reader.read(dataSetName);
		if(records == null)
			return null;

		TransactionAdapter adapter;
		try {
			adapter = new TransactionAdapter(records);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 
		return adapter.getTransactions();
	}
	

	private Object retrieveCategory(String dataSetName, String objectId) throws IOException, InvalidDataFormat
	{
		buildPK4CachedCategory(dataSetName);

		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new CategoryRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}

	private Object retrieveProduct(String dataSetName, String objectId) throws IOException, InvalidDataFormat
	{
		buildPK4CachedProduct(dataSetName);

		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new ProductRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}

	private Object retrieveMember(String dataSetName, String objectId) throws IOException, InvalidDataFormat
	{
		buildPK4CachedMember(dataSetName);

		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new MemberRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}

	private Object retrieveStoreKeeper(String dataSetName, String objectId) throws IOException, InvalidDataFormat
	{
		buildPK4CachedStoreKeeper(dataSetName);

		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new StoreKeeperRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}

	private Object retrieveDiscount(String dataSetName, String objectId) throws IOException, InvalidDataFormat
	{
		buildPK4CachedDiscount(dataSetName);

		DataRecordAdapter adapter = null;
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(objectId.equals(record.getPK()))
			{
				adapter = new DiscountRecordAdapter(record);
				return adapter.getDataObject();
			}
		}
		return null;
	}
	
	public Product retrieveProductByBarcode(String barcode) throws IOException, InvalidDataFormat
	{
		
		if(barcode == null || barcode.equals(""))
			return null;
		
		String dataSetName = Product.class.getSimpleName() + DaoConstant.DS_SUFFIX;
		buildPK4CachedProduct(dataSetName);
		
		//System.out.println("objectId:" + objectId);
		for(DataRecord record : dataReader.getCachedData(dataSetName))
		{
			//System.out.println("PK:" + record.getPK());
			if(barcode.equals(record.getUK()))
			{
				DataRecordAdapter adapter = new ProductRecordAdapter(record);
				return (Product)adapter.getDataObject();
			}
		}
		return null;
	}
	
	public void releaseService()
	{
		try
		{
			dataWriter.finalize();
			service = null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
