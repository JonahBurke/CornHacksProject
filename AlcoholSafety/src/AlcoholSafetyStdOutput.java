import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Authors: Jonah Burke and Ethan Weber Date: 1/19/2019
 * 
 * This is our project for the CornHacks event at UNL.
 */

public class AlcoholSafetyStdOutput {
	public static void main(String[] args) throws IOException {
		
		System.out.println("We have compiled a database of varying alcoholic"
				+ " beverages.\nWhich drink would you like to know about?"
				+ "\nPS: Multiple results may be returned.");
		
		Scanner s = new Scanner(System.in);
		String drinkName = (s.nextLine()).toLowerCase();
		s.close();
		
		File file = new File("alcoholList.csv");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line = br.readLine();
		while(line != null) {
			String tokens[] = line.split(",");
			if((tokens[0].toLowerCase()).contains(drinkName)) {
				int ml = Integer.parseInt(tokens[1]);
				double abv = Double.parseDouble(tokens[2])/2;
				double alcoholAmt = ml*abv/100;
				double mass = alcoholAmt*.78924; //in grams
				double stdDrinks = mass/14;
				System.out.printf("%s contains %.2f ML/%.2f g of alcohol in a %s ML bottle"
						+ "\n(%.2f standard drinks)\n\n",
						tokens[0], alcoholAmt, mass, tokens[1], stdDrinks);
			}
			line = br.readLine();
		}
		br.close();
	}
}