package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.entity.item.ItemEntity;
import main.gfx.Assets;
import main.item.AppleItem;
import main.tile.Tile;

public class Tree extends StaticEntity{

	public Tree(int x, int y) {
		super(x, y, 16, 32);
		health = 100;
	}
	
	@Override
	public void update() {
		if (health <= 0) {
			System.out.println("Remove me");
			Handler.instance.getAudioManager().playSound(Assets.audio_hurt);
			Handler.instance.getEntityManager().addEntity(new ItemEntity((int)getCenterX(), (int)getCenterY(), new AppleItem()));
			Handler.instance.getEntityManager().addEntityToDeletionQueue(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pineTree, (int)(x - Handler.instance.getGameCamera().getxOffset()), (int)(y - Handler.instance.getGameCamera().getyOffset()), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
		//g.drawRect((int)(x + bounds.x - Handler.instance.getGameCamera().getxOffset()), (int)(y+ bounds.y - Handler.instance.getGameCamera().getyOffset()) , width, height);
	}
}	
