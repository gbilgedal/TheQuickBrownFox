/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework. 
 */
package game.tiletypes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	//class for free tile objects
public class FreeTile extends EmptyTile {

    Image image;
    public FreeTile(String property, String type, int id, int x, int y) throws FileNotFoundException {
        super(property, type, false, false, true, id, x, y);

        //if the tile's property is free put a grey image
        image = new Image(new FileInputStream("src/images/grey.png"), Main.TILE_SIZE, Main.TILE_SIZE, false, true);
        imageView = new ImageView(image);
    }
    public ImageView getView() {
        return this.imageView;

    }
}
