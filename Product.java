
public class Product {
	private double saleprice;
	private double factoryprice; //price the business bought 
	
	private String name;
	
	public Product(String nm, double pr) {
		name = nm;
		saleprice = pr;
		factoryprice = 0.0;
	}
	
	public Product(String nm, double pr, double pricef){
		name = nm;
		saleprice = pr;
		factoryprice = pricef;
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return saleprice;
	}
	
	public double getFprice() {
		return factoryprice;
	}
	
	public void inflatePrice() {
		saleprice = saleprice * (0.12*Math.random()+0.9);  //generates inflation/deflation
	}

}
