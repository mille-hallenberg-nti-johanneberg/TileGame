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
		System.out.println("Itement");
		this.item = item;
		bounds.x = 0;
		bounds.y = 0;
		// TODO Auto-generated constructor stub
	}
	
	public ItemEntity(int x, int y) {
		super(x, y, 0, 0);
		this.item = new AppleItem();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(item.getType().getTexture(), (int)x - Handler.instance.getGameCamera().getxOffset(), (int)y - Handler.instance.getGameCamera().getyOffset(), 32, 32, null);
	}

}

//	Item[] inventory;