/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game;

import game.menus.GameScreen;
import game.menus.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;

//This is the class that we run the main program, the actual game itself.
public class Main extends Application{
	public static Stage window;		//Main stage
	public static boolean levelDone = false;		//this controls whether the current level is done or not.
	public static boolean levelCreated = false;		//this controls whether the current level is created or not.
	public static int currLevel = 1;		//game starts at first level.
	public final static int MAX_LEVEL = 6;		//here we assign the number of levels (5 given levels + 1 extra)
	public final static int TILE_SIZE = 150;
	public static int ROW_COUNT = 4;
	public static int COLUMN_COUNT = 4;

	public static void main(String[] args) {
		launch(args);		//this line is required for any JavaFX application
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		MainScreen.startMainMenu(window);

	}
	//Starts the game screen with the given level value
	public static void startGame() throws Exception {
		GameScreen.startGameScreen(currLevel);

	}

}


