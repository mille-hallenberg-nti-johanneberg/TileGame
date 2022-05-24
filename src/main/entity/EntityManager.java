package main.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import main.entity.creature.Player;

public class EntityManager {
	
	ArrayList<Entity> entities;
	ArrayList<Entity> queueDeletion;
	
	private Player player;
	
	public EntityManager(Player player) {
		entities = new ArrayList<>();
		queueDeletion = new ArrayList<>();
		this.player = player;
		addEntity(player);
	}
	
	public void update() {
		queueDeletion = new ArrayList<>();
		
		for(Entity e : entities) {
			e.update();
		}
		
		for(Entity e: queueDeletion) {
			System.out.println(queueDeletion);
			removeEntity(e);
		}
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
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
}
