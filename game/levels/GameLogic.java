/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.levels;

import java.util.ArrayList;

import game.Main;
import game.menus.GameScreen;
import game.tiletypes.Tile;
import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class GameLogic {
	
	public static Path path;
	public static PathTransition pt = null;
	
	static int xPos = 30;
	static int yPos = 30;
	
	final static int xOffset = 150; 
	final static int yOffset = 150;

	public static void checkSolved(Tile starter) throws Exception {
		
		/*
		 * this is the first step of the solution function, checkSolved mainly finds the starter tile, checks whether it is next to the end or not.
		 * then it starts a recursive function call to check the level's situation *with previous direction*. 
		 * also we add the  animation paths in this function and checksolvedwithdirection.
		 * 
		 */
		Tile end = null;
		
		xPos = 30+(starter.getXCoord()*yOffset);
		yPos = 30+(starter.getYCoord()*xOffset);
		
		ArrayList<Tile> tilesList = LevelCreation.tiles; 
		
		path = new Path();
		path.getElements().add(0, new MoveTo(30+(starter.getXCoord()*yOffset),30+(starter.getYCoord()*xOffset)));
		
		// gets the starter and ending tile from the tiles arraylist
		for (int i = 0; i < tilesList.size(); i++) {

			if (tilesList.get(i).getType().equals("End")) {
				end = tilesList.get(i);
			}

		}

		// in the place where the function is called, this move actually is done, after
		// the move is done, we check whether is solved or not
		Tile nextTile = null;

		if (starter.getProperty().equals("Horizontal")) {
			System.out.println("horizontal starter");
			for (int i = 0; i < tilesList.size(); i++) {
				if (tilesList.get(i).getYCoord() == starter.getYCoord() + 1
						&& tilesList.get(i).getXCoord() == starter.getXCoord()) {
					nextTile = tilesList.get(i);

				}
			}
			if (nextTile.isCanGo() && nextTile != end) {
				LineTo lt = new LineTo(xPos+xOffset,yPos);
				path.getElements().add(lt);
				xPos+=xOffset;
				checkSolvedWithDirection(nextTile, "Right");
			}
		}
		if (starter.getProperty().equals("Vertical")) {
			System.out.println("vertical starter");
			for (int i = 0; i < tilesList.size(); i++) {
				if (tilesList.get(i).getXCoord() == starter.getXCoord() + 1
						&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
					nextTile = tilesList.get(i);
				}
			}
			if (nextTile.isCanGo() && nextTile != end) {
				LineTo lt = new LineTo(xPos,yPos+yOffset);
				path.getElements().add(lt);
				yPos+=yOffset;
				checkSolvedWithDirection(nextTile, "Down");
			}

		}
	}

	public static boolean checkSolvedWithDirection(Tile starter, String move) throws Exception {
		/*
		 * this function recursively checks whether the level is solved or not.
		 * in each call, it behaves as if the given tile is the starter one, so when it comes to the end, it checks the end tile and the one before the end tile basically.
		 * it does it with checking the last moved direction, current tile's property, goal tile's existence and goability(being able to go on it).
		 * basically it tries each and every possible move, checks whether they are valid or not.
		 */
		
		Tile end = null;
		Tile nextTile = null;
		ArrayList<Tile> tilesList = LevelCreation.tiles;
		// gets the ending tile from the tiles arraylist
		for (int i = 0; i < tilesList.size(); i++) {
			if (tilesList.get(i).getType().equals("End")) {
				end = tilesList.get(i);
			}
		}

		if (starter.getProperty().equals("Horizontal")) {
			for (int i = 0; i < tilesList.size(); i++) {
				if (tilesList.get(i).getYCoord() == starter.getYCoord() + 1
						&& tilesList.get(i).getXCoord() == starter.getXCoord()) {

					nextTile = tilesList.get(i);
				}
			}
			if (nextTile.isCanGo() && nextTile != end && (move.equals("Right") || move.equals("Left"))) {
				
				LineTo lt = new LineTo(xPos+xOffset,yPos);
				path.getElements().add(lt);
				xPos+=xOffset;
				checkSolvedWithDirection(nextTile, "Right");
			}
			if (nextTile == end&&end.getProperty().equals("Horizontal")) {
				if(move.equals("Right")) {
					LineTo lt = new LineTo(xPos+xOffset,yPos);
					path.getElements().add(lt);
					xPos+=xOffset;
				}
				else if( move.equals("Left")) {
					LineTo lt = new LineTo(xPos-xOffset,yPos);
					path.getElements().add(lt);
					xPos-=xOffset;
					
				}
					PathTransition pt = new PathTransition();
				      pt.setDuration(Duration.millis(4000)); 
				      pt.setNode(GameScreen.rect); 
				      pt.setPath(path);  
				      pt.setAutoReverse(false);
				      pt.setCycleCount(1);
				      pt.play();
				   
				      GameScreen.nextB.setVisible(true);
				      System.out.println("LEVEL FINISHED");
				      Main.levelDone = true;
				
				      for(int i = 0; i<LevelCreation.tiles.size();i++) {
							LevelCreation.tiles.get(i).setCanMove(false);
				      }
					return true;
				}

		}

		if (starter.getProperty().equals("Vertical")) {
			for (int i = 0; i < tilesList.size(); i++) {
				if (tilesList.get(i).getXCoord() == starter.getXCoord() + 1
						&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
					nextTile = tilesList.get(i);
				}
			}
			if (nextTile.isCanGo() && nextTile != end && (move.equals("Down") || move.equals("Up"))) {
				LineTo lt = new LineTo(xPos,yPos+yOffset);
				path.getElements().add(lt);
				yPos+=yOffset;
				
				checkSolvedWithDirection(nextTile, "Down");
			}
			if (nextTile == end&&end.getProperty().equals("Vertical")) {
				if(move.equals("Down")) {
					LineTo lt = new LineTo(xPos,yPos+yOffset);
					path.getElements().add(lt);
					yPos+=yOffset;
				}
				else if( move.equals("Up")) {
					LineTo lt = new LineTo(xPos,yPos-yOffset);
					path.getElements().add(lt);
					yPos-=yOffset;
					
				}
				PathTransition pt = new PathTransition();
			      pt.setDuration(Duration.millis(4000)); 
			      pt.setNode(GameScreen.rect); 
			      pt.setPath(path);  
			      pt.setAutoReverse(false);
			      pt.setCycleCount(1);
			      pt.play();
			   
			      GameScreen.nextB.setVisible(true);
			      System.out.println("LEVEL FINISHED");
			      Main.levelDone = true;
			
			      for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
			      }
					GameScreen.nextB.setVisible(true);
					System.out.println("LEVEL FINISHED");
					Main.levelDone = true;
					for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
					}
					return true;
				
			}

		}

		if (starter.getProperty().equals("00")) {

			if (move.equals("Down")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getYCoord() == starter.getYCoord() - 1
							&& tilesList.get(i).getXCoord() == starter.getXCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
	
					LineTo lt1 = new LineTo(xPos-(xOffset),yPos);
					path.getElements().add(lt1);
					xPos-=(xOffset);			
					
					checkSolvedWithDirection(nextTile, "Left");
				}
			} else if (move.equals("Right")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getXCoord() == starter.getXCoord() - 1
							&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
				
					LineTo lt = new LineTo(xPos,yPos-(yOffset));
					path.getElements().add(lt);
					yPos-=(yOffset);
					
					checkSolvedWithDirection(nextTile, "Up");
				}
			}

			if (nextTile == end) {
				if (move.equals("Right")) {
					
					LineTo lt = new LineTo(xPos,yPos-(yOffset));
					path.getElements().add(lt);
					yPos-=(yOffset);					
				}
				else if (move.equals("Down")) {
			
					LineTo lt1 = new LineTo(xPos-(xOffset),yPos);
					path.getElements().add(lt1);
					xPos-=(xOffset);			
					
					
				}
				
				PathTransition pt = new PathTransition();
			      pt.setDuration(Duration.millis(4000)); 
			      pt.setNode(GameScreen.rect); 
			      pt.setPath(path);  
			      pt.setAutoReverse(false);
			      pt.setCycleCount(1);
			      pt.play();
			   
			      GameScreen.nextB.setVisible(true);
			      System.out.println("LEVEL FINISHED");
			      Main.levelDone = true;
			
			      for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
			      }
				
				GameScreen.nextB.setVisible(true);
				System.out.println("LEVEL FINISHED");
				Main.levelDone = true;
				for(int i = 0; i<LevelCreation.tiles.size();i++) {
					LevelCreation.tiles.get(i).setCanMove(false);
				}
				return true;
				
			}

		}
		if (starter.getProperty().equals("01")) {

			if (move.equals("Down")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getYCoord() == starter.getYCoord() + 1
							&& tilesList.get(i).getXCoord() == starter.getXCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
				
					LineTo lt1 = new LineTo(xPos+(xOffset),yPos);
					path.getElements().add(lt1);
					xPos+=(xOffset);			
					checkSolvedWithDirection(nextTile, "Right");
				}
			} else if (move.equals("Left")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getXCoord() == starter.getXCoord() - 1
							&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
			
					LineTo lt = new LineTo(xPos,yPos-(yOffset));
					path.getElements().add(lt);
					yPos-=(yOffset);
					checkSolvedWithDirection(nextTile, "Up");
				}
			}

			if (nextTile == end) {
				if (move.equals("Left")) {
					
					LineTo lt = new LineTo(xPos,yPos-(yOffset));
					path.getElements().add(lt);
					yPos-=(yOffset);
				}
				else if (move.equals("Down")) {
				
					LineTo lt1 = new LineTo(xPos+(xOffset),yPos);
					path.getElements().add(lt1);
					xPos+=(xOffset);					
				}
				PathTransition pt = new PathTransition();
			      pt.setDuration(Duration.millis(4000)); 
			      pt.setNode(GameScreen.rect); 
			      pt.setPath(path);  
			      pt.setAutoReverse(false);
			      pt.setCycleCount(1);
			      pt.play();
			   
			      GameScreen.nextB.setVisible(true);
			      System.out.println("LEVEL FINISHED");
			      Main.levelDone = true;
			
			      for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
			      }
			
				GameScreen.nextB.setVisible(true);
				System.out.println("LEVEL FINISHED");
				Main.levelDone = true;
				for(int i = 0; i<LevelCreation.tiles.size();i++) {
					LevelCreation.tiles.get(i).setCanMove(false);
				}
				return true;
				
			}

		}
		if (starter.getProperty().equals("10")) {

			if (move.equals("Up")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getYCoord() == starter.getYCoord() - 1
							&& tilesList.get(i).getXCoord() == starter.getXCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
				
					LineTo lt1 = new LineTo(xPos-(xOffset),yPos);
					path.getElements().add(lt1);
					xPos-=(xOffset);
					
					checkSolvedWithDirection(nextTile, "Left");
				}
			} else if (move.equals("Right")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getXCoord() == starter.getXCoord() + 1
							&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
					
					LineTo lt = new LineTo(xPos,yPos+(yOffset));
					path.getElements().add(lt);
					yPos+=(yOffset);
					checkSolvedWithDirection(nextTile, "Down");
				}
			}

			if (nextTile == end) {
				if(move.equals("Right")) {
					
					LineTo lt = new LineTo(xPos,yPos+(yOffset));
					path.getElements().add(lt);
					yPos+=(yOffset);					
				}
				else if(move.equals("Up")) {
					
					LineTo lt1 = new LineTo(xPos-(xOffset),yPos);
					path.getElements().add(lt1);
					xPos-=(xOffset);
									
				}
				PathTransition pt = new PathTransition();
			      pt.setDuration(Duration.millis(4000)); 
			      pt.setNode(GameScreen.rect); 
			      pt.setPath(path);  
			      pt.setAutoReverse(false);
			      pt.setCycleCount(1);
			      pt.play();
			   
			      GameScreen.nextB.setVisible(true);
			      System.out.println("LEVEL FINISHED");
			      Main.levelDone = true;
			
			      for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
			      }
				GameScreen.nextB.setVisible(true);
				System.out.println("LEVEL FINISHED");
				Main.levelDone = true;
				for(int i = 0; i<LevelCreation.tiles.size();i++) {
					LevelCreation.tiles.get(i).setCanMove(false);
				}
				return true;
				
			}

		}
		if (starter.getProperty().equals("11")) {
			if (move.equals("Up")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getYCoord() == starter.getYCoord() + 1
							&& tilesList.get(i).getXCoord() == starter.getXCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
					
					LineTo lt1 = new LineTo(xPos+(xOffset),yPos);
					path.getElements().add(lt1);
					xPos+=(xOffset);
					
					checkSolvedWithDirection(nextTile, "Right");
				}
			} else if (move.equals("Left")) {
				for (int i = 0; i < tilesList.size(); i++) {
					if (tilesList.get(i).getXCoord() == starter.getXCoord() + 1
							&& tilesList.get(i).getYCoord() == starter.getYCoord()) {
						nextTile = tilesList.get(i);
					}
				}
				if (nextTile.isCanGo() && nextTile != end) {
					
					LineTo lt = new LineTo(xPos,yPos+(yOffset));
					path.getElements().add(lt);
					yPos+=(yOffset);
					checkSolvedWithDirection(nextTile, "Down");
				}
			}

			if (nextTile == end) {
				if (move.equals("Up")) {
					
					LineTo lt1 = new LineTo(xPos+(xOffset),yPos);
					path.getElements().add(lt1);
					xPos+=(xOffset);
					
				}
				else if (move.equals("Left")) {
					
					LineTo lt = new LineTo(xPos,yPos+(yOffset));
					path.getElements().add(lt);
					yPos+=(yOffset);
				}
				PathTransition pt = new PathTransition();
			      pt.setDuration(Duration.millis(4000)); 
			      pt.setNode(GameScreen.rect); 
			      pt.setPath(path);  
			      pt.setAutoReverse(false);
			      pt.setCycleCount(1);
			      pt.play();
			   
			      GameScreen.nextB.setVisible(true);
			      System.out.println("LEVEL FINISHED");
			      Main.levelDone = true;
			
			      for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
			      }
				
					GameScreen.nextB.setVisible(true);
					System.out.println("LEVEL FINISHED");
					Main.levelDone = true;
					for(int i = 0; i<LevelCreation.tiles.size();i++) {
						LevelCreation.tiles.get(i).setCanMove(false);
					}
					return true;
				
			}
		}
		return false;
	}

}