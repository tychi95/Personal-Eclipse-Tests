package singleClassBorderLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CounterGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private int value = 0;
	private JLabel display;

	public CounterGUI() {
		display = new JLabel("None");

		/* Layout manager that allow us to place elements in */
		/* North, South, West, East, Center sections */
		setLayout(new BorderLayout());

		/* Adds JLabel representing the display */
		add(display, BorderLayout.NORTH);

		/* Adds the increment button */
		JButton incrementButton = new JButton("Increment");
		add(incrementButton, BorderLayout.CENTER);

		/* Adds action to the button using inner class */
		incrementButton.addActionListener(new IncrementListener());

		/* Adds the decrement button */
		JButton decrementButton = new JButton("Decrement");
		add(decrementButton, BorderLayout.SOUTH);

		/* Adds action to the button using inner class */
		decrementButton.addActionListener(new DecrementListener());
	}

	/* Listener for increment button */
	private class IncrementListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			value++;
			display.setText("Current Value: " + value);
			repaint();
		}
	}

	private class DecrementListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			value--;
			display.setText("Current Value: " + value);
			repaint();
		}
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Counter");

		/* Adds the panel to the frame */
		frame.setContentPane(new CounterGUI());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack(); /* Using pack now */

		/* Shows the GUI */
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}