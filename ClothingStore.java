import java.util.ArrayList;

public class ClothingStore extends Business {
	
	private ArrayList<Product> clothinglist;
	private ArrayList<Product> catalog;
	
	

	public ClothingStore(String nm, double amountmoney, int employees, int salaries) {
		super(nm, amountmoney, employees, salaries);
		clothinglist = new ArrayList<Product>();
		catalog = new ArrayList<Product>();
	}
	
	private void fillup20(Product p) {
		catalog.add(p);
		for(int i = 0; i<20;i++){
			clothinglist.add(p);
		}
	}
	
	public void setupmenu(Product p, Product pr, Product prd) {
		fillup20(p);
		fillup20(pr);
		fillup20(prd);
	}

	private double showinventoryvalue() {
		double sum = 0.0;
		for (int i = 0;i<clothinglist.size();i++) {
			sum += clothinglist.get(i).getPrice();
		}
	return sum;
	}  //end of method
	
	private double showexpenditures() {
		double sum = 0.0;
		for (int i = 0;i<clothinglist.size();i++) {
			sum += clothinglist.get(i).getFprice();
		}
	return sum;
	}  //end of method
	
	
	
	
	public void showFinances() {
		System.out.println("Name: " + getName());
		System.out.println("Bank account balance " + getBankbusiness().showbalance());
		System.out.println("Inventory value :" + showinventoryvalue());
		System.out.println("Total expenditures : " +  showexpenditures());
		System.out.println("Expected Profit : " + (showinventoryvalue()-showexpenditures()) ); //returns profit if everything was sold 
	}
	
	public void showAssets() {
		String assets = "Assets of " + getName() + " ";
		for(int i = 0; i<catalog.size();i++) {
			assets = assets + catalog.get(i).getName()+ "($" + catalog.get(i).getPrice()+") ";
		}
		System.out.println(assets);
	}
	
	public void sell(Finance f, String nameofpd) throws Exception { //sells product
		int index = productindex(nameofpd); //finds index in things of the product name

		try {
			if(f instanceof Person && index != -1) {
				double price = clothinglist.get(index).getPrice(); //gets the price of the product//checks to see if f is a person and this person has the object
				checkFunds(price,((Person) f).getCash()); //checks if funds are available
				((Person) f).setCash(((Person) f).getCash() - price); //deducts cash from Person f
				((Person)f).additempd(clothinglist.get(index));
				getBankbusiness().addbalance(price);
				this.clothinglist.remove(index);
			} else {
				System.out.println("Item does not exist.");
			}
			} catch (InsufficientFundsException e) {
				System.out.println("Insuficient cash funds for this transaction.");
				
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Item does not exist.");
			}
		}
	
	public void inflateallproducts(){ //inflates every price by -1 -- 2% (Simulates real world inflation)
		for (int i =0;i<clothinglist.size();i++){
			clothinglist.get(i).inflatePrice();
		}
	}
	

}
