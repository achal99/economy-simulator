
public class InsufficientFundsException extends Exception {
	
	public InsufficientFundsException() {
	}
	
	public InsufficientFundsException(String number) {
		super("Sorry you don't have this much money currently :" + number);
		
	}
	

}
