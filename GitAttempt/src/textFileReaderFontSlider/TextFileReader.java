package textFileReaderFontSlider;

/**
 * You can read local project text files (e.g., hamlet.txt) or computer files
 * (e.g., C:\Documents and Settings\loginName\Desktop\beatles.txt)
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.*;
import javax.swing.event.*;

import textFileReader.Utilities;

public class TextFileReader extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField fileNameTextField;
	private JTextArea display;
	private JSlider slider;
	private int fontSize = 14;

	public TextFileReader(int frameWidth, int frameHeight) {
		/* Header */
		add(new JLabel("Text File Reader"));

		/* Defining a scroll bar text area */
		display = new JTextArea();
		display.setFont(new Font("serif", Font.PLAIN, fontSize));
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setPreferredSize(new Dimension(9 * frameWidth / 10, 6 * frameHeight / 10));
		add(scrollPane);

		/* Defines button to trigger file reading */
		JButton readFileButton = new JButton("Read File");
		add(readFileButton);
		readFileButton.addActionListener(new FileReadingListener());

		/* Defines button to trigger font size change */
		JButton fontSizeButton = new JButton("FontSize");
		add(fontSizeButton);
		fontSizeButton.addActionListener(new FontSizeListener());

		/* Defines textfield for filename */
		int fileNameTextFieldLength = 10;
		fileNameTextField = new JTextField(fileNameTextFieldLength);
		fileNameTextField.setText("hamlet.txt");
		add(fileNameTextField);

		/* Adding slider */
		slider = new JSlider();
		slider.setMinimum(1);
		slider.setMaximum(40);
		slider.setValue(fontSize);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10); /* draws numbers */
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		add(slider);
		slider.addChangeListener(new SliderListener());
	}

	private class FileReadingListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			display.setText(Utilities.getFileContents(fileNameTextField.getText()));
		}
	};

	private class FontSizeListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				fontSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter font size", 22));
				display.setFont(new Font("serif", Font.PLAIN, fontSize));
				slider.setValue(fontSize);
			}
			/* Handling an unchecked exception */
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You must enter a valid font size");
			}
		}
	};

	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			fontSize = slider.getValue();
			display.setFont(new Font("serif", Font.PLAIN, fontSize));
		}
	}

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