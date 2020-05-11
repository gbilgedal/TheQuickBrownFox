/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.menus;

import java.io.FileInputStream;


import java.util.ArrayList;

import game.Main;
import game.levels.LevelCreation;
import game.levels.GameLogic;
import game.tiletypes.Tile;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameScreen{
	
	//these were the variables created to be reachable from the other classes, for functionalities and validation.
	public static GridPane gridPane;
	public static Tile starter = null;
	public static HBox hBoxFox = null;
	public static Rectangle rect = null;
	public static Button nextB= null;
	
	static int moves = 0;
	static int goalX = 0;
	static int goalY = 0;
	static int currX = 0;
	static int currY = 0;
	static int currXPixel = 0;
	static int currYPixel = 0;
	static ImageView ivGoal = null;
	static ImageView ivCurr = null;
	static Label infoTable = null;


	public static Scene getScene(int i) throws Exception {
		//GAME SCREEN INITIALIZATION
		BorderPane bp = new BorderPane();
		gridPane = new GridPane();		
		gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		gridPane.setAlignment(Pos.CENTER);
		
		HBox h = new HBox(0);

		infoTable = new Label("   Level-"+Main.currLevel +" ✦  Moves: "+ moves);	
		infoTable.setLayoutY(15);
		infoTable.setFont(Font.font("Arial", 30));
		h.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		h.setAlignment(Pos.CENTER);				
		h.getChildren().add(infoTable);
	
		
		//create level from the file and make it visible on the game screen
		LevelCreation.createLevel(i);
		
		ArrayList<Tile> tilesList = LevelCreation.tiles;
		
		//find the starter tile in tilesList
		for(int j = 0;j<tilesList.size();j++) {
			if(tilesList.get(j).getType().equals("Starter")) {
				starter = tilesList.get(j);
				
			}
		}
		
		//Add the current level's tiles to gridpane
		for(int j = 0; j<tilesList.size();j++) {
			Tile tile = tilesList.get(j);	
			gridPane.add(tile.getView(), tile.getYCoord(), tile.getXCoord());
			tile.getView().setOnMousePressed((e -> {
				currX = tile.getXCoord();
				currY = tile.getYCoord();
				currXPixel = (int)e.getSceneX();
				currYPixel = (int)e.getSceneY();
				ivCurr = tile.getView();
            }));
			
			// add event listener to each and every tile.
			tile.getView().setOnMouseReleased((e -> {
				//here we get the tile that was trying to be reached by the user.
				Tile tileToGo = getTileThatIsOntheCursor(currX, currY,tilesList,(int)e.getSceneX(),(int)e.getSceneY(),currXPixel,currYPixel);		
				
				//and after that, if the tile exists, we move the tile to the necessary position with moveTile function.
				if(tileToGo != null) {
					goalX = tileToGo.getXCoord();
					goalY = tileToGo.getYCoord();
					ivGoal = tileToGo.getView();
					try {
						moveTile(currX, currY, goalX, goalY, tileToGo, tile, gridPane, starter);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}				
            }));
		}
		
		//creates the fox
		rect = new Rectangle(60, 60);
		rect.setFill(new ImagePattern(new Image(new FileInputStream("src/images/fox.png"))));
		hBoxFox = new HBox();
		hBoxFox.setAlignment(Pos.CENTER);
		hBoxFox.getChildren().add(rect);
		gridPane.setRowIndex(rect, starter.getXCoord());
		gridPane.setColumnIndex(rect, starter.getYCoord());
		gridPane.getChildren().add(hBoxFox);
		
		
		//adds the next level button
		Image playI=new Image(new FileInputStream("src/images/arrow.png"));
		ImageView iv1=new ImageView(playI);
		iv1.setFitHeight(25);
		iv1.setFitWidth(25);
		nextB = new Button("",iv1);
		h.getChildren().add(nextB);
		nextB.setVisible(false);
		nextB.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//add an event listener to the button
		GameScreen.nextB.setOnMouseClicked(e -> {
			//opens the next level page or the ending screen with respect to currLevel variable that is statically created in Main class.
			if (Main.currLevel + 1 > Main.MAX_LEVEL) {
				try {
					EndingScreen.startEndingMenu(Main.window);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				try {
					GameScreen.startGameScreen(Main.currLevel + 1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("LEVEL FINISHED");
				Main.levelDone = true;
			}
		});
		
		
		
		bp.setCenter(gridPane);
		bp.setBottom(h);
		
		Scene scene = new Scene(bp,600,650);
		return scene;
	}
	
	//this function does exactly what its name says.
	private static Tile getTileThatIsOntheCursor(int tileX, int tileY,ArrayList<Tile> tiles, int sceneX, int sceneY, int startX, int startY ) {
		//to interpret user's behavior on the tile, we took advantage of cursor's beginning and ending position.
		//we have calculated the distance between the first and the last x-y coordinate differences.
		//according to that, we can identify user's behavior.
		int xDistance = sceneX-startX;
		int yDistance = sceneY-startY;
		if(Math.abs(xDistance)>=Math.abs(yDistance)) {
			if(xDistance>0) {
				for(int i = 0; i<tiles.size();i++) {
					//gives the tile that is in the right if the place is in the boundaries of 
					if(tiles.get(i).getYCoord() == tileY+1&&tiles.get(i).getXCoord()==tileX &&tileY-1 < Main.COLUMN_COUNT ) {
							return tiles.get(i);
					}
				}
			}
			else {
				for(int i = 0; i<tiles.size();i++) {
					//gives the tile that is in the left if the place is in boundaries
					if(tiles.get(i).getYCoord() == tileY-1&&tiles.get(i).getXCoord()==tileX&&tileY-1>=0) {
						return tiles.get(i);
					}
				}				
			}
		}
		else {
			if(yDistance>0) {
				for(int i = 0; i<tiles.size();i++) {
					//gives the tile that is on the top if the place is in boundaries
					if(tiles.get(i).getXCoord() == tileX+1&&tiles.get(i).getYCoord()==tileY&&tileX-1 < Main.ROW_COUNT) {
						return tiles.get(i);
					}
				}
				
			}
			else {
				for(int i = 0; i<tiles.size();i++) {
					//gives the tile that is on the bottom if the place is in boundaries
					if(tiles.get(i).getXCoord() == tileX-1&&tiles.get(i).getYCoord()==tileY&&tileX-1>=0) {
						return tiles.get(i);
					}
				}
				
			}	
		}
		return null;
	}

	//this function takes the current tile and goal tile, then switches their ImageViews' place on the GridPane.
	private static void moveTile(int currentX, int currentY, int goX, int goY, Tile tileGo, Tile tileCurrent,
			GridPane gp, Tile starter) throws Exception {
		
		//if the goal tile is changable and movable, if the tile exists, switch their places
		if(tileGo.isCanChange()&&tileCurrent.isCanMove()&&tileGo!=null) {
			gp.setRowIndex(tileGo.getView(), currentX);
			gp.setColumnIndex(tileGo.getView(), currentY);
			gp.setRowIndex(tileCurrent.getView(),goX);
			gp.setColumnIndex(tileCurrent.getView(), goY);
			
			tileGo.setXCoord(currentX);
			tileGo.setYCoord(currentY);
			
			tileCurrent.setXCoord(goX);
			tileCurrent.setYCoord(goY);	

			//then after each move, check whether the level is solved	
			GameLogic.checkSolved(starter);
			
			//increment move count
			moves++;
			
			//change the info table's value.
			infoTable.setText("   Level-"+Main.currLevel +" ✦  Moves: "+ moves);

		}
	}

	//start game screen with the given level
	public static void startGameScreen(int level) throws Exception {
	
		Main.currLevel = level;
		Main.levelDone = false;
		Stage window = Main.window;
		window.setTitle("The Quick Brown Fox - Level "+ level);
		window.setScene(getScene(level));
		window.show();

	}

}
