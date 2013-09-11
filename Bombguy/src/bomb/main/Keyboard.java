package bomb.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys;

	public Keyboard() {
		keys = new boolean[600];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		// System.out.println(e.getKeyCode() + " key pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public boolean isPressed(int id) {
		return keys[id];
	}

	public void poll(int keyId) {
		keys[keyId] = false;
	}

}
