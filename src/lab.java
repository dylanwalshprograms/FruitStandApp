import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class lab {
	
	private static Scanner scnr;
	private static Map<String, Double> items = new TreeMap<>();
	private static ArrayList<String> orderNames = new ArrayList<>();
	private static ArrayList<Double> orderPrices = new ArrayList<>();
	public static String itemName;
	public static int i;
	

	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		boolean keepgoing;
		fillItemsMap();
		System.out.println("Welcome to Dylan's Market!");
		System.out.println();
		do {
			printMenu();
			getItem();
			keepgoing = getYesOrNo(scnr, "Would you like to order anything else (y/n)?");
		}while(keepgoing);
		System.out.println();
		printOrder();
		System.out.printf("Average price per item in order was $%.2f.\n", getAvgPrice(orderPrices));
		System.out.println("The most expensive item you ordered was a " + orderNames.get(getHighestPriceIndex(orderPrices)) + ".");
		System.out.println("The most expensive item you ordered was a " + orderNames.get(getLowestPriceIndex(orderPrices)) + ".");
		scnr.close();
	}
	
	private static void fillItemsMap() {
		items.put("apple", .99);
		items.put("banana", .59);
		items.put("cauliflower", 1.59);
		items.put("dragonfruit", 2.19);
		items.put("elderberry", 1.79);
		items.put("figs", 2.09);
		items.put("grapefruit", 1.99);
		items.put("honeydew", 3.49);
	}
	
	private static void printMenu() {
		System.out.printf("%-20s %-20s", "Item", "Price" );
		System.out.println();
		System.out.println("===========================");
		
		for (Map.Entry<String, Double> entry : items.entrySet()) {
			System.out.printf("%-20s %-20s\n",entry.getKey(), "$" + entry.getValue());
		}
		System.out.println();
	}
	private static void getItem() {
		System.out.println("What item would you like to order? ");
		itemName = scnr.nextLine();
		if (!items.containsKey(itemName)) {
			System.out.println("That item is not on the menu. Try again.");
			getItem();
		}
		else {
			System.out.println("Adding " + itemName + " to cart at $" + items.get(itemName));
			orderNames.add(itemName);
			orderPrices.add(items.get(itemName));
		}
	}
	
	public static boolean getYesOrNo(Scanner scnr, String prompt) { //getYesOrNo from day 6 validation examples
		System.out.print(prompt);
		String input = scnr.nextLine().toLowerCase().trim(); //added a trim in case the user accidentally puts a space before their answer
		boolean isValid = input.equals("yes") || input.equals("y")|| input.equals("no")|| input.equals("n");
		while (!isValid) { // keep looping as long as they enter something invalid
			System.out.println("Oops. Please enter yes or no.");
			System.out.print(prompt);
			input = scnr.nextLine().toLowerCase();
			isValid = input.equals("yes") || input.equals("y")|| input.equals("no")|| input.equals("n");
		}
		return input.startsWith("y");
	}
	public static void printOrder() {
		System.out.println("Thanks for your order!");
		System.out.println("Heres what you got:");
		for (int i = 0; i < orderNames.size(); i++) {
			System.out.printf("%-20s %-20s\n", orderNames.get(i), orderPrices.get(i));
		}
	}
	
	public static double getAvgPrice(ArrayList<Double> prices) {
		double sum = 0;
		for (int i = 0; i < prices.size(); i++) {
			sum += prices.get(i);
		}
		return sum / prices.size();
	}
	
	public static double getHighestPrice(ArrayList<Double> prices) {
		return Collections.max(prices);
	}
	
	public static double getLowestPrice(ArrayList<Double> prices) {
		return Collections.min(prices);
	}
	public static int getHighestPriceIndex(ArrayList<Double> prices) {
		i = prices.indexOf(getHighestPrice(orderPrices));
		return i;
	}
	public static int getLowestPriceIndex(ArrayList<Double> prices) {
		i = prices.indexOf(getLowestPrice(orderPrices));
		return i;
	}

}
