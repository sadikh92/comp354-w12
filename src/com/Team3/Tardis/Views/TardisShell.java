package com.Team3.Tardis.Views;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Team3.Tardis.Controller.TardisController;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskWriter;
/**
 * @author Eric Dana,Eric Regnier,Alex Landovskis,Jaffari Rahmatullah,David Campbell
 * @description tardisShell
 * @Last modified 3/21/12 11:48
 */
public class TardisShell extends JFrame implements ITardisShell {
	// Holds the tabbed pane
	JTabbedPane tabbedPane = new JTabbedPane();

	// Holds the array of tasks and the array of people
	private ArrayList<Task> tasks;
	private ArrayList<Person> people;

	// Stores the panels created by TaskUI and PeopleUI
	private TaskUI taskPanel;
	private PeopleUI peoplePanel;
	private TreeView treePanel;
	private GanttView ganttPanel;
	
	// constructor
	public TardisShell(ArrayList<Task> tasks, ArrayList<Person> people) {
		super("TARDIS Task Manager");

		// This will prevent the user from closing the window accidentally
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Sets tasks and people with a shallow copy so that changes affect the
		// arrayLists
		// in the main
		this.tasks = tasks;
		this.people = people;

		// Creates the file menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// Creates the menu items
		JMenuItem save = new JMenuItem("Save");
		JMenuItem printSubMenu = new JMenu("Print");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem printTask = new JMenuItem("Print Tasks");
		JMenuItem printPeople = new JMenuItem("Print People");

		// Adds them to the menu
		fileMenu.add(save);
		fileMenu.add(printSubMenu);
		fileMenu.add(exit);
		printSubMenu.add(printTask);
		printSubMenu.add(printPeople);

		// Creates the task panel
		taskPanel = new TaskUI(this, this.tasks, this.people);

		// Creates the people panel
		peoplePanel = new PeopleUI(this, this.tasks, this.people);

		// Creates the tree panel
		treePanel = new TreeView(this, this.tasks);

		// Creates the GANTT panel
		ganttPanel = new GanttView(this, this.tasks);

		// Adds a tab to the frame for each panel
		tabbedPane.addTab("Tasks", taskPanel);
		tabbedPane.addTab("People", peoplePanel);
		tabbedPane.addTab("Tree", treePanel);
		tabbedPane.addTab("GANTT Chart", ganttPanel);

		// Adds the tabbed pane
		add(tabbedPane);

		final TardisShell self = this;

		// Action listener for the save function
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Changes are saved to the task XML
				try {
					TaskWriter writer = new TaskWriter();
					boolean saved = writer.writeTasks(TardisController.TASKS_FILE, self.tasks);

					if (saved)
						JOptionPane.showMessageDialog(null, "Your changes have been saved to the XML file.");
					else
						JOptionPane.showMessageDialog(null, "Failed to save changes to the XML file.");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Exception. Failed to save changes to the XML file.");
				}
			}
		});

		// Action listener for the exit function
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Asks the user whether or not they want to save before
				// quitting
				int n = JOptionPane.showConfirmDialog(null, "Would you like to save your changes before exiting?", "Save Confirmation", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION) {
					// Changes are saved to the task XML
					try {
						TaskWriter writer = new TaskWriter();
						boolean saved = writer.writeTasks(TardisController.TASKS_FILE, self.tasks);

						if (!saved)
							JOptionPane.showMessageDialog(null, "Failed to save changes to the XML files.");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Failed to save changes to the XML files.");
					}
				} else if (n == JOptionPane.NO_OPTION) {
					// Nothing happens
				} else {
					System.out.println("Unexpected error.");
				}

				System.exit(0);
			}
		});

		// Action listener for the task printer
		printTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskPrinter();

				JOptionPane.showMessageDialog(null, "The Table View of Tasks has been printed to a text file.");
			}
		});

		// Action listener for the task printer
		printPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				peoplePrinter();

				JOptionPane.showMessageDialog(null, "The Table View of People has been printed to a text file.");
			}
		});

		// Change listener for tabs
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tabbedPane.revalidate();
			}
		});
	}

	// Update will be called to update the different panels when a change is
	// made
	// to the Table View of Tasks
	public void update() {
		taskPanel.update();
		peoplePanel.update();
		treePanel.update();
		ganttPanel.update();
	}

	// Prints a text file of the Table View of Tasks
	public void taskPrinter() {
		TaskView taskView = new TaskView();
		taskView.view("Table View of Tasks.txt", this.people, this.tasks);
	}

	// Prints a text file of the Table View of People
	public void peoplePrinter() {
		PeopleView peopleView = new PeopleView();
		peopleView.view("Table View of People.txt", this.people, this.tasks);
	}

	// Static Method used to create the Frame
	// Called by the main
	public static void createAndShowGUI(ArrayList<Task> tasks, ArrayList<Person> people) {
		// Creates a new frame
		JFrame frame = new TardisShell(tasks, people);

		// Listens for the closing of the window
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Asks the user whether or not they want to save before
				// quitting
				int n = JOptionPane.showConfirmDialog(null, "Any changes since your last save will be lost. Are you sure that you want to quit?", "Save Confirmation", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (n == JOptionPane.NO_OPTION) {
					// Nothing happens
				} else {
					System.out.println("Unexpected error.");
				}
			}
		});

		// Displays the frame of the right size
		frame.setSize(1300, 600);
		frame.setVisible(true);
	}
}