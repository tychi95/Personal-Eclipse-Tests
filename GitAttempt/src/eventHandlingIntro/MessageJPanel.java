package eventHandlingIntro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int value = 0;
	private JLabel display;

	public MessageJPanel() {
		display = new JLabel("Current Value: " + value);
		add(display);

		/* Adds the greetings button */
		JButton greetingsButton = new JButton("Print Greetings in console");
		add(greetingsButton);
		
		/* Associates action with the button using inner class (defined below) */
		greetingsButton.addActionListener(new GreetingsButtonListener());
		
		/* Adds the increment button */
		JButton incrementButton = new JButton("Increment");
		
		/* Associates action with the button using inner class (defined below) */
		add(incrementButton);
		incrementButton.addActionListener(new IncrementButtonListener());
	}

	/* Inner Class: handles greetings */
	private class GreetingsButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Hello!!!");
		}
	}
	
	/* Inner Class: handles increments */
	private class IncrementButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			value++;
			display.setText("Current Value: " + value);
			repaint();
		}
	}	
}