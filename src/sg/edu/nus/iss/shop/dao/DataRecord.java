package sg.edu.nus.iss.shop.dao;

public class DataRecord 
{
	private String PK;
	private String rawText;
	public DataRecord()
	{}
	
	public DataRecord(String rawText)
	{
		this.rawText = rawText;
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
	
}
