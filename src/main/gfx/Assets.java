package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final String soundsPath = "res/sounds/";
	public static String audio_hit, audio_hurt, audio_itemPick, audio_select, audio_save;
	
	private static final int width = 16, height = 16;

	// Tile textures
	public static BufferedImage grass, rock, water, sand;

	// Creature textures
	public static BufferedImage defaultCreature, slimeCreature;

	// Static entity textures
	public static BufferedImage pineTree, boulder;

	// Items
	public static BufferedImage rockItem, stickItem, logItem, berriesItem,
			appleItem;
	
	// GUI
	public static BufferedImage inventorySlot;
	
	// Crop all Assets
	public static void init() {
		initSounds();
		initTextures();
	}
	
	private static void initSounds(){
		audio_hit = soundsPath + "hit.wav";
		audio_hurt = soundsPath + "hurt.wav";
		audio_itemPick = soundsPath + "itemPick.wav";
		audio_select = soundsPath + "hit.wav";
		audio_save = soundsPath + "save.wav";
	}
	
	private static void initTextures(){
		SpriteSheet tiles = new SpriteSheet(
				ImageLoader.loadImage("res/textures/tiles.png"));
		SpriteSheet creatures = new SpriteSheet(
				ImageLoader.loadImage("res/textures/creatures.png"));
		SpriteSheet statics = new SpriteSheet(
				ImageLoader.loadImage("res/textures/statics.png"));
		SpriteSheet items = new SpriteSheet(
				ImageLoader.loadImage("res/textures/items.png"));
		SpriteSheet gui = new SpriteSheet(
				ImageLoader.loadImage("res/textures/gui.png"));

		// Tiles
		grass = tiles.crop(0, 0, width, height);
		rock = tiles.crop(width, 0, width, height);
		water = tiles.crop(0, height, width, height);
		sand = tiles.crop(0, height * 2, width, height);

		// Creatures
		defaultCreature = creatures.crop(0, 0, width, height);
		slimeCreature = creatures.crop(0, height, width, height);

		// Creatures
		pineTree = statics.crop(0, 0, width, height);
		boulder = statics.crop(0, height, width, height);

		// Items
		rockItem = items.crop(0, 0, width, height);
		stickItem = items.crop(width, 0, width, height);
		logItem = items.crop(width * 2, 0, width, height);
		berriesItem = items.crop(width * 3, 0, width, height);
		appleItem = items.crop(width * 4, 0, width, height);
		
		// GUI
		inventorySlot = items.crop(0, 0, width, 8);
	}
}
