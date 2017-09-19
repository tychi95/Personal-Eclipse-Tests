package timer;

import javax.swing.*;
import java.awt.event.*;

public class TimerExample {
	private static int ANIMATION_SPEED_MILLISECS = 1000;

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("Hello Everyone!");
		}
	}

	public TimerExample() {
		Timer timer = new Timer(ANIMATION_SPEED_MILLISECS, new TimerListener());

		/* we need to start the timer (we can stop a timer with stop()) */
		timer.start();

		JOptionPane.showMessageDialog(null, "Select OK to end program");
	}

	public static void main(String[] args) {
		new TimerExample();
	}
}
