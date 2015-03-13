package sg.edu.nus.iss.shop.dao;

public class DataRecord 
{
	private boolean isPersistent = false;
	private String PK;
	private String rawText;
	public DataRecord()
	{}
	
	public DataRecord(String rawText, boolean isPersistent)
	{
		this.rawText = rawText;
		this.isPersistent = isPersistent;
	}
	
	public DataRecord(String rawText)
	{
		this(rawText, false);
	}
	public String toString()
	{
		return this.rawText;
	}
	
	public String getPK()
	{
		return this.PK;
	}
	
	public void setPk(String PK)
	{
		this.PK = PK;
	}
	
	public boolean getIsPersistent()
	{
		return this.isPersistent;
	}
	
	public void setIsPersistent(boolean isPersistent)
	{
		this.isPersistent = isPersistent;
	}
	
}
