/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	//class for creating the starter tile
public class StarterTile extends Pipe  {

	//get image for starter tile(default is vertical)
	Image image = new Image(new FileInputStream("src/images/startVertical.png"),Main.TILE_SIZE,Main.TILE_SIZE,false,true);


	public StarterTile(String property, String type, int id, int x, int y) throws FileNotFoundException {
		super(property, type, id, false,x,y);

		if(property.equalsIgnoreCase("Vertical")) {
			//if the pipe is vertical, get image for vertical pipe
			imageView = new ImageView(image);
			
		}
		else if(property.equalsIgnoreCase("Horizontal")) {
			//if the pipe is horizontal, get vertical pipe image and turn it 90 degree to make it horizontal
			imageView = new ImageView(image);
			imageView.setRotate(90);
		}
	}
}
