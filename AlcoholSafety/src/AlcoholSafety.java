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
        ObservableList<String> items = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
        alcoholList.setItems(items);
        
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
            root.getChildren().add(alcoholList);
        });
        // Handle the list of possible drinks
        alcoholList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
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
		
		/*
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
			if ( Drink is not found in database  ) {
				// TODO: Prompt user for drink type (whisky, rum, etc.) and/or other necessary info
			}

			System.out.println("Amount:");
			//Probably have to store this in a double or int variable or somethign
			input = scan.nextLine();

			// TODO: Calculate the amount of alcohol/standard drinks consumed so far and how much more cna be handled
		}
		*/

	}

}