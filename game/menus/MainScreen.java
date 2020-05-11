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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainScreen{
	public static void startMainMenu(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		//MAIN MENU INITIALIZATION
		BackgroundImage myBI= new BackgroundImage(new Image(new FileInputStream("src/images/bgformainmenu.jpg"),600,600,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		String rules = "Slide the road tiles to make a path for the brown fox to the foxhole.\nBlue tiles can not move.\nAim to deliver the brown box to foxhole as quick as possible.";
		window.setResizable(false);
		window.setTitle("The Quick Brown Fox");
		
		//main container for the menu page
		VBox mainPart = new VBox(10);
		mainPart.setBackground(new Background(myBI));
		
		//container for game name
		HBox h = new HBox(10);
		Label gameName = new Label("The Quick Brown Fox");
		gameName.setFont(Font.font("Arial", 38));
		h.setPadding(new Insets(20,20,20,20));
		h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(gameName);
		
		//container for image
		HBox h1 = new HBox(10);
		Image img = new Image(new FileInputStream("src/images/fox.png"));
		ImageView iv = new ImageView(img);
		iv.setFitWidth(300);
        iv.setPreserveRatio(true);
		h1.setPadding(new Insets(20,20,20,20));
		h1.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(iv);
        
		
		//container for buttons
		HBox h2 = new HBox(10);
		h2.setSpacing(100);
		h2.setAlignment(Pos.CENTER);
		
		Button startButton = new Button(" Start Game ");
		
		startButton.setScaleX(2);
		startButton.setScaleY(2);
		startButton.setOnAction(e->{
			try {
				Main.startGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		Button infoButton = new Button("How to Play");
		infoButton.setOnAction(e->{
			InfoBox.display("Rules for The Quick Brown Fox", rules);
		});
		h2.setPadding(new Insets(20,20,20,20));
		infoButton.setScaleX(2);
		infoButton.setScaleY(2);
        h2.getChildren().addAll(startButton, infoButton);
        
        //container for creators name
        HBox h3 = new HBox(10);
		Label creators = new Label("Bilge&Gokcen");
		creators.setFont(Font.font("Arial", 12));
		h3.setPadding(new Insets(30,20,20,20));
		h3.setAlignment(Pos.CENTER);
        h3.getChildren().addAll(creators);
        
		
        
		mainPart.getChildren().addAll(h,h1,h2,h3);
		Scene scene = new Scene(mainPart,600,600);
		//MAIN MENU INITIALIZATION
		
		window.setScene(scene);
		window.show();
		
	}


	

}
