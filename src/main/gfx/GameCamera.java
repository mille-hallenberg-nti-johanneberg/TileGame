package main.gfx;

import main.Handler;
import main.Window;
import main.entity.Entity;
import main.tile.Tile;

public class GameCamera {
	private int xOffset, yOffset;
	private int zoomFactor = 1;
	
	public GameCamera(int xOffset, int yOffset) {
		this.setxOffset(xOffset);
		this.setyOffset(yOffset);
	}
	
	public void keepInsideBounds() {
		if (xOffset < 0) {
			xOffset = 0;
		}
		else if (xOffset > Handler.instance.getWorld().getWidth() * Tile.TILE_WIDTH - Window.getWidth()) {
			xOffset = Handler.instance.getWorld().getWidth() * Tile.TILE_WIDTH - Window.getWidth();
		}
		if (yOffset < 0) {
			yOffset = 0;
		}
		else if (yOffset > Handler.instance.getWorld().getHeight() * Tile.TILE_HEIGHT - Window.getHeight()) {
			yOffset = Handler.instance.getWorld().getHeight() * Tile.TILE_HEIGHT - Window.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = (int)(e.getX() - Window.getWidth() / 2 + e.getWidth() / 2);
		yOffset = (int)(e.getY() - Window.getHeight() / 2 + e.getHeight() / 2);
		
		keepInsideBounds();
	}
	
	public void moveAmount(int xAmount, int yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
		
		keepInsideBounds();
	}
	
	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getZoom() {
		return zoomFactor;
	}

	public void setZoom(int zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
}
