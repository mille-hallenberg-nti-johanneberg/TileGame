package main;

import main.entity.EntityManager;
import main.gfx.GameCamera;
import main.input.KeyManager;
import main.input.MouseManager;
import main.world.World;

public class Handler {
	public static Handler instance;
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		if (instance == null) {
			instance = this;
			this.game = game;
		}
	}

	public Game getGame() {
		return game;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public EntityManager getEntityManager() {
		return world.getEntityManager();
	}
}
