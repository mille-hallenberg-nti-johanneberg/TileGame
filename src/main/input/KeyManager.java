package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyManager implements KeyListener{
//	private boolean[] keys;
	private Keys[] keys;
		
	private ArrayList<Keys> updateKeysList = new ArrayList<>(); 
	private ArrayList<Keys> pressedKeys = new ArrayList<>();
	
	public boolean
	up, left, down, right, place, remove, save, attack;
	
	public KeyManager() {
//		keys = new boolean[256];
		keys = new Keys[256];
		
		init();
	}
	
	private void init(){
		keyBind(KeyEvent.VK_W, Keys.UP);
		keyBind(KeyEvent.VK_UP, Keys.UP);
		keyBind(KeyEvent.VK_A, Keys.LEFT);
		keyBind(KeyEvent.VK_LEFT, Keys.LEFT);
		keyBind(KeyEvent.VK_S, Keys.DOWN);
		keyBind(KeyEvent.VK_DOWN, Keys.DOWN);
		keyBind(KeyEvent.VK_D, Keys.RIGHT);
		keyBind(KeyEvent.VK_RIGHT, Keys.RIGHT);
		
		keyBind(KeyEvent.VK_SPACE, Keys.PLACE);
		keyBind(KeyEvent.VK_C, Keys.REMOVE);
		keyBind(KeyEvent.VK_O, Keys.SAVE);
		keyBind(KeyEvent.VK_V, Keys.ATTACK);
	}
	
	public void keyBind(int keyCode, Keys key){
		keys[keyCode] = key;
	}
	
	public void update() {
//		up = 	keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
//		left = 	keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
//		down = 	keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
//		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
//		place = keys[KeyEvent.VK_SPACE];
//		remove = keys[KeyEvent.VK_C];
//		save = 	keys[KeyEvent.VK_O];
//		attack = keys[KeyEvent.VK_V];
		
		for (Keys k : updateKeysList){
			k.setDown(false);
			k.setUp(false);
		}
		updateKeysList = new ArrayList<>();
	}	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Keys k = keys[e.getKeyCode()];
		k.setPressed(true);
		if (!pressedKeys.contains(k)){
			k.setDown(true);
			pressedKeys.add(k);
		}
		updateKeysList.add(k);
//		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keys k = keys[e.getKeyCode()];
		k.setPressed(false);
		k.setUp(true);
		pressedKeys.remove(k);
		updateKeysList.add(k);
//		keys[e.getKeyCode()] = false;
	}
	
	
}
