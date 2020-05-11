/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmptyTile extends Tile {

	Image image;
	public EmptyTile(String property, String type,boolean canMove, boolean canGo, boolean canChange, int id,int x, int y) throws FileNotFoundException {

		super(property, type, id, canMove, canGo, canChange, x,y);
		//if the tile does not have a property of a "Free" tile, put an empty tile image to this tile.
		if(!property.equals("Free")) {
			image = new Image(new FileInputStream("src/images/empty.png"),Main.TILE_SIZE,Main.TILE_SIZE,false,true);
			imageView = new ImageView(image);
		}

		
	}
	public boolean isCanMove() {
		return canMove;
	}
	public boolean isCanGo() {
		return canGo;
	}
	public String getProperty() {
		return property;
	}
	public ImageView getView() {
		return this.imageView;
		
	}
	

}
