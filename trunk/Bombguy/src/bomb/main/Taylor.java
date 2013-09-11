package bomb.main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import bomb.enemies.Boss;
import bomb.enemies.Missile;
import bomb.enemies.Turret;

public class Taylor extends JPanel {

	private static final long serialVersionUID = 1L;

	Ivory data;

	public Taylor(Ivory argData) {
		data = argData;
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		// g.clearRect(0, 0, getWidth(), getHeight());

		Boss boss = data.getCurrentBoss();
		BombGuy bombGuy = data.getBombGuy();

		g.setColor(new Color((float)(Math.random()*0.1), (float)(Math.random()*0.1),(float)(Math.random()*0.1), 0.1f));
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(new Color(0.5f, 0.5f, 1f, 1f));
		int bx = (int) bombGuy.getX();
		int by = (int) bombGuy.getY();
		int bs = bombGuy.getSize();
		g.fillRect(bx - bs / 2, by - bs / 2, bs, bs);

		// g.drawString("BombGuy", bombGuy.getX(),
		// bombGuy.getY());

		g.setColor(new Color(1f, 1f, 1f,
				(float) (Math.random() /* 0.5f + 0.5f */)));

		for (Bomb bomb : data.getBombs()) {
			g.fillOval((int) bomb.getX(), (int) bomb.getY(), 5, 5);
			// g.drawString("Bomb", bomb.getX(), bomb.getY());
		}

		g.setColor(new Color(0.7f, 0.7f, 0f, 0.5f));
		for (Missile missile : data.getMissiles()) {
			g.fillOval((int) missile.getX(), (int) missile.getY(), 5, 5);
			// g.drawString("Bomb", bomb.getX(), bomb.getY());
		}

		g.setColor(new Color(0f, 0f, 0f, 1f));
		g.fillRect(0, 11, getWidth(), 10);
		g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));
		g.fillRect(
				0,
				11,
				(int) ((double) boss.getHealth() / (double) boss.getMaxHealth() * getWidth()),
				10);

		g.setColor(new Color(0f, 0f, 0f, 1f));
		g.fillRect(0, 0, getWidth(), 10);
		g.setColor(new Color(0.1f, 0.1f, 0.5f, 0.5f));
		g.fillRect(
				0,
				0,
				(int) ((double) bombGuy.getHealth()
						/ (double) bombGuy.getMaxHealth() * getWidth()), 10);

		g.setColor(new Color(1f, 1f, 1f, 0.4f));
		g.drawString(
				"Boss Health: " + boss.getHealth() + " / "
						+ boss.getMaxHealth(), 20, 20);
		g.setColor(new Color(0.5f, 0f, 0f, 1f));

		// BOSS
		int rx = (int) (Math.random() * 4);
		int ry = (int) (Math.random() * 4);

		g.drawOval(//
				(int) boss.getX() - boss.getSize() / 2 - rx,//
				(int) boss.getY() - boss.getSize() / 2 - ry,//
				boss.getSize() + rx + (int) (Math.random() * 4),//
				boss.getSize() + ry + (int) (Math.random() * 4));

		for (Turret t : boss.getTurrets()) {

			int trxx = (int) (Math.random() * 4);
			int tryy = (int) (Math.random() * 4);

			int tx = (int) (t.getX());
			int ty = (int) (t.getY());
			int ts = t.getSize();
			g.drawOval(//
					(int) tx - ts / 2 - trxx,//
					(int) ty - ts / 2 - tryy,//
					ts + trxx + (int) (Math.random() * 4),//
					ts + tryy + (int) (Math.random() * 4));

			g.drawString("hp: " + t.getHp(), tx, ty);
		}

	}
}
