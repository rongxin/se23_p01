package sg.edu.nus.iss.shop.exception;

public class ApplicationGUIException extends Exception {
	
	private String displayMessage;
	
	public ApplicationGUIException(String displayMessage){
		super("ApplicationGUIException");
		this.displayMessage = displayMessage;
	}
	
	public String getDisplayMessage(){
		return this.displayMessage;
	}
}
