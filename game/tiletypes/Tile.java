/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import game.Main;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
	//superclass of all tiles
public class Tile extends Rectangle {
	String property;
	String type;
	int id;
	ImageView imageView;
	int xCoord;	//tile's x coordinate in the gridpane like matrix ex:("0",0)
	int yCoord;	//tile's y coordinate in the gridpane like matrix ex:(3,"3")
	boolean canMove; //this controls whether the tile can move on to free tiles
	boolean canGo; //this controls whether the tile is reachable
	boolean canChange; //this controls whether the tile can change with another tile
 
	public Tile(String property, String type, int id, boolean canMove, boolean canGo, boolean canChange, int xCoord, int yCoord) {
		this.property = property;
		this.type = type;
		this.id = id;
		this.canMove = canMove;
		this.canGo = canGo;
		this.canChange = canChange;
		this.xCoord = xCoord;
		this.yCoord = yCoord;

		this.setWidth(Main.TILE_SIZE);
		this.setHeight(Main.TILE_SIZE);
	}
	//getter methods of canChange, canMove, canGo
	public boolean isCanChange() {
		return canChange;
	}
	public boolean isCanGo() {
		return canGo;
	}
	public boolean isCanMove() {
		return canMove;
	}
	//setter method of canMove
	public void setCanMove(boolean bool) {
		this.canMove =bool;
	}
	//getter and setter method of x and y coordinates
	public int getXCoord() {
		return xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public void setXCoord(int x) {
		this.xCoord = x;
	}
	public void setYCoord(int y) {
		this.yCoord = y;
	}
	//other getter methods
	public String getProperty() {
		return property;
	}
	public String getType() {
		return type;
	}
	public ImageView getView() {
		return imageView;
	}
}
