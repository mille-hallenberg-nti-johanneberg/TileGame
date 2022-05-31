package main.input;

public enum Keys {
	UP, LEFT, DOWN, RIGHT, PLACE, REMOVE, SAVE, ATTACK;
	
	private Keys(){
		
	}
	
	private boolean pressed, up, down;
	
	public boolean isPressed() {
		return pressed;
	}

	protected void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public boolean isUp() {
		return up;
	}

	protected void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	protected void setDown(boolean down) {
		this.down = down;
	}
}
