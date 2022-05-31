package main.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;
import main.entity.creature.Player;
import main.tile.Tile;
import main.world.*;

public class EntityManager {
	
	ArrayList<Entity> entities;
	ArrayList<Entity> queueDeletion;
	
	private Player player;
	private World world;
	
	public EntityManager(Player player) {
		entities = new ArrayList<>();
		queueDeletion = new ArrayList<>();
		this.player = player;
		this.world = Handler.instance.getWorld();
	}
	
	public EntityManager(Player player, World world) {
		entities = new ArrayList<>();
		queueDeletion = new ArrayList<>();
		this.player = player;
		this.world = world;
	}
	
	public EntityManager() {
		entities = new ArrayList<>();
		queueDeletion = new ArrayList<>();
		
		this.world = Handler.instance.getWorld();
	}
	
	public void update() {
		queueDeletion = new ArrayList<>();
		
		player.update();
		
		setEntities(new ArrayList<Entity>());
		
		for(int y = 0; y <= 3; y++){
			for(int x = 0; x <= 3; x++){
				int chunkX = (int)Handler.instance.getGameCamera().getxOffset() + x * Chunk.chunkSize * Tile.TILE_WIDTH;
				int chunkY = (int)Handler.instance.getGameCamera().getyOffset() + y * Chunk.chunkSize * Tile.TILE_HEIGHT;
				
				Chunk c = Handler.instance.getWorld().getChunk(chunkX, chunkY);
				for (Entity e : c.getEntities()){
					addEntityToUpdater(e);
				}
			}
		}
		
		for(Entity e : entities) {
			Chunk previousChunk = Handler.instance.getWorld().getChunk(e.getX(), e.getY());
			e.update();
			Chunk currentChunk = Handler.instance.getWorld().getChunk(e.getX(), e.getY());
			
			if (e == player)
				continue;
			
			if (previousChunk != currentChunk){
				moveEntityToChunk(e, previousChunk, currentChunk);
			}
		}
		
		for(Entity e: queueDeletion) {
			removeEntity(e);
		}
	}
	
	public void render(Graphics g) {
		player.render(g);
		
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		
		//System.out.println(Handler.instance.getWorld().getChunk(e.getX(), e.getY()));
		//Handler.instance.getWorld();
		world.getChunk(e.getX(), e.getY()).addEntity(e);
	}
	
	public void addEntityToUpdater(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
		world.getChunk(e.getX(), e.getY()).removeEntity(e);
		//Handler.instance.getWorld()
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setEntities(ArrayList<Entity> entities){
		this.entities =  entities;
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public void addEntityToDeletionQueue(Entity e) {
		queueDeletion.add(e);
	}
	
	public void moveEntityToChunk(Entity e, Chunk from, Chunk to){
		from.removeEntity(e);
		to.addEntity(e);
	}
}
