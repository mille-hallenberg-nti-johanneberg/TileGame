package main.tile;

import java.awt.image.BufferedImage;

public class Tile {
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile waterTile = new WaterTile(2);
	public static Tile sandTile = new SandTile(3);
	
	private int id;
	private BufferedImage texture;
	private int health = 100;
	
	//Constructor for creating a Tile
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
		
	//Constructor for creating a Tile with unique health
	public Tile(BufferedImage texture, int id, int health) {
		this.texture = texture;
		this.id = id;
		this.health = health;
		
		tiles[id] = this;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}