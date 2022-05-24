package main.entity.creature;

import java.awt.Graphics;
import java.util.Random;

import main.Handler;
import main.gfx.Assets;

public class Slime extends Creature{

	public Slime(int x, int y) {
		super(x, y, 32, 32);
		bounds.x = 0;
		bounds.y = 0;
	}
	
	int moveAmountX;
	int moveAmountY;

	@Override
	public void update() {
		
		if (moveAmountX == 0 && moveAmountY == 0) {
			Random rand = new Random();
			moveAmountX = rand.nextInt(100)-50;
			moveAmountY = rand.nextInt(100)-50;
		}
		else {
			if (Math.abs(moveAmountX) > 0) {
				moveAmountX-=Math.signum(moveAmountX);
				moveX = Math.signum(moveAmountX) * 3;
			}
			if (Math.abs(moveAmountY) > 0) {
				moveAmountY-=Math.signum(moveAmountY);
				moveY = Math.signum(moveAmountY) * 3;
			}
		}
		
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.slimeCreature, (int)x - Handler.instance.getGameCamera().getxOffset(), (int)y - Handler.instance.getGameCamera().getyOffset(), width, height,  null);
		//g.drawRect((int)(x + bounds.x) - Handler.instance.getGameCamera().getxOffset(), (int)(y + bounds.y) - Handler.instance.getGameCamera().getyOffset(), width, height);
	}

}
