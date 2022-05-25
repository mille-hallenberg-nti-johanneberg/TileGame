package main.entity.item;

import java.awt.Graphics;

import main.Handler;
import main.entity.Entity;
import main.item.AppleItem;
import main.item.Item;
import main.tile.Tile;

public class ItemEntity extends Entity{

	public Item item;
	
	public ItemEntity(int x, int y, Item item) {
		super(x, y, 16, 16);
		this.item = item;
		bounds.x = 8;
		bounds.y = 8;
		// TODO Auto-generated constructor stub
	}
	
	public ItemEntity(int x, int y) {
		super(x, y, 16, 16);
		this.item = new AppleItem();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println(width);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(item.getType().getTexture(), (int)x - Handler.instance.getGameCamera().getxOffset(), (int)y - Handler.instance.getGameCamera().getyOffset(), 32, 32, null);
		g.drawRect((int)(x + bounds.x) - Handler.instance.getGameCamera().getxOffset(), (int)(y + bounds.y) - Handler.instance.getGameCamera().getyOffset(), width, height);
	}

}

//	Item[] inventory;