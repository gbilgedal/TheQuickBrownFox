/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	//class for creating static pipes
public class PipeStatic extends Pipe {
	boolean canMove = false; //static pipes can not move and can not change with another tile.

	//get image for static pipes (default is a vertical)
	Image image = new Image(new FileInputStream("src/images/pipeVerticalStatic.png"),Main.TILE_SIZE,Main.TILE_SIZE,false,true);

	public PipeStatic(String property, String type, int id, int x, int y) throws FileNotFoundException {
		super(property, type, id, false,x,y);
		//if the pipe is vertical, get image for vertical pipe
		if(property.equalsIgnoreCase("Vertical")) {
				imageView = new ImageView(image);
			
		}
		// if the pipe is horizontal, get vertical pipe image and turn it 90 degree to make horizontal
		if(property.equalsIgnoreCase("Horizontal")) {
			imageView = new ImageView(image);
			imageView.setRotate(90);
		}
		
	}
}
