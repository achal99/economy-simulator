import java.util.ArrayList;

public class Restaurant extends Business{
	
	private ArrayList<Product> menulist;
	private ArrayList<Product> menu;

	
	public Restaurant(String nm, double amountmoney,int employees, int salaries) {
		super(nm,amountmoney,employees,salaries);
		menulist = new ArrayList<Product>();
		menu = new ArrayList<Product>();
		}
	
	
	public void sell(Finance f, String nameofpd) throws Exception {
		int index = productindex(nameofpd); //finds index in things of the product name

		try {
			if(f instanceof Person && index != -1) {
				double price = menulist.get(index).getPrice(); //gets the price of the product//checks to see if f is a person and this person has the object
				checkFunds(price,((Person) f).getCash()); //checks if funds are available
				((Person) f).setCash(((Person) f).getCash() - price); //deducts cash from Person f
				((Person)f).additempd(menulist.get(index));
				getBankbusiness().addbalance(price);
				this.menulist.remove(index);
			} else {
				System.out.println("Item does not exist.");
			}
			} catch (InsufficientFundsException e) {
				System.out.println("Insuficient cash funds for this transaction.");
				
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Item does not exist.");
			}
		}
	
	private void fillup20(Product p) {
		menu.add(p);
		for(int i = 0; i<20;i++){
			menulist.add(p);
		}
	}
	
	public void setupmenu(Product p, Product pr, Product prd) {
		fillup20(p);
		fillup20(pr);
		fillup20(prd);
	}
	
	private double showinventoryvalue() {
		double sum = 0.0;
		for (int i = 0;i<menulist.size();i++) {
			sum += menulist.get(i).getPrice();
		}
	return sum;
	}  //end of method
	
	private double showexpenditures() {
		double sum = 0.0;
		for (int i = 0;i<menulist.size();i++) {
			sum += menulist.get(i).getFprice();
		}
	return sum;
	}  //end of method
	
	protected int productindex(String nameofpd) {
		int index = -1;
		for(int i = 0; i < menulist.size();i++) {
			if(menulist.get(i).getName().equals(nameofpd)) {
				index = i;
				break;
			}//end of if statement
			
	}//end of for loop
		return index;
	}

	
	
	
	
	public void showFinances() {
		System.out.println("Name: " + getName());
		System.out.println("Bank account balance " + getBankbusiness().showbalance());
		System.out.println("Inventory value :" + showinventoryvalue());
		System.out.println("Total expenditures : " +  showexpenditures());
		System.out.println("Expected Profit : " + (showinventoryvalue()-showexpenditures()) ); //returns profit if everything was sold 
	}
	
	public void showAssets() {
		String assets = "Assets of " + getName() + " ";
		for(int i = 0; i<menu.size();i++) {
			assets = assets + menu.get(i).getName()+ "($" + menu.get(i).getPrice()+") ";
		}
		System.out.println(assets);
	}
	
	public void inflateallproducts(){ //inflates every price by -1 -- 2% (Simulates real world inflation)
		for (int i =0;i<menulist.size();i++){
			menulist.get(i).inflatePrice();
		}
	}
	
	
	
	
}
