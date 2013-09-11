package bomb.main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bomb.enemies.Boss;
import bomb.enemies.Missile;
import bomb.enemies.Turret;

public class Main {

	public static final long FREQUENCY = 60;

	public static final int TIMER = 200;

	private JFrame frame;

	private Ivory data;
	private Taylor panel;
	private Keyboard keyboard;

	private boolean mouse_pressed = false;

	private int mouseX, mouseY;

	public Main() {

		mouseX = 0;
		mouseY = 0;

		keyboard = new Keyboard();
		data = new Ivory();
		panel = new Taylor(data);

		frame = new JFrame();
		frame.requestFocus();
		frame.addKeyListener(keyboard);
		frame.setSize(data.getWidth(), data.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				Main.exit();
			}
		});

		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				mouse_pressed = false;
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				mouse_pressed = true;
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);

	}

	private void start() {
		new Updater().start();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main().start();
	}

	public static void exit() {
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", null,
				JOptionPane.YES_NO_OPTION) == 0) {
			System.exit(0);
		}
	}

	/**
	 * Updater
	 */
	private class Updater extends Thread {

		public Updater() {
			setName("Updater Thread");
		}

		@Override
		public void run() {
			System.out.println("Updater Started");

			try {
				while (true) {

					int xx = 0, yy = 0;
					int speed = 5;

					if (keyboard.isPressed(KeyEvent.VK_W)) {
						yy -= speed;
					}
					if (keyboard.isPressed(KeyEvent.VK_S)) {
						yy += speed;
					}
					if (keyboard.isPressed(KeyEvent.VK_A)) {
						xx -= speed;
					}
					if (keyboard.isPressed(KeyEvent.VK_D)) {
						xx += speed;
					}
					if (keyboard.isPressed(KeyEvent.VK_ESCAPE)) {
						keyboard.poll(KeyEvent.VK_ESCAPE);
						Main.exit();
					}
					if (keyboard.isPressed(KeyEvent.VK_SPACE)) {
						// data.putBomb();
					}
					BombGuy bombGuy = data.getBombGuy();

					if (mouse_pressed)
						data.putBomb(point_direction(bombGuy.getX(),
								bombGuy.getY(), mouseX, mouseY));

					bombGuy.move(xx, yy);

					Boss boss = data.getCurrentBoss();

					double bmdir = point_direction(boss.getX(), boss.getY(),
							bombGuy.getX(), bombGuy.getY());
					boss.move(Math.cos(bmdir), Math.sin(bmdir));

					LinkedList<Turret> turrets = data.getCurrentBoss()
							.getTurrets();
					for (Iterator<Turret> iterator = turrets.iterator(); iterator
							.hasNext();) {
						Turret t = (Turret) iterator.next();
						t.addAngle(1);
						t.move(boss.getX(), boss.getY());
						if (Math.random() < 0.1) {
							double missileDir = point_direction(t.getX(),
									t.getY(), bombGuy.getX(), bombGuy.getY());
							data.putMissile(t.getX(), t.getY(), missileDir, 10);
							data.putMissile(t.getX(), t.getY(),
									missileDir + 0.1, 10);
							data.putMissile(t.getX(), t.getY(),
									missileDir - 0.1, 10);
						}
						if (t.isRemoved())
							iterator.remove();
					}

					Iterator<Bomb> a = data.getBombs().iterator();
					while (a.hasNext()) {
						Bomb bomb = a.next();
						bomb.move(bomb.getSpeed() * Math.cos(bomb.getDir()),
								bomb.getSpeed() * Math.sin(bomb.getDir()));

						if (boss.getSize() / 2 > point_distance(bomb.getX(),
								bomb.getY(), boss.getX(), boss.getY())) {
							boss.hurt(bomb.getDamage());
							a.remove();
						}

						for (Turret turret : boss.getTurrets()) {
							if (turret.getSize() / 2 > point_distance(
									bomb.getX(), bomb.getY(), turret.getX(),
									turret.getY())) {
								if (turret.hurt(bomb.getDamage())) {
									turret.setRemove(true);
								}
								a.remove();
								bomb.remove();
							}
						}

						bomb.addTimer(-1);
						if (bomb.getTimer() < 0) {
							if (!bomb.isRemoved())
								a.remove();
						}
					}

					Iterator<Missile> missilesIt = data.getMissiles()
							.iterator();
					while (missilesIt.hasNext()) {
						Missile missile = missilesIt.next();
						missile.move(
								missile.getSpeed() * Math.cos(missile.getDir()),
								missile.getSpeed() * Math.sin(missile.getDir()));

						System.out.println();

						if (bombGuy.getSize() / 2 + missile.getSpeed() > point_distance(
								missile.getX(), missile.getY(), bombGuy.getX(),
								bombGuy.getY())) {

							bombGuy.addHealth(-missile.getDamage());
							missilesIt.remove();
						}

						missile.addTimer(-1);
						if (missile.getTimer() < 0) {
							missilesIt.remove();
						}
					}

					panel.repaint();

					sleep(1000 / Main.FREQUENCY);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println("------ Updater Interrupted ------");
			}
		}
	}

	static double point_distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	static double point_direction(double x1, double y1, double x2, double y2) {
		return Math.atan2(y2 - y1, x2 - x1);
	}

}
