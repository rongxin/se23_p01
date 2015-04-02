package sg.edu.nus.iss.shop.dao.exception;

public class InvalidDataFormat extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;
	
	public InvalidDataFormat(String message)
	{
		super("InvalidDataFormat");
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public int getErrorCode()
	{
		return 101;
	} 

}