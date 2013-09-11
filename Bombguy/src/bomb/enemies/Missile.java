package bomb.enemies;

import bomb.main.ObjectInterface;

public class Missile implements ObjectInterface {

	private double x, y, timer, dir;

	public Missile(double d, double e, double dir, double t) {
		x = d;
		y = e;
		timer = t;
		this.dir = dir;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void move(double d, double e) {
		x += d;
		y += e;
	}

	public double getTimer() {
		return timer;
	}

	public int getSpeed() {
		return 10;
	}

	public double getDir() {
		return dir;
	}

	public int getDamage() {
		return 5;
	}

	public void addTimer(int i) {
		timer -= i;
	}

}
