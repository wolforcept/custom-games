package bomb.enemies;

import bomb.main.ObjectInterface;

public class Turret implements ObjectInterface {

	private double cx, cy;
	private int hp, maxHp, size, dist, angle;
	private boolean remove;

	public Turret(int hp, int size, int dist, int angle, int cx, int cy) {
		this.hp = this.maxHp = hp;
		this.size = size;
		this.cx = cx;
		this.cy = cy;
		this.dist = dist;
		this.angle = angle;
		remove = false;
	}

	@Override
	public double getX() {
		return cx + Math.cos(Math.toRadians(angle)) * dist;
	}

	@Override
	public double getY() {
		return cy + Math.sin(Math.toRadians(angle)) * dist;
	}

	@Override
	public void move(double xx, double yy) {
		cx = xx;
		cy = yy;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public boolean hurt(int dmg) {
		hp -= dmg;
		return hp > 0 ? false : true;
	}

	public int getSize() {
		return size;
	}

	public void addAngle(int i) {
		angle += i;
	}

	public void setRemove(boolean b) {
		remove = true;
	}

	public boolean isRemoved() {
		return remove;
	}
}
