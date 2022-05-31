package main.world;

import java.util.ArrayList;

import main.Handler;
import main.entity.EntityManager;
import main.entity.creature.Player;
import main.gfx.Assets;
import main.tile.Tile;
import main.utils.Utils;

public class WorldIO {
	
	private static final String localWorldPath = "/world.txt";
	private static final String localEntitiesPath = "/entities/entities.txt";
	private static final String localPlayerPath = "/entities/player.txt";
	
	static public World loadWorld(String path){
		World world = new World(path);
		
		String file = Utils.loadFileAsString(path + localWorldPath);
		String tokens[] = file.split("\\s+");		// "\\s+" = All blank spaces
		
		int width = Utils.parseInt(tokens[0]);
		int height = Utils.parseInt(tokens[1]);
		
		world.setWidth(width);
		world.setHeight(height);
		
		Tile tiles[][] = new Tile[width][height];
		Chunk entityChunks[][] = new Chunk[(int)Math.ceil(width / Chunk.chunkSize)][(int)Math.ceil(height / Chunk.chunkSize)];
		
		for (int y = 0; y < height / Chunk.chunkSize; y++){
			for (int x = 0; x < width / Chunk.chunkSize; x++){
				entityChunks[x][y] = new Chunk();
			}
		}

		world.setEntityManager(new EntityManager((Player)Utils.readObjectFromFile(path + localPlayerPath), world));
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Tile.tiles[Utils.parseInt(tokens[(x + y * height) + 2])];
			}
		}
		
		double before = System.currentTimeMillis();
		ArrayList<Object> loadedChunks = Utils.readObjectsFromFile(path + localEntitiesPath);
		ArrayList<Chunk> chunks = new ArrayList<>();

		for (Object c : loadedChunks){
			chunks.add((Chunk)c);
		}
				
		int i = 0;
		for (int y = 0; y < height / Chunk.chunkSize; y++){
			for (int x = 0; x < width / Chunk.chunkSize; x++){
				entityChunks[x][y] = chunks.get(i);
				i++;
			}
		}
		
		double after = System.currentTimeMillis();
		
		world.setTiles(tiles);
		world.setEntityChunks(entityChunks);
		
		System.out.println("Benchmark: " + (after - before) / 1000);
		
		return world;
	}
	
	static public void saveWorld(World world) {
		//Save the world
		String folderPath = world.getWorldFolder();
		int width = world.getWidth(), height = world.getHeight();
		
		Tile tiles[][] = world.getTiles();
		Chunk entityChunks[][] = world.getEntityChunks();
		
		StringBuilder worldString = new StringBuilder(width + " " + height + "\n");
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				worldString.append(tiles[x][y].getId());
				worldString.append(" ");
			}
			worldString.append("\n");
		}
		Utils.writeStringToFile(folderPath + localWorldPath, worldString.toString());
		
		//Save entities
		ArrayList<Object> chunks = new ArrayList<>();
		for (int y = 0; y < entityChunks[0].length; y++){
			for (int x = 0; x < entityChunks.length; x++){
				chunks.add(entityChunks[x][y]);
			}
		}
		Utils.writeObjectsToFile(folderPath + localEntitiesPath, chunks);
		Utils.writeObjectToFile(folderPath + localPlayerPath, world.getEntityManager().getPlayer());
		
		Handler.instance.getAudioManager().playSound(Assets.audio_save);
		System.out.println("Saved.");
	}
}
