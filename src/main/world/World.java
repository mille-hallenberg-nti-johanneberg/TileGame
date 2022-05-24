package main.world;

import java.awt.Graphics;
import java.util.Random;

import main.Handler;
import main.Window;
import main.audio.AudioManager;
import main.entity.EntityManager;
import main.entity.item.ItemEntity;
import main.gfx.Assets;
import main.item.*;
import main.tile.Tile;

public class World {
	private int width, height;
	
	private Tile[][] tiles;
	private Chunk[][] entityChunks;
	
	private EntityManager entityManager;
	
	private String folderPath;
//	private final String localWorldPath = "/world.txt";
//	private final String localEntitiesPath = "/entities/entities.txt";
//	private final String localPlayerPath = "/entities/player.txt";
	
	public World(String path) {
		this.folderPath = path;
		//createWorld();
		//saveWorld();
		//loadWorld(folderPath);
		Handler.instance.getAudioManager().playSound(Assets.audio_hit);
	}
	
	public void render(Graphics g) {
		renderWorld(g);
		entityManager.render(g);
	}
	Random rand = new Random();
	public void update() {
		controls();
		// if(rand.nextInt(100)<= 1) entityManager.addEntity(new Slime(Handler.instance.getGameCamera().getxOffset(), Handler.instance.getGameCamera().getyOffset()));;
		entityManager.update();
//		System.out.println(entityChunks[0][0].getEntities());
	}
	
	public void controls() {
		if (Handler.instance.getKeyManager().save) WorldIO.saveWorld(this);
		if (Handler.instance.getKeyManager().place) entityManager.addEntity(new ItemEntity((int)entityManager.getPlayer().getX(), (int)entityManager.getPlayer().getY(), new RockItem()));
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
		
		Tile t = tiles[x][y];
		if (t == null) 
			return Tile.grassTile;
		
		return t;
	}
	
	public void setTile(int x, int y, Tile t) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		
		tiles[x][y] = t;
	}
	
	public void setTiles(Tile[][] tiles){
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public Chunk[][] getEntityChunks() {
		return entityChunks;
	}

	public void setEntityChunks(Chunk[][] entityChunks) {
		this.entityChunks = entityChunks;
	}
	
	public Chunk getChunk(float x, float y){
		int i_x = (int)Math.floor(x / (Chunk.chunkSize * Tile.TILE_WIDTH)), i_y = (int)Math.floor(y / (Chunk.chunkSize * Tile.TILE_HEIGHT));
		if (i_x < 0) i_x = 0;
		if (i_y < 0) i_y = 0;
		if (i_x >= width / Chunk.chunkSize-1) i_x = width / Chunk.chunkSize-1;
		if (i_y >= height / Chunk.chunkSize-1) i_y = height / Chunk.chunkSize-1;
		return entityChunks[i_x][i_y];
	}
	
	public String getWorldFolder(){
		return folderPath;
	}

//	public Chunk[][] getEntityChunk() {
//		return entityChunks;
//	}
//
//	public void setEntityChunk(Chunk[][] entityChunk) {
//		this.entityChunks = entityChunk;
//	}
	
	/*
	public void loadWorld(String path){
		String file = Utils.loadFileAsString(path + localWorldPath);
		String tokens[] = file.split("\\s+");		// "\\s+" = All blank spaces
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		tiles = new Tile[width][height];
		entityChunks = new Chunk[(int)Math.ceil(width / Chunk.chunkSize)][(int)Math.ceil(height / Chunk.chunkSize)];
		
		for (int y = 0; y < height / Chunk.chunkSize; y++){
			for (int x = 0; x < width / Chunk.chunkSize; x++){
				entityChunks[x][y] = new Chunk();
			}
		}

		entityManager = new EntityManager((Player)Utils.readObjectFromFile(folderPath + localPlayerPath));
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Tile.tiles[Utils.parseInt(tokens[(x + y * height) + 2])];
			}
		}
		
//		Chunk loadedChunk = (Chunk) Utils.readObjectFromFile(folderPath + localEntitiesPath);
//		System.out.println("I read: " + loadedChunk.getEntities());
//		entityManager.setEntities(loadedChunk.getEntities());
//		entityManager.setPlayer((Player) loadedChunk.getEntities().get(0));
		double before = System.currentTimeMillis();
		ArrayList<Object> loadedChunks = Utils.readObjectsFromFile(folderPath + localEntitiesPath);
		ArrayList<Chunk> chunks = new ArrayList<>();
		System.out.println(loadedChunks);
		for (Object c : loadedChunks){
			chunks.add((Chunk)c);
		}
		
		System.out.println(chunks);
		
		int i = 0;
		for (int y = 0; y < height / Chunk.chunkSize; y++){
			for (int x = 0; x < width / Chunk.chunkSize; x++){
				entityChunks[x][y] = chunks.get(i);
				i++;
			}
		}
		
		double after = System.currentTimeMillis();
		
		System.out.println("Benchmark: " + (after - before) / 1000);
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
				worldString.append(tiles[x][y].getId());
				worldString.append(" ");
			}
			worldString.append("\n");
		}
		System.out.println("I convertd");
		Utils.writeStringToFile(folderPath + localWorldPath, worldString.toString());
		
		//Save entities
//		Chunk chunk = new Chunk();
//		chunk.setEntities(entityManager.getEntities());
		ArrayList<Object> chunks = new ArrayList<>();
		for (int y = 0; y < entityChunks[0].length; y++){
			for (int x = 0; x < entityChunks.length; x++){
				chunks.add(entityChunks[x][y]);
			}
		}
		Utils.writeObjectsToFile(folderPath + localEntitiesPath, chunks);
		Utils.writeObjectToFile(folderPath + localPlayerPath, entityManager.getPlayer());
		
		//System.out.println("I wrote: " + chunk.getEntities());
		//Utils.writeObjectToFile(folderPath + localEntitiesPath, chunk);
		
		//entityManager.setEntities(new ArrayList<Entity>());
		//Utils.writeStringToFile(folderPath + localEntitiesPath, entitiesString);
	}
	*/
}
