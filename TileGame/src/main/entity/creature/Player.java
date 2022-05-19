package main.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.gfx.Assets;
import main.input.KeyManager;
import main.tile.Tile;

public class Player extends Creature{

	public Player(int x, int y) {
		super(x, y, 32, 32);
		bounds.x = 0;
		bounds.y = 0;
		
		health = 30;
	}
	
	public void controls() {
		moveX = 0;
		moveY = 0;
		
		KeyManager input = Handler.instance.getKeyManager();
		
		if (input.up) {
			moveY -= speed;
		}
		if (input.down) {
			moveY += speed;
		}
		if (input.left) {
			moveX -= speed;
		}
		if (input.right) {
			moveX += speed;
		}
		
		if (input.place) Handler.instance.getWorld().setTile((int)(x / Tile.TILE_WIDTH) + direction.x, (int)(y / Tile.TILE_HEIGHT) + direction.y, Tile.stoneTile);
		if (input.remove) Handler.instance.getWorld().setTile((int)(x / Tile.TILE_WIDTH) + direction.x, (int)(y / Tile.TILE_HEIGHT) + direction.y, Tile.grassTile);
		
		if (input.attack) attack();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.defaultCreature, (int)x - Handler.instance.getGameCamera().getxOffset(), (int)y - Handler.instance.getGameCamera().getyOffset(), width, height, null);
		g.setColor(Color.red);
		//g.drawRect((int)(x + bounds.x) - Handler.instance.getGameCamera().getxOffset(), (int)(y + bounds.y) - Handler.instance.getGameCamera().getyOffset(), width, height);
	}
	
	@Override
	public void update() {
		controls();
		move();
	}

}
