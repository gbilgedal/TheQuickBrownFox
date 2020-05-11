/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.menus;

import java.io.FileInputStream;

import game.InfoBox;
import game.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class EndingScreen{

	public static void startEndingMenu(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		String story = "There is a pretty popular sentence \nfor testing out the alphabet. \nWhich goes like: \nThe quick brown fox jumps over the lazy dog.\nWe were inspired from this sentence.\nAnd we thought, why not? ";
		//ENGİNG MENU INITIALIZATION
		BackgroundImage myBI= new BackgroundImage(new Image(new FileInputStream("src/images/bgformainmenu.jpg"),600,600,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		window.setResizable(false);
		window.setTitle("The Quick Brown Fox");
		
		//main container for the menu page
		VBox mainPart = new VBox(10);
		mainPart.setBackground(new Background(myBI));
		
		//container for game name
		HBox h = new HBox(10);
		Label gameName = new Label("The Quick Brown Fox");
		gameName.setTextFill(Color.WHITE);
		gameName.setFont(Font.font("Arial", 38));
		h.setPadding(new Insets(20,20,20,20));
		h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(gameName);
		
		//container for image
		HBox h1 = new HBox(10);
		Image img = new Image(new FileInputStream("src/images/fox.png"));
		ImageView iv = new ImageView(img);
		iv.setFitWidth(200);
        iv.setPreserveRatio(true);
		h1.setPadding(new Insets(5,20,5,20));
		h1.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(iv);
        
		HBox h4 = new HBox(10);
		
		//label object for the score evaluation
		Label scoreEv;
		if(GameScreen.moves <= 43) {
			scoreEv = new Label("~Total moves: "+GameScreen.moves +" huh?~ \nYou were as quick as me! \nCongratulations <3");
		}
		else if(GameScreen.moves <=63) {
			scoreEv = new Label("~Total moves: "+GameScreen.moves +"~ \nYou did fine but can try again! \nDon't forget to have fun <3");
		}
		else {
			scoreEv = new Label("~Total moves: "+GameScreen.moves +"~ \nI don't wanna lie, you were slow. \nWanna try again? <3");
		}
		scoreEv.setTextFill(Color.DARKRED);
		scoreEv.setTextAlignment(TextAlignment.CENTER);
		scoreEv.setFont(Font.font("Arial", 27));
		
		h4.setPadding(new Insets(10,20,5,20));
		h4.setAlignment(Pos.CENTER);
        h4.getChildren().add(scoreEv);
		
		//container for buttons
		HBox h2 = new HBox(10);
		h2.setSpacing(100);
		h2.setAlignment(Pos.CENTER);
		
		//restart button
		Button restartButton = new Button("Restart Game ");
		restartButton.setFont(Font.font("Arial", 25));
		restartButton.setScaleX(1.15);
		restartButton.setScaleY(1.15);
		restartButton.setOnAction(e->{
			//restarts the level by setting the current level to 1 and cleaning the moves done, then calls the main function to start the game.
			try {
				Main.currLevel = 1;
				GameScreen.moves = 0;
				Main.startGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		//infoButton
		Button infoButton = new Button("Why am I called\n'The Quick Brown Fox?'");
		infoButton.setTextAlignment(TextAlignment.CENTER);
		infoButton.setOnAction(e->{
			InfoBox.display("Story Behind The Quick Brown Fox", story);
		});
		h2.setPadding(new Insets(18,20,20,20));
		infoButton.setFont(Font.font("Arial", 14));
		
		infoButton.setScaleX(1.15);
		infoButton.setScaleY(1.15);
        h2.getChildren().addAll(restartButton, infoButton);
        
        //container for creators name
        HBox h3 = new HBox(10);
		Label creators = new Label("\nGokcen&Bilge");
		creators.setFont(Font.font("Arial", 18));
		h3.setPadding(new Insets(20,20,-25,20));
		h3.setAlignment(Pos.CENTER);
        h3.getChildren().addAll(creators);
        
		
        
		mainPart.getChildren().addAll(h,h1,h4,h2,h3);
		Scene scene = new Scene(mainPart,600,600);
		//ENDING MENU INITIALIZATION
		
		
		window.setScene(scene);
		window.show();
		
	}
	

}
