import java.util.ArrayList;

public class Person implements Finance {
	private String name;
	private double cash;
	private ArrayList<Product> things;
	private BankAccount bankacc;
	private double monthlysalary;
	
	public void addsalary() {
		bankacc.addbalance(monthlysalary);
	}
	
	public Person(String nm, double csh, double savbankbalance, double monthsal) {
		name = nm;
		cash = csh;
		bankacc = new SavingsAccount(savbankbalance);
		things = new ArrayList<Product>();
		monthlysalary = monthsal;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	
	public int productindex (String name) { //returns the index of the product by name 
		int index = -1;
		for (int i = 0;i < things.size();i++) {
			if (name.equals(things.get(i).getName())) { //finds the first instance of the product name in the arraylist
				index = i;
				break;
			} //end of for loop
		
		}	return index;
	}// end of method
	
	/*public static boolean checkProduct(int index) {
		if (index > 0) {
			return true;
		} else {
			return false;
		}
	}
	*/



	public void sell(Finance f, String nameofpd) throws Exception {
		int index = productindex(nameofpd); //finds index in things of the product name

		try {
			if(f instanceof Person && index != -1) {
				double price = things.get(index).getPrice(); //gets the price of the product//checks to see if f is a person and this person has the object
				checkFunds(price,((Person) f).cash); //checks if funds are available
				((Person) f).cash = ((Person) f).cash - price; //deducts cash from Person f
				(((Person) f).things).add(things.get(index)); //adds product to Person f's inventory
				this.cash += price;	
				this.things.remove(index);
			} else {
				System.out.println("Item does not exist.");
			}
			} catch (InsufficientFundsException e) {
				System.out.println("Insuficient cash funds for this transaction.");
				
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Item does not exist.");
			}
		}
//maybe we can add a buy method LATER 
	
	public void give(Finance f, double moneygiven) throws Exception {
		try {
			checkFunds(moneygiven,this.cash);
			if(f instanceof Person) {
				((Person) f).cash += moneygiven;
				this.cash -= moneygiven;
			}
		} catch (InsufficientFundsException e) {
			System.out.println("Insufficient cash funds for this transaction.");
		}
		
		
		
	}


	public void showFinances() {
		System.out.println("Name: " + name );
		System.out.println("Cash: " + cash);
		System.out.println("Bank Account balance: " + bankacc.showbalance());
	}


	public void showAssets()  { //shows all assets
		String assets = "Assets of " + name + ": " ;
		 if(things.size()==0) {
			 System.out.println("No Assets currently");
		 } else {
			for (int i = 0; i < things.size(); i++) {
				assets += things.get(i).getName() + " , ";
				}
			System.out.println(assets);
		}//end of if else 
	

	}

	private void checkFunds(double amount, double cash) throws InsufficientFundsException { //throws 
		if(amount > cash) {
			throw new InsufficientFundsException();
	}
	}

	public void depositsavings(double amount) throws Exception {
		try {
			checkFunds(amount,cash);
			bankacc.deposit(amount);
			cash -= amount;
		} catch (InsufficientFundsException e) {
			System.out.println("Insufficient funds for this deposit");
		}
	}
	
	public void withdrawsavings(double amount) throws Exception {
		try {
			checkFunds(amount,bankacc.showbalance()); //checks to see if enough funds available
			bankacc.withdraw(amount);
			cash += amount;
		} catch (InsufficientFundsException e) { //catches exception
			System.out.println("Insufficient funds for this withdrawal");
		}
				
	}




	public void additem(String nm, double price) { //creates new product and then adds to arraylist things MAY REMOVE IF BUSINESS EXISTS
		Product p = new Product(nm,price);
		things.add(p);	
	}
	
	public void additempd(Product pd) {
		things.add(pd);
	}




	public void dumpitem(String nm) { //uses name to find product and then remove MAY REMOVE IF BUSINESS EXISTS
		int index = productindex(nm);
		things.remove(index);	
	}
	
	public void showsalary() {
		System.out.println("$"+monthlysalary);
	}

	
}
