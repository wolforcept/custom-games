package bomb.main;

public class BombGuy implements ObjectInterface {

	private double x, y;

	private int maxhp, hp;

	public BombGuy(int argX, int argY) {
		x = argX;
		y = argY;
		maxhp = hp = 100;
	}

	@Override
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void move(double xx, double yy) {
		x += xx;
		y += yy;
	}

	public int getSize() {
		return 4;
	}

	public void addHealth(int i) {
		hp += i;
	}

	public int getHealth() {
		return hp;
	}

	public double getMaxHealth() {
		return maxhp;
	}

}
