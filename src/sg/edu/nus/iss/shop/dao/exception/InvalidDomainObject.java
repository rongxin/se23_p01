package sg.edu.nus.iss.shop.dao.exception;

public class InvalidDomainObject extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidDomainObject(String message)
	{
		super("InvalidDomainObject");
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public int getErrorCode()
	{
		return 100;
	} 

}
