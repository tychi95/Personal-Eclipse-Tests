package tables;

/**
 * Example illustrates how we can detect which row has been selected, how to
 * use BorderLayout manager and how to use a textArea.  After selecting a course
 * select one of the two buttons to get either summary information or specific 
 * information.
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Info2 extends JPanel {
	private static final long serialVersionUID = 2L;
	private int width = 350;
	private int tableTextAreaHeight = 75;
	private JFrame frame;
	private TableDisplay tableDisplay;
	private JTextArea textArea;
	private String title = "Information about Courses";
	private Object[] columnNames = new String[] { "Course Name", "Section" };
	private Object[][] twoDimDataArrayCourses = new String[][] { { "cmsc106", "0101" }, { "cmsc117", "0201" },
			{ "cmsc217", "0102" }, { "cmsc317", "0102" }, { "cmsc717", "4444" } };
	private String[] summaryInfo = { "cmsc106, Intro to programming, 0101", "cmsc117, Networking, 0201",
			"cmsc217, Adv. Networking, 0101", "cmsc317, Database, 0102", "cmsc717, Operating System, 4444", };

	private String[] specificInfo = { "cmsc106, Intro to programming, 0101\nNon-Majors, Dr. Prog",
			"cmsc117, Networking, 0201\nMajors, Dr. Net", "cmsc217, Adv. Networking, 0101\nMajors, Dr. SuperNet",
			"cmsc317, Database, 0102\nMajors, Dr. Database", "cmsc717, Operating System, 4444\nMajors, Dr. OS" };

	public Info2() {
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
		/* Displays entries for courses */
		tableDisplay = new TableDisplay();
		add(tableDisplay);
		tableDisplay.updateTable(twoDimDataArrayCourses, columnNames);

		/* The following scrollable text area will display info for */
		/* elements selected from the table */
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setPreferredSize(new Dimension(width, tableTextAreaHeight));
		add(scrollPane);

		/* Adding two buttons to get information. One represents a */
		/* summary button and the second provides more detail information */
		JButton summaryButton = new JButton("Summary");
		add(summaryButton);
		summaryButton.addActionListener(new Summary());

		JButton specificButton = new JButton("Specific Info");
		add(specificButton);
		specificButton.addActionListener(new Specific());

		/* The Border layout will allow us to organize our components */
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		add(tableDisplay, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);

		/*
		 * Panel to combine buttons so we can place them in the SOUTH
		 * BorderLayout area
		 */
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(summaryButton);
		buttonsPanel.add(specificButton);

		/* Adding the buttons panel to rest of the GUI */
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private class Summary implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			/* Determining which course was selected */
			int selectedRow = tableDisplay.getTable().getSelectedRow();

			textArea.setText(summaryInfo[selectedRow]);
		}
	}

	private class Specific implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			/* Determining which course was selected */
			int selectedRow = tableDisplay.getTable().getSelectedRow();

			textArea.setText(specificInfo[selectedRow]);
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
			scrollPane.setPreferredSize(new Dimension(width, tableTextAreaHeight));
			add(scrollPane);
		}

		public void updateTable(Object[][] twoDimDataArray, Object[] columnNames) {

			/* Defining our table model object which holds the data */
			/* We construct a new model object each time we want to */
			/* update our table and set the table model to the new object */
			tableModel = new DefaultTableModel(twoDimDataArray, columnNames);
			table.setModel(tableModel);
		}

		public JTable getTable() {
			return table;
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Info2();
			}
		});
	}
}