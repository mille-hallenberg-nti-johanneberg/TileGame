package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	private boolean[] keys;
		
	public boolean
	up, left, down, right, place, remove, save, attack;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void update() {
		up = 	keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		left = 	keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		down = 	keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		place = keys[KeyEvent.VK_SPACE];
		remove = keys[KeyEvent.VK_C];
		save = 	keys[KeyEvent.VK_O];
		attack = keys[KeyEvent.VK_V];
	}	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	
}
