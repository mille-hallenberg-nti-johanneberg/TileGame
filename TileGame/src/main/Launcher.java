package main;

public class Launcher {

	public static void main(String[] args) {
		Window window = new Window("MyTileGame", 1080, 720);
		Game game = new Game(window);
		game.start();
	}

}
