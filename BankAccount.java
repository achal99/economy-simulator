
public class BankAccount {
	
	private double balance;
	
	private static final double interestrate = 0.1* Math.random(); 
	
	public BankAccount(double initialbalance) {
		balance = initialbalance;
	}
	
	public void deposit(double amount) {
		balance += amount;
		
	}
	
	public void withdraw(double amount) throws Exception { //create exception that checks if you don't have money 
		try {
			checkFunds(amount);
			balance -= amount;
		} catch(InsufficientFundsException e) {
			System.out.println("Sorry you do not have enough money in your account to withdraw $" + amount );
		}
	}//end of method
	
	public double showbalance() {
		return balance;
	}
	
	public void addbalance(double amount) {
		balance += amount;
	}
	
	private void checkFunds(double amountmoney) throws InsufficientFundsException {
		if(amountmoney > this.balance){
			throw new InsufficientFundsException();
		}
	}
	
	
	


	

}
