import java.util.ArrayList;

public class Business implements Finance {
	private String name;
	private BankAccount bankbusiness;
	private ArrayList<Product> inventory;
	private ArrayList<Product> menu;
	private int numemployees;
	private double averageemployeesalary;
	
	public Business (String nm, double amountmoney,int employees, int salaries) {
		setName(nm);
		setBankbusiness(new SavingsAccount(amountmoney));
		inventory = new ArrayList<Product>();
		numemployees = employees;
		averageemployeesalary=salaries;
	}

	public void sell(Finance f, String nameofpd) throws Exception {
		int index = productindex(nameofpd); //finds index in things of the product name

		try {
			if(f instanceof Person && index != -1) {
				double price = inventory.get(index).getPrice(); //gets the price of the product//checks to see if f is a person and this person has the object
				checkFunds(price,((Person) f).getCash()); //checks if funds are available
				((Person) f).setCash(((Person) f).getCash() - price); //deducts cash from Person f
				((Person)f).additempd(inventory.get(index));
				getBankbusiness().addbalance(price);
				this.inventory.remove(index);
			} else {
				System.out.println("Item does not exist.");
			}
			} catch (InsufficientFundsException e) {
				System.out.println("Insuficient cash funds for this transaction.");
				
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Item does not exist.");
			}
		}
	protected int productindex(String nameofpd) {
		int index = -1;
		for(int i = 0; i < inventory.size();i++) {
			if(inventory.get(i).getName().equals(nameofpd)) {
				index = i;
				break;
			}//end of if statement
			
	}//end of for loop
		return index;
	}


	public void showFinances() {
		System.out.println("Name: " + getName() );
		System.out.println("Bank Account balance: " + getBankbusiness().showbalance());
	}

	public void showAssets() {
		String assets = "Assets of " + getName() + ": " ;
		 if(inventory.size()==0) {
			 System.out.println("No Assets currently");
		 } else {
			for (int i = 0; i < inventory.size(); i++) {
				assets += inventory.get(i).getName() + " , ";
				}
			System.out.println(assets);
		}//end of if else 
		
	}


	@Override
	public void dumpitem(String nm) {
		int index = productindex(nm);
		inventory.remove(index);	
		
	}


	@Override
	public void additem(String nm, double price) {
		// TODO Auto-generated method stub
		
	}
	protected void checkFunds(double amount, double cash) throws InsufficientFundsException { //throws 
		if(amount > cash) {
			throw new InsufficientFundsException();
	}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BankAccount getBankbusiness() {
		return bankbusiness;
	}

	public void setBankbusiness(BankAccount bankbusiness) {
		this.bankbusiness = bankbusiness;
	}
	

	public void inflateallproducts(){ //inflates every price by -1 -- 2% (Simulates real world inflation)
		for (int i =0;i<inventory.size();i++){
			inventory.get(i).inflatePrice();
		}
	}

	
}
