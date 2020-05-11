/*
 * This game was a simple slide the tiles and connect the starting tile to the ending tile game with JavaFX Framework.
 */
package game.levels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import game.Main;
import game.tiletypes.CurvedPipe;
import game.tiletypes.CurvedPipeStatic;
import game.tiletypes.EmptyTile;
import game.tiletypes.EndTile;
import game.tiletypes.FreeTile;
import game.tiletypes.Pipe;
import game.tiletypes.StarterTile;
import game.tiletypes.PipeStatic;
import game.tiletypes.Tile;

public class LevelCreation {

	public static ArrayList<Tile> tiles = new ArrayList<>();

	//this function is used for creating the necessary objects by given properties
	public static Tile createTile(String id, String type, String property) throws Exception {
		int idInt = Integer.parseInt(id) - 1;
		
		//here, in order to use GridPane efficiently, to gather the values of X and Y for each tile,
		//we divided and found the remainder by row and column count
		int x = idInt / Main.ROW_COUNT;
		int y = idInt % Main.COLUMN_COUNT;
		switch (type) {
		case "Starter":
			StarterTile starter = new StarterTile(property, type, idInt, x, y);
			return starter;
		case "End":
			EndTile end = new EndTile(property, type, idInt, x, y);
			return end;
		case "Empty":
			if (property.equalsIgnoreCase("Free")) {
				FreeTile ft = new FreeTile(property, type, idInt, x, y);
				return ft;
			}
			EmptyTile et = new EmptyTile(property, type,true, false, false, idInt, x, y);
			return et;
		case "PipeStatic":
			if ((property.equalsIgnoreCase("00") || property.equalsIgnoreCase("01") || property.equalsIgnoreCase("10")
					|| property.equalsIgnoreCase("11"))) {
				CurvedPipeStatic cp = new CurvedPipeStatic(property, type, idInt, x, y);
				return cp;
			}
			PipeStatic ps = new PipeStatic(property, type, idInt, x, y);
			return ps;
		case "Pipe":
			if (property.equalsIgnoreCase("00") || property.equalsIgnoreCase("01") || property.equalsIgnoreCase("10")
					|| property.equalsIgnoreCase("11")) {
				CurvedPipe cp = new CurvedPipe(property, type, idInt, x, y);
				return cp;
			}
			Pipe p = new Pipe(property, type, idInt, true, x, y);
			return p;
		default:
			return null;
		}

	}

	public static void createLevel(int levelNum) throws Exception {
		// reads the txt file then gathers the necessary information to create the level
		try {
			if(levelNum<=Main.MAX_LEVEL) {
			File file = new File("src/game/levels/infos/level" + levelNum + ".txt");
			Scanner scanner = new Scanner(file);
			tiles.clear();
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if (data.trim().equalsIgnoreCase("")) {
					continue;
				}
				String[] infos = data.split(",");
				// in this line, createTile function creates the tile elements and then pushes the
				// element to the tiles array
				tiles.add(createTile(infos[0], infos[1], infos[2]));
				Main.levelCreated = true;
			}
			scanner.close();}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
