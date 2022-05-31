package main.item;

import java.awt.image.BufferedImage;

import main.gfx.Assets;

public enum ItemType {
	ROCK(Assets.rockItem, 10), STICK(Assets.stickItem, 20), LOG(Assets.logItem,5), 
		BERRIES(Assets.berriesItem, 30), APPLE(Assets.appleItem, 15);

	private BufferedImage texture;
	private int stackSize;

	ItemType(BufferedImage texture) {
		this.texture = texture;
		this.stackSize = 1;
	}

	ItemType(BufferedImage texture, int stackSize) {
		this.texture = texture;
		this.stackSize = stackSize;
	}

	// Getters and setters
	public BufferedImage getTexture() {
		return texture;
	}

	public int getStackSize() {
		return stackSize;
	}
}
