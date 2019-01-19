import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Authors: Jonah Burke and Ethan Weber Date: 1/19/2019
 * 
 * This is our project for the CornHacks event at UNL.
 */

public class AlcoholSafety extends JFrame{
	static GraphicsConfiguration gc;
	static JTextField weight, height;
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		int screenWidth = (int) screenSize.getWidth();
		JFrame frame = new JFrame(gc);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Alcohol Safety");
		frame.setSize(screenWidth/2, screenHeight/2);
		frame.setLocation(screenWidth/4, screenHeight/4);
		
		
		frame.getContentPane().setLayout(new FlowLayout());
		
		frame.pack();
		frame.setVisible(true);
		
		
		Scanner scan = new Scanner(System.in);
		String input = null;
		boolean decided = true;
		do {
			System.out.println("Options:\n"
					+ "Update Profile\n"
					+ "Update Drinks Consumed");
			input = scan.nextLine();
			if (input.equalsIgnoreCase("Update Profile")) {
				decided = false;

			} else if (input.equalsIgnoreCase("Update Drinks Consumed")) {
				decided = false;

			} else {
				System.out.println("Not a recognized input. Try again.");
			}
		} while (decided);

		if (input.equalsIgnoreCase("Update Profile")) {
			
		} else { // Update Drinks Consumed
			System.out.println("Enter Drink Name");
			input = scan.nextLine();
//			if (/* Drink is not found in database */ ) {
//				// TODO: Prompt user for drink type (whisky, rum, etc.) and/or other necessary info
//			}

			System.out.println("Amount:");
			//Probably have to store this in a double or int variable or somethign
			input = scan.nextLine();

			// TODO: Calculate the amount of alcohol/standard drinks consumed so far and how much more cna be handled
		}

	}

}