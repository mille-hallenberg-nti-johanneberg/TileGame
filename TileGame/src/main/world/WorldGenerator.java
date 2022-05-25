package main.world;

import java.util.Random;

import main.entity.EntityManager;
import main.entity.creature.Player;
import main.entity.statics.Tree;
import main.noise.OpenSimplexNoise;
import main.tile.Tile;

public class WorldGenerator {
	
	public static World createWorld(int width, int height, int seed, String path){
		World world = new World(path);
		
		world.setWidth(width);
		world.setHeight(height);
		
		Tile[][] tiles = new Tile[width][height];
		Chunk entityChunks[][] = new Chunk[(int)Math.ceil(width / Chunk.chunkSize)][(int)Math.ceil(height / Chunk.chunkSize)];

		for (int y = 0; y < height / Chunk.chunkSize; y++){
			for (int x = 0; x < width / Chunk.chunkSize; x++){
				entityChunks[x][y] = new Chunk();
			}
		}
		
		//EntityManager entityManager = ;
		world.setEntityChunks(entityChunks);
		world.setEntityManager(new EntityManager(new Player(100, 150), world));

		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		Random rand = new Random(seed);		
		
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				double noiseValue = noise.eval(x / 24f, y / 24f);
				
				tiles[x][y] = Tile.grassTile;
				if (noiseValue >= 0.2f){
					if (rand.nextInt(10) <= 5){
						world.getEntityManager().addEntity(new Tree(x * Tile.TILE_WIDTH + rand.nextInt(32)-16, y * Tile.TILE_HEIGHT + rand.nextInt(32)-16));
					}
				}
				else if (noiseValue >= 0.0f) tiles[x][y] = Tile.sandTile;
				else tiles[x][y] = Tile.waterTile;
				
			}	
		}
		
		world.setTiles(tiles);
		
		return world;
	}
}
