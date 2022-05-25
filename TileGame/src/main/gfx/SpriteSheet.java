package main.gfx;

import java.awt.image.BufferedImage;

//The SpriteSheet class is a BufferedImage of an texture atlas (a giant image containing smaller images) 
public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//Crops out a small image out of the sheet
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
}
