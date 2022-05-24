package main.world;

import java.awt.Graphics;

import main.Handler;
import main.Window;
import main.entity.EntityManager;
import main.entity.creature.Player;
import main.noise.OpenSimplexNoise;
import main.tile.Tile;
import main.utils.Utils;

public class World {
	private int width, height;
	private final int chunkSize = 8;
	
	private Tile[][] world;
	private Chunk[][] entityChunks;
	
	private EntityManager entityManager;
	
	private String folderPath;
	private final String localWorldPath = "/world.txt";
	private final String localEntitiesPath = "/entities.txt";
	
	public World(String path) {
		//createWorld();
		this.folderPath = path;
		//saveWorld();
		
		entityManager = new EntityManager(new Player(100, 150));
		loadWorld(folderPath);
	}
	
	public void createWorld(){
		width = 500;
		height = 500;
		world = new Tile[width][height];
		OpenSimplexNoise noise = new OpenSimplexNoise(245);
		System.out.println("Aight");
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				double noiseValue = noise.eval(x / 24f, y / 24f);
				
//				if (noiseValue > 0.3) world[x][y] = Tile.grassTile;
//				else if (noiseValue > 0.2) world[x][y] = Tile.stoneTile;
//				else world[x][y] = Tile.waterTile;
				
				if (noiseValue >= 0.0f) world[x][y] = Tile.grassTile; //Grass
				else if (noiseValue >= -0.2f) world[x][y] = Tile.sandTile;
				else world[x][y] = Tile.waterTile;
			}	
		}
	}
	
	public void loadWorld(String path){
		String file = Utils.loadFileAsString(path + localWorldPath);
		String tokens[] = file.split("\\s+");		// "\\s+" = All blank spaces
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		world = new Tile[width][height];
		entityChunks = new Chunk[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				world[x][y] = Tile.tiles[Utils.parseInt(tokens[(x + y * height) + 2])];
			}
		}
		
		Chunk loadedChunk = (Chunk) Utils.readObjectFromFile(folderPath + localEntitiesPath);
		System.out.println("I read: " + loadedChunk.getEntities());
		entityManager.setEntities(loadedChunk.getEntities());
		entityManager.setPlayer((Player) loadedChunk.getEntities().get(0));
		
//		for(int y = 0; y < height / chunkSize; y++) {
//			for(int x = 0; x < width / chunkSize; x++) {
//				entityChunks[x][y].addEntity(null);
//			}
//		}
//		Random rand = new Random();
//		Random randSpawn = new Random();
//		for (int y = 0; y < height; y++) {
//			
//			for (int x = 0; x < width; x++) {
//				if (randSpawn.nextInt(10) <= 4)
//					entityManager.addEntity(new Tree(Tile.TILE_WIDTH*x + rand.nextInt(32)-16,Tile.TILE_HEIGHT*y+ rand.nextInt(32)-16));
//			}
//		}
//		for (int i = 0; i < 10; i++)
//			entityManager.addEntity(new Slime(64,32));
	}
	
	public void saveWorld() {
		System.out.println("Fnuction");
		//Save the world
		StringBuilder worldString = new StringBuilder(width + " " + height + "\n");
		//set.append("test");
		//String worldString = width + " " + height + "\n";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				worldString.append(world[x][y].getId());
				worldString.append(" ");
			}
			worldString.append("\n");
		}
		System.out.println("I convertd");
		Utils.writeStringToFile(folderPath + localWorldPath, worldString.toString());
		
		//Save entities
		Chunk chunk = new Chunk();
		chunk.setEntities(entityManager.getEntities());
		
		System.out.println("I wrote: " + chunk.getEntities());
		Utils.writeObjectToFile(folderPath + localEntitiesPath, chunk);
		
		//entityManager.setEntities(new ArrayList<Entity>());
		//Utils.writeStringToFile(folderPath + localEntitiesPath, entitiesString);
	}
	
	public void render(Graphics g) {
		renderWorld(g);
		entityManager.render(g);
	}
	
	public void update() {
		controls();
		entityManager.update();
	}
	
	public void controls() {
		if (Handler.instance.getKeyManager().save) saveWorld();
	}
	
	public void renderWorld(Graphics g) {
		//Renders inside the camera
		int startX 	= (int)((Handler.instance.getGameCamera().getxOffset()) / Tile.TILE_WIDTH);
		int endX	= (int)((Handler.instance.getGameCamera().getxOffset() + Window.getWidth()) / Tile.TILE_WIDTH)+1;
		int startY 	= (int)((Handler.instance.getGameCamera().getyOffset()) / Tile.TILE_HEIGHT);
		int endY	= (int)((Handler.instance.getGameCamera().getyOffset() + Window.getHeight()) / Tile.TILE_HEIGHT)+1;
		
		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				g.drawImage(getTile(x,y).getTexture(), x * Tile.TILE_WIDTH - Handler.instance.getGameCamera().getxOffset(), y * Tile.TILE_HEIGHT - Handler.instance.getGameCamera().getyOffset(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) 
			return Tile.stoneTile;
		
		Tile t = world[x][y];
		if (t == null) 
			return Tile.grassTile;
		
		return t;
	}
	
	public void setTile(int x, int y, Tile t) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		
		world[x][y] = t;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

//	public Chunk[][] getEntityChunk() {
//		return entityChunks;
//	}
//
//	public void setEntityChunk(Chunk[][] entityChunk) {
//		this.entityChunks = entityChunk;
//	}
}
