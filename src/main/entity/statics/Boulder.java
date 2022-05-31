package main.entity.statics;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.tile.Tile;

public class Boulder extends StaticEntity{

	public Boulder(int x, int y) {
		super(x, y, 40, 40);
		bounds.x = 0;
		bounds.y = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.boulder, (int)(x - Handler.instance.getGameCamera().getxOffset()), (int)(y - Handler.instance.getGameCamera().getyOffset()), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
	}

}
