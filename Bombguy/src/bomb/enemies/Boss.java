package bomb.enemies;

import java.util.LinkedList;

import bomb.main.ObjectInterface;

public class Boss implements ObjectInterface {

	private double x, y;
	private int hp, maxHp, size;
	private LinkedList<Turret> turrets;

	public Boss(BossType t, int x, int y) {
		this.hp = this.maxHp = t.hp;
		this.size = t.size;
		this.x = x;
		this.y = y;
		turrets = new LinkedList<Turret>();

		turrets.add(new Turret(1000, 32, 50, 0, x, y));
		turrets.add(new Turret(1000, 32, 50, 90, x, y));
		turrets.add(new Turret(1000, 32, 50, 180, x, y));
		turrets.add(new Turret(1000, 32, 50, 270, x, y));
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
	public void move(double xx, double yy) {
		x += xx;
		y += yy;
	}

	public int getHealth() {
		return hp;
	}

	public int getMaxHealth() {
		return maxHp;
	}

	public boolean hurt(int dmg) {
		hp -= dmg;
		return hp > 0 ? false : true;
	}

	public int getSize() {
		return size;
	}

	public LinkedList<Turret> getTurrets() {
		return turrets;
	}
}
