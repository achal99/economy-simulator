
public interface Finance {
	
	public void sell(Finance f, String nameofpd) throws Exception ; //sells product to another business/person
	
	public void showFinances(); //shows all information pertaining to finances such as money in all accounts including cash
	
	public void showAssets(); //shows all the assets of Person/Business
	
	public void dumpitem(String nm);

	void additem(String nm, double price);

}
