package tables;

/**
 * Example that illustrates how to display information using JTables.
 * Click on the "TAs Info" button to see information about TAs and on the
 * "Instructors Info" button to see instructors' information. 
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Info1 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	TableDisplay tableDisplay;

	private String title = "Information about TAs and Instructors";

	private Object[] columnNamesTAs = new String[] { "TA Name", "Office Hours" };

	private Object[][] twoDimDataArrayTAs = new String[][] { { "Mary", "1-2" }, { "John", "2-3" }, { "Kathy", "4-5" } };

	private Object[] columnNamesInstructors = new String[] { "Instructor", "Office", "Office Hours" };

	private Object[][] twoDimDataArrayInstructors = new String[][] { { "Pat", "CSS1420", "1-2" },
			{ "Kim", "CSI1000", "4-5" } };

	public Info1() {
		defineFrame();
		defineGUI();
		displayGUI();
	}

	private void defineFrame() {
		frame = new JFrame(title);
		JFrame.setDefaultLookAndFeelDecorated(true);

		/* Sets the panel as the content pane for the frame. */
		this.setOpaque(true);
		frame.setContentPane(this);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void displayGUI() {
		frame.pack();
		frame.setVisible(true);
	}

	private void defineGUI() {
		tableDisplay = new TableDisplay();
		add(tableDisplay);

		/* By default we display TA information */
		tableDisplay.updateTable(twoDimDataArrayTAs, columnNamesTAs);

		/* Adding a button to display TAs information */
		JButton tasInfoButton = new JButton("TAs Info");
		add(tasInfoButton);
		/*
		 * We are defining as action the updating of the table with TAs'
		 * information
		 */
		tasInfoButton.addActionListener(new DisplayTAsInformation());

		/* Adding a button to display Instructors' information */
		JButton instructorsInfoButton = new JButton("Instructors Info");
		add(instructorsInfoButton);
		/*
		 * We are defining as action the updating of the table with instructors'
		 * information
		 */
		instructorsInfoButton.addActionListener(new DisplayInstructorsInfo());
	}

	private class DisplayTAsInformation implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			tableDisplay.updateTable(twoDimDataArrayTAs, columnNamesTAs);
		}
	}

	private class DisplayInstructorsInfo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			tableDisplay.updateTable(twoDimDataArrayInstructors, columnNamesInstructors);
		}
	}

	private class TableDisplay extends JPanel {
		private static final long serialVersionUID = 1L;

		private JTable table;

		private DefaultTableModel tableModel;

		public TableDisplay() {
			/* Creating the table object and setting some table properties */
			/*
			 * Remember, the JTable represents the view of our data (View
			 * component
			 */
			/* of the MVC model */

			table = new JTable();
			table.getTableHeader().setForeground(Color.RED);
			table.getTableHeader().setBackground(Color.GREEN);
			table.setGridColor(Color.BLACK);
			table.setForeground(Color.BLUE);
			table.setSelectionBackground(Color.RED);

			/* Setting the scrollPane */
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane);
		}

		public void updateTable(Object[][] twoDimDataArray, Object[] columnNames) {

			/* Defining our table model object which holds the data */
			/* We construct a new model object each time we want to */
			/* update our table and set the table model to the new object */
			tableModel = new DefaultTableModel(twoDimDataArray, columnNames);
			table.setModel(tableModel);
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Info1();
			}
		});
	}
}