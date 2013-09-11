package bomb.main;

import java.util.LinkedList;

import bomb.enemies.Boss;
import bomb.enemies.BossType;
import bomb.enemies.Missile;

public class Ivory {

	private int width, height;

	private BombGuy bombGuy;
	private LinkedList<Bomb> bombs;
	private LinkedList<Missile> missiles;
	private Boss currentBoss;

	public Ivory() {

		width = height = 768;

		bombGuy = new BombGuy(100, 100);

		bombs = new LinkedList<Bomb>();
		missiles = new LinkedList<Missile>();

		currentBoss = new Boss(BossType.PEACH,//
				width / 4 + (int) (Math.random() * width / 2), //
				height / 4 + (int) (Math.random() * height / 2));
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BombGuy getBombGuy() {
		return bombGuy;
	}

	public void putBomb(double dir) {
		bombs.add(new Bomb(bombGuy.getX(), bombGuy.getY(), dir, Main.TIMER));
	}

	public void putMissile(double d, double e, double dir, int duration) {
		missiles.add(new Missile(d, e, dir, duration));
	}

	public LinkedList<Bomb> getBombs() {
		return bombs;
	}

	public LinkedList<Missile> getMissiles() {
		return missiles;
	}
	
	public Boss getCurrentBoss() {
		return currentBoss;
	}

}
