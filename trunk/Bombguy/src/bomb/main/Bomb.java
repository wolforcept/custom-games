package bomb.main;

public class Bomb implements ObjectInterface {

	private double x, y, timer, dir;
	private boolean removed = false;

	public Bomb(double d, double e, double dir, double t) {
		x = d;
		y = e;
		timer = t;
		this.dir = dir - 0.1 + Math.random() * 0.2;
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

	public void addTimer(int a) {
		timer += a;
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

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
