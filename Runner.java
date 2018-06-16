
public class Runner {

	public static void main(String[] args) throws Exception {
		System.out.println("In this world, there are 5 people named : Fred, Bob, Andrew, George, Michael");
		System.out.println("There are 3 businesses : Fast Food, Fine Dining, and Designer");
		System.out.println("You can find out more information about these entities such as their bank account balance and salary with the commands");
		System.out.println("Every turn, 2 weeks will pass. Every month, everyone will receive a salary. Every year inflation will take place.");
		System.out.println();
		System.out.println();
		SetupReader.setupworld();
		SetupReader.setuppersons();
		
	
	}
}
