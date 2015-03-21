package sg.edu.nus.iss.shop.exception;

public class TransactionWrongQuantityException extends Exception {
	private static final long serialVersionUID = 1L;
	private String displayMessage;
	
	public TransactionWrongQuantityException(String displayMessage){
		super("ApplicationGUIException");
		this.displayMessage = displayMessage;
	}
	
	public String getDisplayMessage(){
		return this.displayMessage;
	}
}
