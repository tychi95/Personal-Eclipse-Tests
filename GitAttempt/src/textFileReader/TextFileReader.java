package textFileReader;

/**
 * You can read local project text files (e.g., hamlet.txt) or computer files
 * (e.g., C:\Documents and Settings\loginName\Desktop\beatles.txt)
 */

/* TODO Try defining display as a local variable.  What happens?  
 * What if you defined as a final variable? */

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class TextFileReader extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea display;
	private JTextField fileNameTextField;
	
	public TextFileReader(int frameWidth, int frameHeight) {
		/* Header */
		add(new JLabel("Text File Reader"));

		/* Defining a scroll bar text area */
		display = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setPreferredSize(new Dimension(9 * frameWidth / 10, 6 * frameHeight / 10));
		add(scrollPane);

		/* Defines button to trigger file reading */
		JButton readFileButton = new JButton("Read File");
		add(readFileButton);
		readFileButton.addActionListener(new FileReadingListener());

		/* Defines textfield for filename */
		int fileNameTextFieldLength = 40;
		fileNameTextField = new JTextField(fileNameTextFieldLength);
		add(fileNameTextField);
	}

	private class FileReadingListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			display.setText(Utilities.getFileContents(fileNameTextField.getText()));
		}
	};

	private static void createAndShowGUI() {
		int width = 650, height = 300;
		JFrame frame = new JFrame("Text File Reader");
		frame.setSize(new Dimension(width, height));
		frame.setContentPane(new TextFileReader(width,
				height)); /* Adds the panel to the frame */

		/* Centralizing the frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int upperLeftCornerX = (screenSize.width - frame.getWidth()) / 2;
		int upperLeftCornerY = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(upperLeftCornerX, upperLeftCornerY);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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