package main.tile;

import main.gfx.Assets;

public class StoneTile extends Tile{

	public StoneTile(int id) {
		super(Assets.rock, id, 500);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
}
