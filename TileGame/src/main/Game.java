package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.audio.AudioManager;
import main.gfx.Assets;
import main.gfx.GameCamera;
import main.input.KeyManager;
import main.input.MouseManager;
import main.world.World;
import main.world.WorldGenerator;
import main.world.WorldIO;

//The heart of the application. Contains a 60fps locked game loop which calls an update and render function.
public class Game implements Runnable{
	//Window and graphics
	private Window window;
	private Graphics g;
	private BufferStrategy bs;
	
	//Thread
	Thread thread;
	boolean gameRunning = false;
	
	//World and camera
	private World world;
	private GameCamera gameCamera;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Game(Window window) {
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		this.window = window;
	}
	
	//Prepare application and load files
	void init() {
		Assets.init();
		
		new Handler(this);
		Handler.instance.setAudioManager(new AudioManager());
		
		window.getFrame().addKeyListener(keyManager);
		window.getFrame().addMouseListener(mouseManager);
		window.getFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);
				
//		Handler.instance.setWorld(WorldGenerator.createWorld(500, 500, 500, "res/worlds/world_1"));
		Handler.instance.setWorld(WorldIO.loadWorld("res/worlds/world_1"));
		//world = Handler.instance.getWorld();
		gameCamera = new GameCamera(0, 0);
		
		
	}
	
	void update() {
		keyManager.update();
		mouseManager.update();
		
		Handler.instance.getWorld().update();
		Handler.instance.getAudioManager().update();

		gameCamera.centerOnEntity(Handler.instance.getEntityManager().getPlayer());
	}
	
	void render() {
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, Window.getWidth(), Window.getHeight());
		
		//Draw here
		Handler.instance.getWorld().render(g);
		
		bs.show();
		g.dispose();
	}
	
	//Main Game Loop
	public void run() {
		init();
		
		int fps = 60;
		int nanosInSeconds = 1_000_000_000;
		double timePerTick = nanosInSeconds / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (gameRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				update();
				render();
				delta--;
				ticks++;
			}
			
			if (timer >= nanosInSeconds) {
				System.out.println(ticks);
				timer = 0;
				ticks =0;
			}
		}
	}

	public synchronized void start() {
		if (gameRunning) 
			return;
		gameRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!gameRunning)
			return;
		gameRunning = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
}
