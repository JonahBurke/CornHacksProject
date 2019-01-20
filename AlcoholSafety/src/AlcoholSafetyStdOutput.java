import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Authors: Jonah Burke and Ethan Weber Date: 1/19/2019
 * 
 * This is our project for the CornHacks event at UNL.
 */

public class AlcoholSafetyStdOutput {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("We're able to calculate your BAC with some info:");
		
		System.out.println("Please enter your gender as one of the following "
				+ "numbers:\nMale (1)\nFemale (2)");
		
		String genderString = s.nextLine();
		int gender = 0;
		try {
			gender = Integer.parseInt(genderString);
		} catch(NumberFormatException NFE) {
			System.out.println("that's not a valid number.");
		}
		double ratio = 0;
		if(gender < 1 || gender > 2) {
			System.out.println("The options are 1 or 2. Try again.");
			System.exit(1);
		} else if(gender == 1) {
			ratio = 0.68;
		} else {
			ratio = 0.55;
		}
		
		System.out.println("Please enter your weight to the nearest pound.");
		
		String weightString = s.nextLine();
		int weight = 0;
		try {
			weight = Integer.parseInt(weightString);
		} catch(NumberFormatException NFE) {
			System.out.println("that's not a valid number.");
			System.exit(1);
		}
		
		if(weight < 50 || weight > 750) {
			System.out.println("if you weigh that much you probably shouldn't"
					+ " be drinking...\nPlease restart the program and input a realistic weight");
			System.exit(1);
		}
		
		
		System.out.println("Please type a query to find your drink in our database:");
		
		String drinkName = (s.nextLine()).toLowerCase();
		
		File file = new File("alcoholList.csv");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException FNFE) {
			System.out.println("Invalid file path");
			System.exit(1);
		}
		
		String line = new String();
		try {
			line = br.readLine();
		} catch (IOException IOE) {
			System.out.println("IOException trying to read first line of file");
		}
		
		int i=0;
		HashMap<Integer, String> results = new HashMap<Integer, String>();
		HashMap<Integer, String> extraInfo = new HashMap<Integer, String>();
		HashMap<Integer, Double> drinks = new HashMap<Integer, Double>();
		
		while(line != null) {
			String tokens[] = line.split(",");
			if((tokens[0].toLowerCase()).contains(drinkName)) {
				int ml = 0;
				double abv = 0;
				try {
					ml = Integer.parseInt(tokens[1]);
					abv = Double.parseDouble(tokens[2])/2;//proof/2 = abv
				} catch(NumberFormatException NFE) {
					System.out.println("It's possible there's an issue in our list,"
							+ " but it shouldn't affect the rest of the program.");
					NFE.printStackTrace();
				}
				
				double oz = ml/29.5735;
				double alcoholAmt = ml*abv/100;//total amt in bottle
				double mass = alcoholAmt*.78924; //amt in bottle in grams
				double stdDrinks = mass/14;
				double gramsPerShot = mass/stdDrinks;
				results.put(i, String.format("%s %s ML, %s proof", tokens[0], tokens[1], tokens[2]));
				extraInfo.put(i, String.format("%s (%s proof) contains %.2f ML/%.2f oz of alcohol\n"
						+ "in a %s ML bottle (%.2f standard drinks)\n",
						tokens[0], tokens[2], alcoholAmt, oz, tokens[1], stdDrinks));
				drinks.put(i, gramsPerShot);
				i++;
			}
			try {
				line = br.readLine();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("IOException trying to close bufferedReader... why would this ever happen");
			System.exit(1);
		}
		
		System.out.println("These are the first and most relevant options for your "
				+ "search query.\nPlease select an option by typing the number related "
				+ "to each option.\n");
		
		int j = 0;
		if(i == 0) {
			System.out.println("no results were found for that query, sorry!");
			System.exit(0);
		} 
		else if(i >= 4) { //if there are less than 4 options, display the amt available
			for(j=0; j<4; j++) {
				System.out.println("("+(j+1)+")" + " " + results.get(j));
			}
		} else { //display no more than 4 options
			for(j=0; j<i; j++) {
				System.out.println("("+(j+1)+")" + " " + results.get(j));
			}
		}
		
		String choiceString = s.nextLine();
		int choice = 0;
		try {
			choice = Integer.parseInt(choiceString);
		} catch(InputMismatchException IME) {
			System.out.println("That's not a valid number");
			System.exit(1);
		}
		
		if(choice < 1 || choice > 4) {
			System.out.println("Your options are between one and "+j+". Try again.");
			System.exit(1);
		}
		System.out.println(extraInfo.get(choice-1));
		
		System.out.println("How many shots of this drink have you had tonight?");
		
		String shotsString = s.nextLine();
		int shots = 0;
		try {
			shots = Integer.parseInt(shotsString);
		} catch(NumberFormatException NFE) {
			System.out.println("That's not a valid number");
			System.exit(1);
		}
		
		if(shots < 1 ) {
			System.out.println("if you had zero shots your bac is probably 0."
					+ " thanks for using our project though!");
			System.exit(1);
		} else if (shots > 25) {
			System.out.println("if you had that many shots today we doubt you're alive");
			System.exit(1);
		}
		
		//(std drinks/shot * shots) / (weight in lbs * grams/lb * ratio by gender) * 100 = BAC
		double bloodAlcContent = (drinks.get(choice-1) * shots) / (weight * 453.592 * ratio) * 100;
		System.out.printf("Your max BAC should be around %.2f%%", bloodAlcContent);
		
		s.close();
	}
}