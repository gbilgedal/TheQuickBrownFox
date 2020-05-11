/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	//class for curved pipe objects
public class CurvedPipe extends Pipe{

	//takes the image for static pipe (we have L shaped pipe as default)
	Image image = new Image(new FileInputStream("src/images/curvedL.png"),Main.TILE_SIZE,Main.TILE_SIZE,false,true);


	
	public CurvedPipe(String property, String type, int id, int x, int y) throws FileNotFoundException {
		super(property, type, id, true,x,y);
			imageView = new ImageView(image);

			if(property.equalsIgnoreCase("00")) {

				//in case of "00" property pipes look up and left so we rotate the image 270 degrees
				imageView.setRotate(270);
				
			}
			else if(property.equalsIgnoreCase("01")) {
				//in case of "01" property pipes look up and right so we already have this image
			}
			else if(property.equalsIgnoreCase("10")) {
				//in case of "10" property pipes look down and left so we rotate the image 180 degrees
				imageView.setRotate(180);
			}
			else if(property.equalsIgnoreCase("11")) {
				//in case of "11" property pipes look down and right so we rotate the image 90 degrees
				imageView.setRotate(90);
			}
			
		
		
	}
	//a getter function
	public ImageView getView() {
		return this.imageView;
	}
	
}
