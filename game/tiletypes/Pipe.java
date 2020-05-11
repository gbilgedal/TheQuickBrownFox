/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	//class for creating pipes
public class Pipe extends Tile{
	//Get image for pipes and our image is for vertical image
	Image image= new Image(new FileInputStream("src/images/pipeVertical.png"),Main.TILE_SIZE,Main.TILE_SIZE,false,true);

	public Pipe(String property, String type, int id, boolean canMove, int x, int y) throws FileNotFoundException {
		super(property, type, id, canMove, true, false,x, y);
		//If the pipes are vertical get image for vertical pipe
		if(property.equalsIgnoreCase("Vertical")) {
			imageView = new ImageView(image);
			
		}
		//If the pipes are horizontal
		else if(property.equalsIgnoreCase("Horizontal")) {
			// If the pipes are horizontal get vertical pipe image and turn it 90 degree to make horizontal
			imageView = new ImageView(image);
			imageView.setRotate(90);
		}
	}
	public ImageView getView() {
		return this.imageView;
		
	}

}
