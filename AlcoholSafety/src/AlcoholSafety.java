import java.util.Scanner;

/**
 * Authors: Jonah Burke and Ethan Weber Date: 1/19/2019
 * 
 * This is our project for the CornHacks event at UNL.
 */

public class AlcoholSafety {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = null;
		do {
			boolean decided = false;
			System.out.println("Options:\n"
					+ "Update Profile\n"
					+ "Update Drinks Consumed");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("Update Profile")) {
				decided = true;
				
			} else if (input.equalsIgnoreCase("Update Drinks Consumed")) {
				decided = true;
				
			} else {
				System.out.println("Not a recognized input. Try again.");
			}
		} while (!decided);
		
		if (input.equalsIgnoreCase("Update Profile")) {
			// TODO: Implement a person/profile class for body characteristics
		} else { // Udpate Drinks Consumed
			System.out.println("Enter Drink Name");
			input = scan.nextLine();
			if (/* Drink is not found in database */ ) {
				// TODO: Prompt user for drink type (whisky, rum, etc.) and/or other necessary info
			}
			
			System.out.println("Amount:");
			//Probably have to store this in a double or int variable or somethign
			input = scan.nextLine();
			
			// TODO: Calculate the amount of alcohol/standard drinks consumed so far and how much more cna be handled
		}

	}

}
