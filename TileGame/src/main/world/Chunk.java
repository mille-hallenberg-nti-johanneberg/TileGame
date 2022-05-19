package main.world;

import java.util.ArrayList;

import main.entity.Entity;

public class Chunk {
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
