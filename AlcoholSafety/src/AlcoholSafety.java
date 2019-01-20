import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * Authors: Jonah Burke and Ethan Weber Date: 1/19/2019
 * 
 * This is our project for the CornHacks event at UNL.
 */

public class AlcoholSafety extends Application{
	
	/**
	 * These Methods are for the javafx framework
	 */
	
	@Override
    public void init() {
		// Put something in here if you need setup.
    }
    
    @Override
    public void start(Stage primaryStage) {
    	// Initializing default/custom objects and variables
    	int maxWidth = 250;
    	int stdHeight = 25;
    	
        // Initializing javafx objects
    	Scene scene;
        StackPane root = new StackPane();
        root.setAlignment(Pos.TOP_LEFT);
        TextField gender = new TextField("Male or Female?");
        TextField weight = new TextField("Weight (lbs)");
        TextField searchbar = new TextField("Search for Alcohol");
        Font labelFont = new Font(15);
        Label info = new Label("Body Information");
        info.setFont(labelFont);
        Label search = new Label("Alcohol Search");
        search.setFont(labelFont);
        Button execSearch = new Button("Search");
        ListView<String> alcoholList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        
        info.setTranslateY(0);
        info.setTranslateX(0);
        gender.setTranslateY(info.getTranslateY() + stdHeight);
        gender.setMaxWidth(maxWidth);
        gender.setTranslateX(info.getTranslateX());
        weight.setTranslateY(gender.getTranslateY() + stdHeight);
        weight.setMaxWidth(maxWidth);
        weight.setTranslateX(info.getTranslateX());
        search.setTranslateY(weight.getTranslateY() + stdHeight);
        search.setTranslateX(info.getTranslateX());
        searchbar.setTranslateY(search.getTranslateY() + stdHeight);
        searchbar.setTranslateX(info.getTranslateX());
        searchbar.setMaxWidth(maxWidth);
        execSearch.setTranslateY(searchbar.getTranslateY() + stdHeight);
        execSearch.setTranslateX(info.getTranslateX());
        alcoholList.setTranslateY(execSearch.getTranslateY() + stdHeight);
        alcoholList.setTranslateX(info.getTranslateX());
        alcoholList.setMaxWidth(maxWidth);
        alcoholList.setMaxHeight(150);
        
        // Handle click on "Search" button
        execSearch.setOnAction((ActionEvent) -> {
        	System.out.println("Lets boogie");
        	items.addAll(getAlcoholList(searchbar.getText()));
            if (!root.getChildren().contains(alcoholList)) {
            	root.getChildren().add(alcoholList);
            }
            searchbar.clear();
        });
        // Handle the list of possible drinks
        alcoholList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	System.out.println("We boogied");
            }
        });
        
       
        // Add all the nodes to the StackPane
        root.getChildren().add(info);
        root.getChildren().add(gender);
        root.getChildren().add(weight);
        root.getChildren().add(search);
        root.getChildren().add(searchbar);
        root.getChildren().add(execSearch);
        
        // Setup the scene
        scene = new Scene(root, 400, 400);
        // Adding the title to the window (primaryStage)
        primaryStage.setTitle("Drink Safely");
        primaryStage.setScene(scene);
        // Show the window(primaryStage)
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        Platform.exit();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Methods for the backend go here
	 */
	
	public static ObservableList<String> getAlcoholList(String drinkName) {
		ObservableList<String> alcoholList = FXCollections.observableArrayList();
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
		//HashMap<Integer, String> results = new HashMap<Integer, String>();
		
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
				
				// These would be useful later
				double oz = ml/29.5735;
				double alcoholAmt = ml*abv/100;//total amt in bottle
				double mass = alcoholAmt*.78924; //amt in bottle in grams
				double stdDrinks = mass/14;
				double gramsPerShot = mass/stdDrinks;
				alcoholList.add(String.format("%s %s ML, %s proof", tokens[0], tokens[1], tokens[2]));
			}
			try {
				line = br.readLine();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
		return alcoholList;
	}

}