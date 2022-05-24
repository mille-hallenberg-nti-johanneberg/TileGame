package main.entity.creature;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.Handler;
import main.entity.Entity;
import main.gfx.Assets;
import main.tile.Tile;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3;
	public static final float DEFAULT_WIDTH = 16, DEFAULT_HEIGHT = 16;
	
	protected float speed = DEFAULT_SPEED;
	protected float moveX;
	protected float moveY;
	
	protected Point direction = new Point(0, 1);

	public Creature(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void move() {
		face();
		moveX();
		moveY();
	}
	
	public void attack() {
		Rectangle attackBox = new Rectangle((int)(x + direction.x * width), (int)(y + direction.y * height), width, height);
		ArrayList<Entity> intersectedEntities = intersectedEntities(attackBox);
		
		for (Entity e : intersectedEntities) {
			e.takeDamage(5);
		}
	}
	
	public void face() {
		float magnitudeX = Math.abs(moveX);
		float magnitudeY = Math.abs(moveY);
		
		if (magnitudeX > 0 && magnitudeY > 0) {
			direction.x = (int) Math.signum(moveX);
			direction.y = (int) Math.signum(moveY);
		}
		else if (magnitudeX > 0) {
			direction.x = (int) Math.signum(moveX);
			direction.y = 0;
		}
		else if (magnitudeY > 0) {
			direction.x = 0;
			direction.y = (int) Math.signum(moveY);
		}
	}

	public void moveX() {
		if (collidesWithEntity(moveX, 0)) return;
		int tileX = 0;
		
		//Collision right
		if (moveX > 0) {
			tileX = (int) ((x + moveX + bounds.width) / Tile.TILE_WIDTH);
			if (collisionWithTile(tileX, (int) (y / Tile.TILE_HEIGHT)) || 
					collisionWithTile(tileX, (int) ((y + bounds.height-1) / Tile.TILE_HEIGHT))) {
				x = tileX * Tile.TILE_WIDTH - bounds.x - bounds.width;
				return;
			}
		} 
		//Collision left
		else if (moveX < 0) {
			tileX = (int) Math.floor((x + moveX) / Tile.TILE_WIDTH);
			if (collisionWithTile(tileX, (int) (y / Tile.TILE_HEIGHT)) || 
					collisionWithTile(tileX, (int) ((y + bounds.height-1) / Tile.TILE_HEIGHT))) {
				x = (tileX + 1) * Tile.TILE_WIDTH;
				return;
			}
		}
		
		//Move, no collision
		x += moveX;
	}

	public void moveY() {
		if (collidesWithEntity(0, moveY)) return;
		int tileY = 0;
		
		//Collision up
		if (moveY > 0) {
			tileY = (int) ((y + moveY + bounds.height) / Tile.TILE_HEIGHT);
			if (collisionWithTile((int) (x / Tile.TILE_WIDTH), tileY) || 
					collisionWithTile((int) ((x + bounds.width-1) / Tile.TILE_WIDTH), tileY)) {
				y = tileY * Tile.TILE_HEIGHT - bounds.y - bounds.height;
				return;
			}
		} 
		//Collision down
		else if (moveY < 0) {
			tileY = (int) Math.floor((y + moveY) / Tile.TILE_HEIGHT);
			if (collisionWithTile((int) (x / Tile.TILE_WIDTH), tileY) || 
					collisionWithTile((int) ((x + bounds.width-1) / Tile.TILE_WIDTH), tileY)) {
				y = (tileY + 1) * Tile.TILE_HEIGHT;
				return;
			}
		}
		
		//Move, no collision
		y += moveY;
	}

	public boolean collisionWithTile(int x, int y) {
		return Handler.instance.getWorld().getTile(x, y).isSolid();
	}
}
