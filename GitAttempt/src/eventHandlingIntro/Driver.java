package eventHandlingIntro;

import javax.swing.*;
public class Driver {

	private static void createAndShowGUI() {
		int frameWidth = 550, frameHeight = 100;
		JPanel actualGUI = new MessageJPanel();
		MyFrame frame = new MyFrame("Message Borad", actualGUI, frameWidth, frameHeight);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		/* Scheduling the GUI processing in the EDT */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}