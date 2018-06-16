import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class SetupReader {

	private static ArrayList<Person> personlist = new ArrayList<Person>();
	private static ArrayList<Business> businesslist = new ArrayList<Business>();
	private static LocalDate timekeeper;
	private static int timecounter = -1; // set at negative one, so that the
											// first calling of setupersons
											// starts it at 0.

	public static void setupworld() {
		SetupReader.starTime();
		Person fred = new Person("FRED", 1000, 1000, 2000);
		SetupReader.addtopersons(fred);
		fred.additem("DOG", 200);
		Person bob = new Person("BOB", 2000, 1000, 2000);
		SetupReader.addtopersons(bob);
		bob.additem("CAT", 150);
		Person andrew = new Person("ANDREW", 1600, 1000, 5000);
		SetupReader.addtopersons(andrew);
		andrew.additem("BICYCLE", 250);
		Person george = new Person("GEORGE", 1020, 1000, 80000);
		SetupReader.addtopersons(george);
		george.additem("COMPUTER", 3000);
		Person michael = new Person("MICHAEL", 1500, 1000, 90000);
		Restaurant finedining = new Restaurant("FINE DINING", 500000, 50, 3000);
		finedining.setupmenu(new Product("LOBSTER", 200, 120), new Product("CAVIAR", 100, 50),
				new Product("STEAK", 100, 40));
		SetupReader.addtobusiness(finedining);
		Restaurant fastfood = new Restaurant("FAST FOOD", 200000, 200, 10);
		fastfood.setupmenu(new Product("PIZZA", 10, 2), new Product("BURGERS", 20, 10), new Product("SODA", 5, 2));
		SetupReader.addtobusiness(fastfood);
		ClothingStore designer = new ClothingStore("DESIGNER", 200000, 20, 2000);
		designer.setupmenu(new Product("GUCCI", 100, 20), new Product("VERSACE", 200, 140),
				new Product("ARMANI", 100, 20));
		SetupReader.addtobusiness(designer);
	}

	private static void addallsalaries() {
		for (int i = 0; i < personlist.size(); i++) {
			personlist.get(i).addsalary();
		}
	}

	private static void checkTime() {
		if (timecounter % 2 == 0) {
			SetupReader.addallsalaries();
			System.out.println("Everyone recieved their monthly salaries!");
		} if (timecounter %12 == 0) {
			for(int i = 0;i<businesslist.size();i++){
				businesslist.get(i).inflateallproducts();
			}//end of for loops
			System.out.println("Changes to price have taken place due to inflation.");
		} //end of if statement
	}

	public static void starTime() {
		timekeeper = LocalDate.parse("2016-07-11");
	}

	private static LocalDate changeTime() {
		return timekeeper.plusWeeks(2);
	}

	public static void addtopersons(Person p) {
		personlist.add(p);
	}

	public static void addtobusiness(Business b) {
		businesslist.add(b);
	}

	public static void setuppersons() throws Exception {
		timecounter++;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		boolean properanswer = true; // keeps asking until user gives a valid answer
		while (properanswer) {

			pw.println("In this economy, you may choose what transactions take place and what happens.");
			pw.println("Enter 'a' for a buy/sell transaction from a person, Enter 'b' to see financial stats ");
			pw.println("Enter 'c' to give/recieve money, Enter 'd' to withdraw/deposit from a bank account");
			pw.println("Enter 'e' to see the names of all people, Enter 'f' to see all businesses");
			pw.println("Enter 'g' to see assets of a person/business, Enter 'h' to buy from a business.");
			pw.print("Enter 'i' to see the monthly salary of a person.");
			pw.println(" Enter 'done' when you're finished playing");
			pw.println();
			pw.println("The date in this economy is " + timekeeper.toString());
			SetupReader.checkTime();

			String response = br.readLine();
			switch (response) {
			case "a":
				SetupReader.buysell();
				properanswer = false;
				break;
			case "b":
				SetupReader.financialstats();
				properanswer = false;
				break;
			case "c":
				SetupReader.givereceive();
				properanswer = false;
				break;
			case "d":
				SetupReader.withdrawdeposit();
				properanswer = false;
				break;
			case "e":
				SetupReader.showNames();
				properanswer = false;
				break;
			case "f":
				SetupReader.showbusinesses();
				properanswer = false;
				break;
			case "g":
				SetupReader.showassetsinfo();
				properanswer = false;
				break;
			case "i":
				SetupReader.showsalary();
				properanswer = false;
				break;
			case "h":
				SetupReader.buybusiness();
				properanswer = false;
				break;
			case "done":
				System.out.println("Game done.");
				properanswer = false;
				break;
			default:
				System.out.println("That was not an option.");
			}
		}
	} // end of method

	private static void buysell() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println("What is the name of the seller?");
		String seller = br.readLine().toUpperCase();
		int indexseller = SetupReader.findPersonindex(seller);
		pw.println("What is the name of the buyer?");
		String buyer = br.readLine().toUpperCase();
		int indexbuyer = SetupReader.findPersonindex(buyer);
		pw.println("What is the name of the product being sold?");
		String pdname = br.readLine().toUpperCase();

		if (indexbuyer == -1 || indexseller == -1) {
			System.out.println("One or more of these people do not exist.");
		} else {
			if (personlist.get(indexseller).productindex(pdname) != -1) {
				(personlist.get(indexseller)).sell(personlist.get(indexbuyer), pdname);
				System.out.println("Sale was succesful.");
			} else {
				System.out.println("Item does not exist.");
			}

		}
		timekeeper = changeTime();
		SetupReader.setuppersons();

		System.out.println("The date is " + timekeeper.toString());

	}

	private static int findPersonindex(String nm) {
		int index = -1;
		for (int i = 0; i < personlist.size(); i++) {
			if (nm.equals(personlist.get(i).getName())) {
				index = i;
				break;
			} // end of if statement
		} // end of for loop
		return index;
	}// end of method

	private static int findBusinessindex(String nm) {
		int index = -1;
		for (int i = 0; i < businesslist.size(); i++) {
			if (nm.equals(businesslist.get(i).getName())) {
				index = i;
				break;
			} // end of if statement
		} // end of for loop
		return index;
	}// end of method

	private static void financialstats() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println(
				"If you would like the financial stats for a person 'p', if you would like the financial stats for a business enter 'b'.");
		String response = br.readLine();
		if (response.equals("p")) {
			pw.println("What is name of the person you would like?");
			String person = br.readLine().toUpperCase();
			int indexperson = SetupReader.findPersonindex(person);
			if (indexperson == -1) {
				System.out.println("This is not a real person");
			} else {
				personlist.get(indexperson).showFinances();
			} // end of second if else statement
		} else if (response.equals("b")) {
			pw.println("What is the nameof the business you would like?");
			String business = br.readLine().toUpperCase();
			int indexbusiness = SetupReader.findBusinessindex(business);
			if (indexbusiness == -1) {
				System.out.println("This business does not exist");
			} else {
				businesslist.get(indexbusiness).showFinances();
			}
		}
		timekeeper = changeTime();
		SetupReader.setuppersons();
		System.out.println("The date is " + timekeeper.toString());
	}// end of method

	private static void givereceive() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		double amountgiven = 0;
		pw.println("What is the name of the donor?");
		String donor = br.readLine().toUpperCase();
		int indexdonor = SetupReader.findPersonindex(donor);
		pw.println("What is the name of the receiver?");
		String receiver = br.readLine().toUpperCase();
		int indexreceiver = SetupReader.findPersonindex(receiver);
		try {
			pw.println("What is the amount that will be given?");
			amountgiven = Double.parseDouble(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Sorry that was not a number");
		}

		if (indexdonor == -1 || indexreceiver == -1) {
			System.out.println("One or more of these peopele do not exist.");
		} else {
			personlist.get(indexdonor).give(personlist.get(indexreceiver), amountgiven);
		}
		timekeeper = changeTime();
		SetupReader.setuppersons();
		System.out.println("The date is " + timekeeper.toString());
	}

	private static void withdrawdeposit() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		double money = 0;

		pw.println("Who's bank account would you like to manage?");
		String name = br.readLine().toUpperCase();
		int indexname = SetupReader.findPersonindex(name);
		pw.println("How much would you like to deposit/withdraw?");
		try {
			money = Double.parseDouble(br.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Sorry, that was not a number.");
		}
		if (indexname != -1) { // checks if name actually exists
			pw.println("Enter 'w' to withdraw from savings or enter 'd' to deposit savings");
			String answer = br.readLine();
			if (answer.equals("w")) {
				personlist.get(indexname).withdrawsavings(money);
				System.out.println("$" + money + " was succesfully withdrawn.");
			} else if (answer.equals("d")) {
				personlist.get(indexname).depositsavings(money);
				System.out.println("$" + money + " was succesfully deposited");
			} else {
				System.out.println("That was not a given answer choice.");
			}
		} else {
			System.out.println("That person does not exist in this economy.");
		} // end of ifelse
		SetupReader.setuppersons();
		timekeeper = changeTime();
		System.out.println("The date is " + timekeeper.toString());
	}// end of method

	private static void showNames() throws Exception {
		System.out.println("Fred,Bob,Andrew,George,Michael");
		timekeeper = changeTime();
		SetupReader.setuppersons();
	}

	private static void showassetsinfo() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println(
				"If you would like the asset information for a person 'p', if you would like the asset information for a business enter 'b'.");
		String response = br.readLine();
		if (response.equals("p")) {
			pw.println("What is name of the person you would like?");
			String person = br.readLine().toUpperCase();
			int indexperson = SetupReader.findPersonindex(person);
			if (indexperson == -1) {
				System.out.println("This is not a real person");
			} else {
				personlist.get(indexperson).showAssets();
			} // end of second if else statement
		}
		if (response.equals("b")) {
			pw.println("What is the nameof the business you would like?");
			String business = br.readLine().toUpperCase();
			int indexbusiness = SetupReader.findBusinessindex(business);
			if (indexbusiness == -1) {
				System.out.println("This business does not exist");
			} else {
				businesslist.get(indexbusiness).showAssets();
			}
		}
		SetupReader.setuppersons();
		timekeeper = changeTime();
		System.out.println("The date is " + timekeeper.toString());

	}

	private static void buybusiness() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println("What is the name of the seller?");
		String seller = br.readLine().toUpperCase();
		int indexbusiness = SetupReader.findBusinessindex(seller);
		pw.println("What is the name of the buyer?");
		String buyer = br.readLine().toUpperCase();
		int indexbuyer = SetupReader.findPersonindex(buyer);
		pw.println("What is the name of the product being sold?");
		String pdname = br.readLine().toUpperCase();

		if (indexbuyer == -1 || indexbusiness == -1) {
			System.out.println("One or more of these people do not exist.");
		} else {
			if (businesslist.get(indexbusiness).productindex(pdname) != -1) {
				(businesslist.get(indexbusiness)).sell(personlist.get(indexbuyer), pdname);
				System.out.println("Sale was succesful.");
			} else {
				System.out.println("Item does not exist.");
			}

			System.out.println();
		}
		timekeeper = changeTime();
		SetupReader.setuppersons();

		System.out.println("The date is " + timekeeper.toString());

	}

	private static void showbusinesses() throws Exception {
		System.out.println("Fine Dining, Fast Food, Designer");
		timekeeper = changeTime();
		SetupReader.setuppersons();
	}

	private static void showsalary() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println("What is name of the person you would like to see the monthly salary of?");
		String person = br.readLine().toUpperCase();
		int indexperson = SetupReader.findPersonindex(person);
		if (indexperson == -1) {
			System.out.println("This is not a real person");
		} else {
			personlist.get(indexperson).showsalary();
		} // end of second if else statement
		timekeeper = changeTime();
		SetupReader.setuppersons();
	}

}
// end of class
