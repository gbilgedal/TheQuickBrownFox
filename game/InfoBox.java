/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import javafx.geometry.*;

//This class is used for creating the info boxes and their layout. Info boxes are the ones that appear on the screen as a pop up screen.
//In this project, we use them with the button interactions
public class InfoBox {
    private static Stage window = new Stage();
    private static Button closeButton;
    private static Label rulesLabel;
    private static VBox layout;
    private static Scene scene;

    //display() method displays the information box with given title and message at the screen.
    public static void display(String title, String message) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        //creates a label for the message to appear on the screen
        rulesLabel = new Label(message);
        rulesLabel.setTextAlignment(TextAlignment.CENTER);
        //a button for turning back to the main stage
        closeButton = new Button("Back to Main Menu");
        //an event listener to make the button do something, closing this screen.
        closeButton.setOnAction(e -> window.close());

        layout = new VBox(10);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.getChildren().addAll(rulesLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
