package main.entity.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.entity.item.ItemEntity;
import main.gfx.Assets;
import main.input.KeyManager;
import main.input.Keys;
import main.item.Item;
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
				
		if (Keys.UP.isPressed()) {
			moveY -= speed;
		}
		if (Keys.DOWN.isPressed()) {
			moveY += speed;
		}
		if (Keys.LEFT.isPressed()) {
			moveX -= speed;
		}
		if (Keys.RIGHT.isPressed()) {
			moveX += speed;
		}
		
		if (Keys.PLACE.isDown()) Handler.instance.getWorld().setTile((int)(x / Tile.TILE_WIDTH) + direction.x, (int)(y / Tile.TILE_HEIGHT) + direction.y, Tile.stoneTile);
		if (Keys.REMOVE.isPressed()) Handler.instance.getWorld().setTile((int)(x / Tile.TILE_WIDTH) + direction.x, (int)(y / Tile.TILE_HEIGHT) + direction.y, Tile.grassTile);
		
		if (Keys.ATTACK.isPressed()) attack();
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
		
		for (ItemEntity ie: intersectsWithItems()) {
			Handler.instance.getEntityManager().addEntityToDeletionQueue(ie);
			Handler.instance.getAudioManager().playSound(Assets.audio_itemPick);
		}
	}

}
