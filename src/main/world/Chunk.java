package main.world;

import java.io.Serializable;
import java.util.ArrayList;

import main.entity.Entity;

public class Chunk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Entity> entities;
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}
	public void removeEntity(Entity e) {
		this.entities.add(e);
	}
}
