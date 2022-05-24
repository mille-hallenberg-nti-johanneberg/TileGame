package main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import main.Handler;
import main.entity.creature.Creature;
import main.entity.item.ItemEntity;
import main.tile.Tile;

public abstract class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int DEFAULT_HEALTH = 15;
	
	protected int health = DEFAULT_HEALTH;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(Tile.TILE_WIDTH / 2 - width / 2, Tile.TILE_WIDTH / 2 - width / 2, width, height);
	}
	
	public boolean collidesWithEntity(float xOffset, float yOffset) {
		for (Entity e : Handler.instance.getWorld().getEntityManager().getEntities()) {
			if (e == this) 
				continue;
			if (e.getClass().getSuperclass() == Creature.class) 
				continue;
			if (e.getClass() == ItemEntity.class) 
				continue;
			if (e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset))) 
				return true;
		}
		return false;
	}
	
	public ArrayList<Entity> intersectedEntities(Rectangle bounds) {
		ArrayList<Entity> intersectedEntities = new ArrayList<>();
		for (Entity e : Handler.instance.getWorld().getEntityManager().getEntities()) {
			if (e == this) 
				continue;
			if (e.getCollisionBounds(0, 0).intersects(bounds)) 
				intersectedEntities.add(e);
		}
		return intersectedEntities;
	}
	
	public boolean checkEntityCollision(float xOffset, float yOffset) {
		//TODO Add some sort of entity manager
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), width, height);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public int getHealth() {
		return health;
	}
	
	public void takeDamage(int damage) {
		health -= damage;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
