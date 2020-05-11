/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

	//class for static curved pipe objects
public class CurvedPipeStatic extends PipeStatic {
    //take the image for static pipe(we have L shaped curved pipe as default)
    Image image = new Image(new FileInputStream("src/images/curvedLStatic.png"), Main.TILE_SIZE, Main.TILE_SIZE, false, true);

    public CurvedPipeStatic(String property, String type, int id, int x, int y) throws FileNotFoundException {
        super(property, type, id, x, y);

        imageView = new ImageView(image);

        if (type.equals("PipeStatic")) {
            this.canMove = false;	//static pipes can not move and can not be changed with another tile.

            if (property.equalsIgnoreCase("00")) {
                //in case of "00" property, pipe looks up and left so we rotate the image 270 degrees
                imageView.setRotate(270);

            } else if (property.equalsIgnoreCase("01")) {
                //in case of "01" property, pipe looks up and right so we already have the needed image
            } else if (property.equalsIgnoreCase("10")) {
                //in case of "10" property, pipe looks down and left so we rotate the image 180 degrees
                imageView.setRotate(180);
            } else if (property.equalsIgnoreCase("11")) {
                //in case of "11" property, pipe looks down and right so we rotate the image 90 degrees
                imageView.setRotate(90);
            }
        }
    }


}
