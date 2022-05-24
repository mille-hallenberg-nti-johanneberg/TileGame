package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 16, height = 16;
	
	//Tile textures
	public static BufferedImage
	grass, rock, water, sand;
	
	//Creature textures
	public static BufferedImage
	defaultCreature, slimeCreature;
	
	//Static entity textures
	public static BufferedImage
	pineTree;
	
	//Crop all Assets
	public static void init() {
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("res/textures/tiles.png"));
		SpriteSheet creatures = new SpriteSheet(ImageLoader.loadImage("res/textures/creatures.png"));
		SpriteSheet statics = new SpriteSheet(ImageLoader.loadImage("res/textures/statics.png"));
		
		//Tiles
		grass = tiles.crop(0, 0, width, height);
		rock = tiles.crop(width, 0, width, height);
		water = tiles.crop(0, height, width, height);
		sand = tiles.crop(0, height * 2, width, height);
		
		//Creatures
		defaultCreature = creatures.crop(0, 0, width, height);
		slimeCreature = creatures.crop(0, height, width, height);
		
		//Creatures
		pineTree = statics.crop(0, 0, width, height);
	}
}
